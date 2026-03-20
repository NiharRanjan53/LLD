package Good_Code;

public class Character implements Cloneable {
    private String name;
    private int health;
    private int attackPower;
    private int level;

    public Character(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone();
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public int getLevel() {
        return this.level;
    }

    public void info() {
        System.out.println(
                "Name " + this.name + " Health: " + health + " Attack Power: " + attackPower + " Level: " + level);
    }

}
