package srpfacadelab;

public class SimpleEnemy implements IEnemy {

    private int health;
    private String name;

    public SimpleEnemy(String name){
        this.name = name;
    }

    @Override
    public void takeDamage(int amount){
        //Meant to be the simplest.
        //Allowing negative health
        health -= amount;
    }

    public String getName(){
        return name;
    }

}
