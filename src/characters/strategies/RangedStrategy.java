package characters.strategies;

import characters.Character;

public class RangedStrategy implements AttackStrategy {
    @Override
    public void attack(Character attacker, Character target) {
        int damage = 4;
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " attacks " + target.getName() + " with ranged attack for " + damage + " damage.");
    }
}
