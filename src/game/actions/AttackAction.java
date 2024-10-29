package game.actions;
import game.characters.Character;

public class AttackAction extends GameAction {
    @Override
    public void applyAction(Character character) {
        System.out.println(character.getName() + " is attacking!");
        character.attack();
    }
}