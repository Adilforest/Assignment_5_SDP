package characters;

import characters.strategies.AttackStrategy;

public class Character {
    private String name;
    private int health;
    private AttackStrategy attackStrategy;

    public Character(String name) {
        this.name = name;
        this.health = 20;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void attack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
        } else {
            System.out.println(name + " has no attack strategy!");
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Health is now " + health);
    }

    public void heal() {
        int healAmount = 5;
        health += healAmount;
        System.out.println(name + " heals for " + healAmount + ". Health is now " + health);
    }

    public void defend() {
        System.out.println(name + " is defending!");
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }
}
