package characters.states;

import characters.Character;

public class NormalState implements CharacterState {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " attacks normally!");

    }

    @Override
    public void heal(Character character) {
        System.out.println(character.getName() + " heals normally.");
        character.setHealth(character.getHealth() + 2);
    }

    @Override
    public void defend(Character character) {
        System.out.println(character.getName() + " defends normally.");
    }
}
