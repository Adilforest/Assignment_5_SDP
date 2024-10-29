package game.actions;
import game.characters.Character;

public abstract class GameAction {
    public final void executeAction(Character character){
        prepareAction();
        applyAction(character);
        finishAction();
    }

    public void prepareAction(){
        System.out.println("Preparing action...");
    }

    public abstract void applyAction(Character character);

    public void finishAction(){
        System.out.println("Action finished.");
    }
}
