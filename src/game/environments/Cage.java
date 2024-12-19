package game.environments;

import edu.monash.fit2099.engine.positions.Location;


/**
 * A class that represents the Cage, it can spawn Dog.
 * Created by:
 * author Lim Yu Ean
 */
public class Cage extends SpawningGround {

    /**
     * Constructor.
     *
     */
    public Cage() {
        super('<');
        // since Barrack the same enemy in the east and west map, we just initialise
        // to the west map enemy factory
        this.enemyFactory = new WestMapEnemyFactory();
    }

    /**
     * A method to spawn a Dog at the location
     * @param location the location where the Dog Soldier is spawned
     */
    @Override
    public void spawnEnemy(Location location) {
        enemyFactory.spawnDog(location);
    }
}
