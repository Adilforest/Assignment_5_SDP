package game;

import actions.AttackAction;
import actions.DefendAction;
import actions.GameAction;
import actions.HealAction;
import characters.Character;
import characters.states.DefeatedState;
import characters.states.PoweredUpState;
import characters.strategies.MagicStrategy;
import characters.strategies.MeleeStrategy;
import characters.strategies.RangedStrategy;
import characters.visitor.HealingVisitor;

import java.util.Scanner;

/**
 * Entry point for the turn-based console combat game "Goblin's Nightmare".
 *
 * <p>All four behavioral patterns are active during the game loop:</p>
 * <ul>
 *   <li><b>Strategy</b>       — the hero's attack delegates to the
 *       {@link characters.strategies.AttackStrategy} chosen at the start
 *       (Melee / Ranged / Magic).</li>
 *   <li><b>Template Method</b> — every hero action goes through
 *       {@link actions.GameAction#executeAction(Character)}
 *       (prepare → perform → finish).</li>
 *   <li><b>State</b>          — the hero's active {@link characters.states.CharacterState}
 *       narrates each action; the hero transitions to {@link PoweredUpState} when
 *       the goblin drops below half health, and both combatants enter
 *       {@link DefeatedState} on death.</li>
 *   <li><b>Visitor</b>        — a {@link HealingVisitor} visits the hero after every
 *       heal turn, granting a bonus restoration on top of the state-driven heal.</li>
 * </ul>
 */
public class SimpleGameLoop {

    /** Starting health for the goblin. */
    private static final int GOBLIN_START_HEALTH  = 20;
    /** Fixed damage the goblin deals each turn. */
    private static final int GOBLIN_ATTACK_DAMAGE = 3;
    /** Goblin HP at or below which the hero powers up. */
    private static final int POWERED_UP_THRESHOLD = GOBLIN_START_HEALTH / 2;
    /** Bonus HP granted by the {@link HealingVisitor} on every heal turn. */
    private static final int VISITOR_BONUS_HEAL   = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ---- Character setup ---------------------------------------------------
        System.out.print("Enter the name for your hero: ");
        String heroName = scanner.nextLine();

        Character hero   = new Character(heroName);
        Character goblin = new Character("Goblin");
        goblin.setHealth(GOBLIN_START_HEALTH);

        // ---- Strategy selection ------------------------------------------------
        System.out.println("Choose your attack strategy:");
        System.out.println("  1. Melee   (5 damage)");
        System.out.println("  2. Ranged  (4 damage)");
        System.out.println("  3. Magic   (6 damage)");
        System.out.print("Choice: ");
        int strategyChoice = scanner.nextInt();

        switch (strategyChoice) {
            case 2  -> hero.setAttackStrategy(new RangedStrategy());
            case 3  -> hero.setAttackStrategy(new MagicStrategy());
            default -> {
                if (strategyChoice != 1) {
                    System.out.println("Invalid choice — defaulting to Melee.");
                }
                hero.setAttackStrategy(new MeleeStrategy());
            }
        }

        // Visitor applied after every hero heal action
        HealingVisitor blessingVisitor = new HealingVisitor(VISITOR_BONUS_HEAL);

        // ---- Combat loop -------------------------------------------------------
        while (!hero.isDefeated() && !goblin.isDefeated()) {

            System.out.println();
            System.out.printf("=== Hero HP: %d  |  Goblin HP: %d  [state: %s] ===%n",
                    hero.getHealth(), goblin.getHealth(),
                    hero.getState().getClass().getSimpleName());
            System.out.println("Your turn — choose an action:");
            System.out.println("  1. Attack");
            System.out.println("  2. Defend");
            System.out.println("  3. Heal");
            System.out.print("Action: ");
            int actionChoice = scanner.nextInt();

            switch (actionChoice) {
                case 1 -> {
                    new AttackAction(hero).executeAction(goblin);

                    // State transition: power up when goblin drops to/below half health
                    if (goblin.getHealth() > 0
                            && goblin.getHealth() <= POWERED_UP_THRESHOLD
                            && !(hero.getState() instanceof PoweredUpState)) {
                        System.out.printf("%s feels an adrenaline surge!%n", hero.getName());
                        hero.setState(new PoweredUpState());
                    }
                }
                case 2 -> new DefendAction(hero).executeAction(goblin);
                case 3 -> {
                    // HealAction delegates to hero.heal() → state.heal(hero)
                    new HealAction(hero).executeAction(goblin);
                    // Visitor grants an additional blessing on top
                    hero.accept(blessingVisitor);
                }
                default -> {
                    System.out.println("Invalid action — please enter 1, 2, or 3.");
                    continue;
                }
            }

            // Check goblin defeat
            if (goblin.getHealth() <= 0) {
                goblin.setState(new DefeatedState());
                System.out.println();
                System.out.printf("*** %s has defeated the Goblin! Victory! ***%n", hero.getName());
                break;
            }

            // ---- Goblin counter-attack (State narration + fixed damage) ----------
            System.out.println();
            System.out.println("--- Goblin's turn ---");
            goblin.getState().attack(goblin);   // NormalState narrates the goblin's move
            hero.takeDamage(GOBLIN_ATTACK_DAMAGE);

            // Check hero defeat
            if (hero.getHealth() <= 0) {
                hero.setState(new DefeatedState());
                System.out.println();
                System.out.println("*** You have been defeated. Game over. ***");
            }
        }

        scanner.close();
    }
}
