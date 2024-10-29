package game.actions;

import game.characters.Character;

public class HealAction extends GameAction {
    @Override
    public void applyAction(Character character) {
        System.out.println(character.getName() + " is healing!");

    }
}