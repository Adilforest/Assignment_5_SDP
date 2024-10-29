package actions;

import characters.Character;

public class DefendAction extends GameAction {

    public DefendAction(Character performer) {
        super(performer);
    }

    @Override
    protected void performAction(Character target) {
        performer.defend();
    }
}