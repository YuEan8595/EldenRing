package game.environments;

import edu.monash.fit2099.engine.positions.Location;


/**
 * A class that represents the Barrack, it can spawn Godrick Soldier.
 * Created by:
 * author Lim Yu Ean
 */
public class Barrack extends SpawningGround {

    /**
     * Constructor.
     *
     */
    public Barrack() {
        super('B');
        // since Barrack the same enemy in the east and west map, we just initialise
        // to the west map enemy factory
        this.enemyFactory = new WestMapEnemyFactory();
    }

    /**
     * A method to spawn a Godrick Soldier at the location
     * @param location the location where the Godrick Soldier is spawned
     */
    @Override
    public void spawnEnemy(Location location) {
        enemyFactory.spawnSoldier(location);
    }
}
