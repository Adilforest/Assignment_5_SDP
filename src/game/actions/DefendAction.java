package game.actions;
import game.characters.Character;;

public class DefendAction extends GameAction {
    @Override
    public void applyAction(Character character) {
        System.out.println(character.getName() + " is defending!");
    }
}