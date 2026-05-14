package srpfacadelab;

/**
 * Handles the full logic of a player picking up an item:
 *   - Checking weight and uniqueness (via InventoryManager)
 *   - Applying healing immediately if the item is consumable
 *   - Triggering visual effects for rare/unique items  ← NEW FEATURE
 *   - Adding the item to the inventory if it's gear
 *
 * SRP: This class has exactly one reason to change — the rules for what
 * happens when a player picks up an item need to change.
 */
public class ItemPickupHandler {

    private final RpgPlayer player;
    private final InventoryManager inventoryManager;
    private final IGameEngine gameEngine;

    public ItemPickupHandler(RpgPlayer player, InventoryManager inventoryManager, IGameEngine gameEngine) {
        this.player = player;
        this.inventoryManager = inventoryManager;
        this.gameEngine = gameEngine;
    }

    /**
     * Attempts to pick up an item. Returns true if successful.
     *
     * Rules applied in order:
     *  1. Reject if over carry weight.
     *  2. Reject if unique item already held.
     *  3. NEW: Show blue_swirly if item is both rare AND unique.
     *  4. Consume healing items immediately (don't add to inventory).
     *  5. Show cool particles for rare gear, then add to inventory.
     */
    public boolean pickUpItem(Item item) {
        // 1 & 2: Delegate capacity/uniqueness check to inventory
        int currentWeight = inventoryManager.calculateInventoryWeight();
        if (currentWeight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && inventoryManager.itemAlreadyInInventory(item))
            return false;

        // 3. NEW FEATURE: Super rare items (rare AND unique) get a special effect
        if (item.isRare() && item.isUnique()) {
            gameEngine.playSpecialEffect("blue_swirly");
        }

        // 4. Consumable healing items: apply heal, don't store in inventory
        if (item.getHeal() > 0) {
            int newHealth = player.getHealth() + item.getHeal();
            player.setHealth(Math.min(newHealth, player.getMaxHealth()));

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        // 5. Rare gear gets a visual flourish before being stored
        if (item.isRare()) {
            gameEngine.playSpecialEffect("cool_swirly_particles");
        }

        inventoryManager.addItem(item);
        return true;
    }
}
