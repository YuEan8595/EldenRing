package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Dog;
import game.enemies.GodrickSoldier;
import game.utils.RandomNumberGenerator;


/**
 * An abstract class that represents a factory that creates enemy based on the
 * location of the spawning ground
 *  Created by:
 *  @author Lim Yu Ean
 */
public abstract class EnemyFactory {

    /**
     * A method to spawn a Land Enemy if the location has no actor on it.
     * @param location the location where the Land Enemy is spawned
     * */
    public abstract void spawnLandEnemy(Location location);

    /**
     * A method to spawn a Water Enemy if the location has no actor on it.
     * @param location the location where the Water Enemy is spawned
     * */
    public abstract void spawnWaterEnemy(Location location);

    /**
     * A method to spawn a Bone Enemy if the location has no actor on it.
     * @param location the location where the Bone Enemy is spawned
     * */
    public abstract void spawnBoneEnemy(Location location);

    /**
     * A method to spawn a Dog with 37% if the location has no actor on it.
     * @param location the location where the Dog is spawned
     * */
    public void spawnDog(Location location){
        if (RandomNumberGenerator.getRandomInt(100) <= 37 && !location.containsAnActor()) {
            location.addActor(new Dog());
        }
    }

     /**
     * A method to spawn a Godrick Soldier with 45% if the location has no actor on it.
     * @param location the location where the Godrick Soldier is spawned
     * */
    public void spawnSoldier(Location location){
        if (RandomNumberGenerator.getRandomInt(100) <= 45 && !location.containsAnActor()) {
            location.addActor(new GodrickSoldier());
        }
    }
}
