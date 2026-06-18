package characters.visitor;

import characters.Character;

public interface CharacterVisitor {
    void visit(Character character);
}
