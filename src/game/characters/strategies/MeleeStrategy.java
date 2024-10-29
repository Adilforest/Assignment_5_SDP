package game.characters.strategies;
import game.characters.Character;

public class MeleeStrategy implements FightingStrategy{
    @Override
    public void fight(Character character){
        System.out.println(character.getName() + " fights in close combat!");
    }
}
