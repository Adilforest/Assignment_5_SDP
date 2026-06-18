package characters.visitor;

import characters.Character;

/**
 * Visitor — restores a fixed amount of health to any {@link Character} it visits.
 * Separates the healing-over-time concern from the Character class itself,
 * following the Visitor pattern (GoF Behavioral).
 */
public class HealingVisitor implements CharacterVisitor {
    private final int healAmount;

    public HealingVisitor(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void visit(Character character) {
        int newHealth = character.getHealth() + healAmount;
        character.setHealth(newHealth);
        System.out.printf("[HealingVisitor] %s receives a restorative blessing (+%d HP). Health is now %d.%n",
                character.getName(), healAmount, character.getHealth());
    }
}
