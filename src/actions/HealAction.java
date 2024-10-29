package actions;

import characters.Character;

public class HealAction extends GameAction {

    public HealAction(Character performer) {
        super(performer);
    }

    @Override
    protected void performAction(Character target) {
        performer.heal();
    }
}