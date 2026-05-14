package srpfacadelab;

import java.util.ArrayList;
import java.util.List;

public class SimpleGameEngine implements IGameEngine {

    private List<IEnemy> enemies;
    private List<RpgPlayer> players;

    public SimpleGameEngine(){
        enemies = new ArrayList<IEnemy>();
        enemies.add(new SimpleEnemy("Creeper"));
        enemies.add(new SimpleEnemy("Zombie"));
        enemies.add(new SimpleEnemy("Golem"));
        players = new ArrayList<RpgPlayer>();
    }

    public void addPlayer(RpgPlayer player){
        players.add(player);
    }

    @Override
    public void playSpecialEffect(String effectName) {
        if (effectName.equals("green_swirly"))
            System.out.println("[Special Effect]: Green Swirl!!!");

        else if (effectName.equals("cool_swirly_particles"))
            System.out.println("[Special Effect]: Fancy Swirly Particles in the air!!!");

        else if (effectName.equals("parry"))
            System.out.println("[Special Effect]: Nop! Try better next time!");
            
        else if (effectName.equals("lots_of_gore"))
            System.out.println("[Special Effect]: Ouch!! That hurts!!");

    }

    @Override
    public List<IEnemy> getEnemiesNear(RpgPlayer player) {
        //Meant to be the simplest logic
        if (players.get(0).equals(player))
                return enemies; //First player got all enemies

        //Other players got none
        return null;
    }
}
