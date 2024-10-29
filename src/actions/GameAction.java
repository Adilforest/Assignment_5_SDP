package actions;

import characters.Character;

public abstract class GameAction {
    protected Character performer;

    public GameAction(Character performer) {
        this.performer = performer;
    }

    public final void executeAction(Character target) {
        prepareAction();
        performAction(target);
        finishAction();
    }

    protected abstract void performAction(Character target);

    private void prepareAction() {
        System.out.println(performer.getName() + " is preparing the action...");
    }

    private void finishAction() {
        System.out.println("Action finished.");
    }
}