package game.environments;


import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the PuddleOfWater, it can spawn GiantCrab and GiantCrayFish.
 * Created by:
 * @author Lim Yu Ean
 */
public class PuddleOfWater extends SpawningGround {

    /**
     * A constructor for PuddleOfWater class to initialize its displayChar
     */
    public PuddleOfWater() {
        super('~');
    }

    /**
     * A method to spawn a WaterEnemy at the location
     * @param location the location where the WaterEnemy Soldier is spawned
     */
    @Override
    public void spawnEnemy(Location location) {
        enemyFactory.spawnWaterEnemy(location);
    }
}
