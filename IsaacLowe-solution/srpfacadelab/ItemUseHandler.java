package srpfacadelab;

import java.util.List;

/**
 * Handles the logic of a player actively using an item from their inventory.
 *
 * SRP: This class has exactly one reason to change — the rules for what
 * happens when a player uses an item need to change.
 */
public class ItemUseHandler {

    private final RpgPlayer player;
    private final IGameEngine gameEngine;

    public ItemUseHandler(RpgPlayer player, IGameEngine gameEngine) {
        this.player = player;
        this.gameEngine = gameEngine;
    }

    /**
     * Executes the effect of using the given item.
     * Currently: Stink Bomb deals 100 damage to all nearby enemies.
     */
    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb")) {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);

            if (enemies != null) {
                for (IEnemy enemy : enemies) {
                    enemy.takeDamage(100);
                }
            }
        }
    }
}
