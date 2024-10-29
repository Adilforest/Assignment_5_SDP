package game.characters.strategies;
import game.characters.Character;

public class MagicStrategy implements FightingStrategy{
    @Override
    public void fight(Character character){
        System.out.println(character.getName() + " casts a powerful spell!");
    }
}
