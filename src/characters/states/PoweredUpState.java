package characters.states;

import characters.Character;

public class PoweredUpState implements CharacterState {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " attacks with increased power!");
    }

    @Override
    public void heal(Character character) {
        System.out.println(character.getName() + " heals with increased power.");
        character.setHealth(character.getHealth() + 4);
    }

    @Override
    public void defend(Character character) {
        System.out.println(character.getName() + " defends with increased power.");
    }
}

