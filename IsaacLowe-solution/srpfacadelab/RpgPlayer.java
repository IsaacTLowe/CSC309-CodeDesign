package srpfacadelab;

/**
 * Holds the core state of an RPG player: health, armour, and carrying capacity.
 *
 * SRP: This class has exactly one reason to change — the player's fundamental
 * attributes (health, armour, carrying capacity) need to be redefined.
 * All game logic (picking up items, taking damage, using items) has been
 * extracted into dedicated manager classes.
 */
public class RpgPlayer {

    public static final int MAX_CARRYING_CAPACITY = 1000;

    private int health;
    private int maxHealth;
    private int armour;
    private int carryingCapacity;

    public RpgPlayer() {
        this.carryingCapacity = MAX_CARRYING_CAPACITY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
}
