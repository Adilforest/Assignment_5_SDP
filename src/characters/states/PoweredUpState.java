package characters.states;

import characters.Character;

/**
 * Powered-up combat state — triggered when the hero lands a decisive blow.
 * Heals for double the normal amount and narrates enhanced actions.
 */
public class PoweredUpState implements CharacterState {

    @Override
    public void attack(Character character) {
        System.out.printf("%s attacks with heightened ferocity!%n", character.getName());
    }

    @Override
    public void heal(Character character) {
        int amount = 10;
        character.setHealth(character.getHealth() + amount);
        System.out.printf("%s channels power to heal for %d. Health is now %d.%n",
                character.getName(), amount, character.getHealth());
    }

    @Override
    public void defend(Character character) {
        System.out.printf("%s raises an enhanced guard, deflecting more damage!%n", character.getName());
    }
}
