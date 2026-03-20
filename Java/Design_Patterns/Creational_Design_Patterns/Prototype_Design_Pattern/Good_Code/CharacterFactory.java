package Good_Code;

public class CharacterFactory {
    private Character prototypeCharacter;

    public CharacterFactory() {
        this.prototypeCharacter = new Character("x", 0, 0, 0);
    }

    public Character createCharacterWithName(String name) throws CloneNotSupportedException {
        Character clonedCharacter = this.prototypeCharacter.clone();

        return new Character(name, clonedCharacter.getHealth(), clonedCharacter.getAttackPower(),
                clonedCharacter.getLevel());

    }

    public Character getCharacterWithNewLevel(int level) throws CloneNotSupportedException {
        Character clonedCharacter = this.prototypeCharacter.clone();
        return new Character(clonedCharacter.getName(), clonedCharacter.getHealth(), clonedCharacter.getAttackPower(),
                level);
    }
}
