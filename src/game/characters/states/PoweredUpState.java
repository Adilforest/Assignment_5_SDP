package game.characters.states;
import game.characters.Character;

public class PoweredUpState implements State{
    @Override
    public void attack(Character character){
        System.out.println(character.getName() + "attacks with extra power!");
    }
}
