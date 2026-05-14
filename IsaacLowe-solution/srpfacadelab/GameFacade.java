package srpfacadelab;

/**
 * Facade for the RPG game subsystem.
 *
 * FACADE PATTERN: Client code should only ever interact with this class.
 * GameFacade hides the complexity of the underlying subsystem — RpgPlayer,
 * InventoryManager, CombatManager, ItemPickupHandler, and ItemUseHandler —
 * behind a single, simple interface.
 *
 * If the internal design changes (e.g. a new manager is introduced, or
 * InventoryManager is split further), client code is completely insulated
 * from that change because it only talks to this facade.
 *
 * Usage:
 *   GameFacade game = new GameFacade(gameEngine);
 *   game.pickUpItem(sword);
 *   game.takeDamage(40);
 *   game.useItem(stinkBomb);
 */
public class GameFacade {

    // --- Subsystem objects (hidden from clients) ---
    private final RpgPlayer player;
    private final InventoryManager inventoryManager;
    private final CombatManager combatManager;
    private final ItemPickupHandler itemPickupHandler;
    private final ItemUseHandler itemUseHandler;

    /**
     * Wires the full subsystem together.
     * Clients only need to provide the game engine.
     */
    public GameFacade(IGameEngine gameEngine) {
        this.player           = new RpgPlayer();
        this.inventoryManager = new InventoryManager(player);
        this.combatManager    = new CombatManager(player, inventoryManager, gameEngine);
        this.itemPickupHandler = new ItemPickupHandler(player, inventoryManager, gameEngine);
        this.itemUseHandler   = new ItemUseHandler(player, gameEngine);
    }

    // --- Facade public interface ---

    /**
     * Attempts to pick up an item. Returns true if successful.
     * Delegates to ItemPickupHandler which handles weight, uniqueness,
     * healing, effects, and inventory storage.
     */
    public boolean pickUpItem(Item item) {
        return itemPickupHandler.pickUpItem(item);
    }

    /**
     * Applies damage to the player, factoring in armour and encumbrance.
     * Delegates to CombatManager.
     */
    public void takeDamage(int damage) {
        combatManager.takeDamage(damage);
    }

    /**
     * Uses an item (e.g. throws a Stink Bomb).
     * Delegates to ItemUseHandler.
     */
    public void useItem(Item item) {
        itemUseHandler.useItem(item);
    }

    // --- Convenience accessors so clients can query player state ---

    public int getHealth() {
        return player.getHealth();
    }

    public int getMaxHealth() {
        return player.getMaxHealth();
    }

    public void setMaxHealth(int maxHealth) {
        player.setMaxHealth(maxHealth);
    }

    public void setHealth(int health) {
        player.setHealth(health);
    }

    public int getArmour() {
        return player.getArmour();
    }

    public int getCarryingCapacity() {
        return player.getCarryingCapacity();
    }

    public int getCurrentCarryWeight() {
        return inventoryManager.calculateInventoryWeight();
    }
}
