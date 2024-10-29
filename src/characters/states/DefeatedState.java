package characters.states;

import characters.Character;

public class DefeatedState implements CharacterState {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " is defeated and cannot attack.");
    }

    @Override
    public void heal(Character character) {
        System.out.println(character.getName() + " is defeated and cannot heal.");
    }

    @Override
    public void defend(Character character) {
        System.out.println(character.getName() + " is defeated and cannot defend.");
    }
}
