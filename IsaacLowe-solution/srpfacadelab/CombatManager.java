package srpfacadelab;

/**
 * Handles all combat interactions for a player: receiving damage, applying
 * armour reduction, triggering parry effects, and the encumbrance damage bonus.
 *
 * SRP: This class has exactly one reason to change — the rules around how
 * damage is calculated and applied need to change.
 */
public class CombatManager {

    private final RpgPlayer player;
    private final InventoryManager inventoryManager;
    private final IGameEngine gameEngine;

    public CombatManager(RpgPlayer player, InventoryManager inventoryManager, IGameEngine gameEngine) {
        this.player = player;
        this.inventoryManager = inventoryManager;
        this.gameEngine = gameEngine;
    }

    /**
     * Applies incoming damage to the player.
     *
     * Rules applied:
     *  1. Play parry effect if the hit would have been fully blocked by armour.
     *  2. NEW FEATURE: Reduce damage by 25% if carrying under 50% of capacity
     *     (un-encumbered players can dodge more effectively).
     *  3. Subtract armour from remaining damage.
     *  4. Apply final damage to player health and play gore effect.
     */
    public void takeDamage(int damage) {
        // 1. Parry: armour absorbs the whole blow
        if (damage < player.getArmour()) {
            gameEngine.playSpecialEffect("parry");
        }

        // 2. NEW FEATURE: 25% damage reduction when carrying under 50% of capacity
        if (inventoryManager.calculateInventoryWeight() < player.getCarryingCapacity() * 0.5) {
            damage = (int) (damage * 0.75);
        }

        // 3. Armour reduces the damage taken
        int damageToDeal = Math.max(0, damage - player.getArmour());

        // 4. Apply to health
        player.setHealth(player.getHealth() - damageToDeal);

        gameEngine.playSpecialEffect("lots_of_gore");
    }
}
