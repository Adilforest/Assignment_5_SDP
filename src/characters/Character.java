package characters;

import characters.states.CharacterState;
import characters.states.NormalState;
import characters.states.DefeatedState;
import characters.strategies.AttackStrategy;
import characters.visitor.CharacterVisitor;

/**
 * Domain entity representing a combatant in the game.
 *
 * <ul>
 *   <li><b>Strategy</b> — attack behavior is delegated to an injected {@link AttackStrategy}.</li>
 *   <li><b>State</b>    — per-turn behavior (attack / heal / defend narration and modifiers)
 *       is controlled by the current {@link CharacterState}; the state also decides whether
 *       an action is allowed (e.g. DefeatedState blocks all actions).</li>
 *   <li><b>Visitor</b>  — {@link #accept(CharacterVisitor)} lets external visitors (e.g.
 *       {@link characters.visitor.HealingVisitor}) operate on this character without
 *       coupling them to the class hierarchy.</li>
 * </ul>
 */
public class Character {
    private final String name;
    private int health;
    private AttackStrategy attackStrategy;
    private CharacterState state;

    public Character(String name) {
        this.name = name;
        this.health = 20;
        this.state = new NormalState();
    }

    // ------------------------------------------------------------------ state

    public CharacterState getState() {
        return state;
    }

    /**
     * Transitions to a new state and announces it.
     */
    public void setState(CharacterState newState) {
        this.state = newState;
        System.out.printf("[State] %s transitions to: %s%n",
                name, newState.getClass().getSimpleName());
    }

    public boolean isDefeated() {
        return state instanceof DefeatedState;
    }

    // --------------------------------------------------------------- strategy

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    // ----------------------------------------------------------------- combat

    /**
     * Delegates first to the current state (for narration / modifiers),
     * then executes the injected {@link AttackStrategy}.
     */
    public void attack(Character target) {
        state.attack(this);
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
        } else {
            System.out.println(name + " has no attack strategy!");
        }
    }

    /** Delegates to the current state, then restores a base amount of health. */
    public void heal() {
        state.heal(this);
    }

    /** Delegates to the current state for the defend action. */
    public void defend() {
        state.defend(this);
    }

    // ----------------------------------------------------------------- visitor

    /** Accept a {@link CharacterVisitor}, enabling double-dispatch. */
    public void accept(CharacterVisitor visitor) {
        visitor.visit(this);
    }

    // ----------------------------------------------------------------- damage

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
        System.out.printf("%s takes %d damage. Health is now %d.%n", name, damage, health);
    }

    // ----------------------------------------------------------------- getters / setters

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }
}
