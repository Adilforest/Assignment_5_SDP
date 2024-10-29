package game.characters.states;

import game.characters.Character;

public class NormalState implements State{
    @Override
    public void attack(Character character){
        System.out.println(character.getName() + " attacks normally.");
    }

}
