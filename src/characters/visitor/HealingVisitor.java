package characters.visitor;

public class HealingVisitor implements CharacterVisitor {
    private int healAmount;

    public HealingVisitor(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void visit(Character character) {

    }
}
