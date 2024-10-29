package characters.states;

import characters.Character;

public interface CharacterState {
    void attack(Character character);
    void heal(Character character);
    void defend(Character character);
}