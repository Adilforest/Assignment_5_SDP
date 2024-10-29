package game.characters.states;
import game.characters.Character;

public class DefeatedState implements State {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " is defeated and cannot attack.");
    }
}