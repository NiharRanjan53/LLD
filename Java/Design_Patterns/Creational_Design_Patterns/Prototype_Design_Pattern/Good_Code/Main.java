package Good_Code;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        CharacterFactory characterFactory = new CharacterFactory();
        Character c1 = characterFactory.createCharacterWithName("Ravi");
        Character c2 = characterFactory.getCharacterWithNewLevel(5);

        c1.info();
        c2.info();
    }
}
