package characters.states;

import characters.Character;

/**
 * Default combat state — baseline attack power, standard heal, and standard defence.
 */
public class NormalState implements CharacterState {

    @Override
    public void attack(Character character) {
        System.out.printf("%s attacks with standard power.%n", character.getName());
    }

    @Override
    public void heal(Character character) {
        int amount = 5;
        character.setHealth(character.getHealth() + amount);
        System.out.printf("%s heals for %d. Health is now %d.%n",
                character.getName(), amount, character.getHealth());
    }

    @Override
    public void defend(Character character) {
        System.out.printf("%s takes a defensive stance.%n", character.getName());
    }
}
