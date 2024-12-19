package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.*;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents a factory that creates enemy based on the location of the spawning ground
 * which is on the west of the map.
 *  Created by:
 *  @author Lim Yu Ean
 */
public class WestMapEnemyFactory extends EnemyFactory{

    /**
     * A method to spawn a LoneWolf with 33% if the location has no actor on it.
     * @param location the location where the LoneWolf is spawned
     * */
    @Override
    public void spawnLandEnemy(Location location) {
        if (RandomNumberGenerator.getRandomInt(100) <= 33 && !location.containsAnActor()) {
            location.addActor(new LoneWolf());
        }
    }

    /**
     * A method to spawn a GiantCrab with 2% if the location has no actor on it.
     * @param location the location where the GiantCrab is spawned
     * */
    @Override
    public void spawnWaterEnemy(Location location) {
        if (RandomNumberGenerator.getRandomInt(100) <= 2 && !location.containsAnActor()) {
            location.addActor(new GiantCrab());
        }
    }

    /**
     * A method to spawn a HeavySkeletonSwordsman with 27% if the location has no actor on it.
     * @param location the location where the HeavySkeletonSwordsman is spawned
     * */
    @Override
    public void spawnBoneEnemy(Location location) {
        if (RandomNumberGenerator.getRandomInt(100) <= 27 && !location.containsAnActor()) {
            location.addActor(new HeavySkeletonSwordsman());
        }
    }
}
