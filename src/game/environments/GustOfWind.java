package game.environments;


import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the GustOfWind, it can spawn LoneWolf and GiantDog.
 * Created by:
 * @author Lim Yu Ean
 */
public class GustOfWind extends SpawningGround {

    /**
     * A constructor for GustOfWind class to initialize its displayChar
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * A method to spawn a LandEnemy at the location
     * @param location the location where the LandEnemy Soldier is spawned
     */
    @Override
    public void spawnEnemy(Location location) {
        enemyFactory.spawnLandEnemy(location);
    }
}
