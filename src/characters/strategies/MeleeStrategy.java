package characters.strategies;

import characters.Character;

public class MeleeStrategy implements AttackStrategy {
    @Override
    public void attack(Character attacker, Character target) {
        int damage = 5;
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " attacks " + target.getName() + " with melee for " + damage + " damage.");
    }
}
