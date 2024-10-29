package game;
import actions.GameAction;
import characters.Character;
import actions.AttackAction;
import actions.DefendAction;
import actions.HealAction;
import characters.strategies.MeleeStrategy;
import characters.strategies.RangedStrategy;
import characters.strategies.MagicStrategy;

import java.util.Scanner;

public class SimpleGameLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name for your hero: ");
        String heroName = scanner.nextLine();
        Character hero = new Character(heroName);
        Character goblin = new Character("Goblin");

        System.out.println("Choose your attack strategy:");
        System.out.println("1. Melee Attack");
        System.out.println("2. Ranged Attack");
        System.out.println("3. Magic Attack");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                hero.setAttackStrategy(new MeleeStrategy());
                break;
            case 2:
                hero.setAttackStrategy(new RangedStrategy());
                break;
            case 3:
                hero.setAttackStrategy(new MagicStrategy());
                break;
            default:
                System.out.println("Invalid choice! Using Melee Attack by default.");
                hero.setAttackStrategy(new MeleeStrategy());
                break;
        }

        goblin.setHealth(20);
        int goblinAttackDamage = 3;

        while (hero.getHealth() > 0 && goblin.getHealth() > 0) {
            System.out.println("--- Your turn ---");
            System.out.println("Choose an action: ");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Heal");
            int actionChoice = scanner.nextInt();

            GameAction action;

            switch (actionChoice) {
                case 1:
                    action = new AttackAction(hero);
                    action.executeAction(goblin);
                    break;
                case 2:
                    action = new DefendAction(hero);
                    action.executeAction(goblin);
                    break;
                case 3:
                    action = new HealAction(hero);
                    action.executeAction(goblin);
                    break;
                default:
                    System.out.println("Invalid action!");
                    continue;
            }

            if (goblin.getHealth() <= 0) {
                System.out.println("You defeated the Goblin!");
                break;
            }

            System.out.println("Goblin attacks Hero!");
            hero.setHealth(hero.getHealth() - goblinAttackDamage);
            System.out.println("Hero takes " + goblinAttackDamage + " damage. Health is now " + hero.getHealth());

            if (hero.getHealth() <= 0) {
                System.out.println("You have been defeated!");
            }
        }
        scanner.close();
    }
}