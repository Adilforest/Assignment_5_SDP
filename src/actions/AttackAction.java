package actions;

import characters.Character;

public class AttackAction extends GameAction {

    public AttackAction(Character performer) {
        super(performer);
    }

    @Override
    protected void performAction(Character target) {
        performer.attack(target);
    }
}