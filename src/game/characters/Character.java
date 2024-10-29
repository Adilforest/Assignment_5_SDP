package game.characters;

import game.characters.states.State;
import game.characters.strategies.FightingStrategy;

public class Character {
    private String name;
    private int health;
    private State state;
    private FightingStrategy strategy;


    public Character(String name, int health, State initialState, FightingStrategy initialStrategy){
        this.name = name;
        this.health = health;
        this.state = initialState;
        this.strategy = initialStrategy;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setStrategy(FightingStrategy strategy){
        this.strategy = strategy;
    }

    public void attack(){
        state.attack(this);
    }

    public void fight(){
        strategy.fight(this);
    }

    public void takeDamage(int damage){
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health is now " + health);
    }

    public int getHealth(){
        return health;
    }

    public String getName(){
        return name;
    }

}
