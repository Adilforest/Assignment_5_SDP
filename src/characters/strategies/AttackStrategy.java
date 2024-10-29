package characters.strategies;

import characters.Character;


public interface AttackStrategy {
    void attack(Character attacker, Character target);
}