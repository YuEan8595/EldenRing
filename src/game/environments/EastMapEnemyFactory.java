package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GiantCrayfish;
import game.enemies.GiantDog;
import game.enemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents a factory that creates enemy based on the location of the spawning ground
 * which is on the east of the map.
 *  Created by:
 *  @author Lim Yu Ean
 */
public class EastMapEnemyFactory extends EnemyFactory{

    /**
     * A method to spawn a GiantDog with 4% if the location has no actor on it.
     * @param location the location where the GiantDog is spawned
     * */
    @Override
    public void spawnLandEnemy(Location location) {
        if (RandomNumberGenerator.getRandomInt(100) <= 4 && !location.containsAnActor()) {
            location.addActor(new GiantDog());
        }
    }

    /**
     * A method to spawn a GiantCrayfish with 1% if the location has no actor on it.
     * @param location the location where the GiantCrayfish is spawned
     * */
    @Override
    public void spawnWaterEnemy(Location location) {
        if (RandomNumberGenerator.getRandomInt(100) <= 1 && !location.containsAnActor()) {
            location.addActor(new GiantCrayfish());
        }
    }

    /**
     * A method to spawn a SkeletalBandit with 27% if the location has no actor on it.
     * @param location the location where the SkeletalBandit is spawned
     * */
    @Override
    public void spawnBoneEnemy(Location location) {
        if (RandomNumberGenerator.getRandomInt(100) <= 27 && !location.containsAnActor()) {
            location.addActor(new SkeletalBandit());
        }
    }
}
