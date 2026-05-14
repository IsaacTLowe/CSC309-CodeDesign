package srpfacadelab;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple concrete implementation of IGameEngine.
 * Stores enemies and players, and provides basic effect rendering.
 *
 * Note: addPlayer now accepts a GameFacade. The engine only needs to know
 * how to find enemies near a given RpgPlayer (used internally by ItemUseHandler),
 * so getEnemiesNear still takes an RpgPlayer — the raw state object.
 */
public class SimpleGameEngine implements IGameEngine {

    private final List<IEnemy> enemies;
    private final List<RpgPlayer> players;

    public SimpleGameEngine() {
        enemies = new ArrayList<>();
        enemies.add(new SimpleEnemy("Creeper"));
        enemies.add(new SimpleEnemy("Zombie"));
        enemies.add(new SimpleEnemy("Golem"));
        players = new ArrayList<>();
    }

    /** Register a player's underlying RpgPlayer so the engine can track positions. */
    public void addPlayer(RpgPlayer player) {
        players.add(player);
    }

    @Override
    public void playSpecialEffect(String effectName) {
        if (effectName.equals("green_swirly"))
            System.out.println("[Special Effect]: Green Swirl!!!");

        else if (effectName.equals("blue_swirly"))
            System.out.println("[Special Effect]: Blue Swirly — a legendary find!!!");

        else if (effectName.equals("cool_swirly_particles"))
            System.out.println("[Special Effect]: Fancy Swirly Particles in the air!!!");

        else if (effectName.equals("parry"))
            System.out.println("[Special Effect]: Nop! Try better next time!");

        else if (effectName.equals("lots_of_gore"))
            System.out.println("[Special Effect]: Ouch!! That hurts!!");
    }

    @Override
    public List<IEnemy> getEnemiesNear(RpgPlayer player) {
        // Simplest logic: first registered player gets all enemies; others get none.
        if (!players.isEmpty() && players.get(0).equals(player))
            return enemies;

        return null;
    }
}
