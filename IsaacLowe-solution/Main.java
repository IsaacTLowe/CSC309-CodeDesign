import srpfacadelab.*;

/**
 * Entry point demonstrating the Facade pattern.
 *
 * Client code only ever talks to GameFacade — it has no knowledge of
 * RpgPlayer, InventoryManager, CombatManager, ItemPickupHandler, or
 * ItemUseHandler. The facade provides the single central access point
 * to all game functionality.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Create the game engine (the only infrastructure piece clients touch directly)
        SimpleGameEngine gameEngine = new SimpleGameEngine();

        // 2. Create players entirely through the Facade — no subsystem classes visible
        GameFacade player1 = new GameFacade(gameEngine);
        GameFacade player2 = new GameFacade(gameEngine);

        player1.setMaxHealth(500);
        player1.setHealth(500);

        player2.setMaxHealth(300);
        player2.setHealth(300);

        // --- Demo: pick up a normal rare item ---
        Item rareHelmet = new Item(1, "Helm of Doom", 0, 50, 10, false, true);
        System.out.println("Picking up rare helmet: " + player1.pickUpItem(rareHelmet));

        // --- Demo: pick up a rare AND unique item (triggers blue_swirly) ---
        Item legendaryItem = new Item(2, "Excalibur", 0, 100, 20, true, true);
        System.out.println("Picking up legendary sword: " + player1.pickUpItem(legendaryItem));

        // --- Demo: unique item can't be picked up twice ---
        System.out.println("Picking up same legendary sword again: " + player1.pickUpItem(legendaryItem));

        // --- Demo: healing item consumed immediately ---
        Item bigPotion = new Item(3, "Mega Potion", 600, 0, 1, false, false);
        player1.setHealth(100);
        System.out.println("Health before potion: " + player1.getHealth());
        player1.pickUpItem(bigPotion);
        System.out.println("Health after potion (capped at max): " + player1.getHealth());

        // --- Demo: take damage (player is carrying 30lbs out of 1000 → under 50% → 25% damage reduction) ---
        System.out.println("\nPlayer armour: " + player1.getArmour());
        System.out.println("Carry weight: " + player1.getCurrentCarryWeight() + " / " + player1.getCarryingCapacity());
        System.out.println("Health before hit: " + player1.getHealth());
        player1.takeDamage(200);
        System.out.println("Health after 200 damage (with 25% dodge reduction + armour): " + player1.getHealth());

        // --- Demo: use a Stink Bomb ---
        Item stinkBomb = new Item(4, "Stink Bomb", 0, 0, 1, false, false);
        player1.pickUpItem(stinkBomb); // pick it up first so player has it
        player1.useItem(stinkBomb);    // use it
    }
}
