package characters.strategies;

import characters.Character;

public class MagicStrategy implements AttackStrategy {
    @Override
    public void attack(Character attacker, Character target) {
        int damage = 6;
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " casts a spell on " + target.getName() + " for " + damage + " damage.");
    }
}

