package game.characters.strategies;
import game.characters.Character;

public class RangedStrategy implements FightingStrategy{
    @Override
    public void fight(Character character){
        System.out.println((character.getName() + " attacks from a distance!"));
    }
}
