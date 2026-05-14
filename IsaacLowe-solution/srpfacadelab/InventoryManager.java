package srpfacadelab;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the player's inventory: what they're carrying, how much it weighs,
 * whether a unique item is already held, and how armour is totalled from gear.
 *
 * SRP: This class has exactly one reason to change — the rules around how
 * inventory works (weight limits, uniqueness, armour calculation) need to change.
 */
public class InventoryManager {

    private final RpgPlayer player;
    private final List<Item> inventory;

    public InventoryManager(RpgPlayer player) {
        this.player = player;
        this.inventory = new ArrayList<>();
    }

    /**
     * Attempts to add an item to the inventory.
     * Returns false if the player is over-encumbered or already holds a unique copy.
     */
    public boolean addItem(Item item) {
        if (calculateInventoryWeight() + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && itemAlreadyInInventory(item))
            return false;

        inventory.add(item);
        recalculateArmour();
        return true;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int calculateInventoryWeight() {
        int sum = 0;
        for (Item i : inventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    public boolean itemAlreadyInInventory(Item item) {
        for (Item i : inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    /**
     * Recalculates and updates the player's total armour from all equipped items.
     * Called whenever the inventory changes.
     */
    private void recalculateArmour() {
        int totalArmour = 0;
        for (Item i : inventory) {
            totalArmour += i.getArmour();
        }
        player.setArmour(totalArmour);
    }
}
