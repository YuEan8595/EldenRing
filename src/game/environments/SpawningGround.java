package game.environments;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An abstract class that represents a spawning ground.
 * It is a type of ground that can spawn enemy on it.
 * Created by:
 * @author Lim Yu Ean
 */
public abstract class SpawningGround extends Ground {

    /**
     * EnemyFactory to create enemy based on east or west of GameMap
     */
    protected EnemyFactory enemyFactory;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * A method to spawn an Enemy at the location
     * @param location the location where the Enemy is spawned
     */
    public abstract void spawnEnemy(Location location);

    /**
     * A method to spawn the respective enemy on different type of spawning ground
     * depends on the location of the spawning ground which is either on the west or east of the map
     * if the location has no actor on it.
     * @param location the location where the Enemy is spawned
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        if (enemyFactory == null) {
            GameMap map = location.map();
            int max = map.getXRange().max();
            if (location.x() < (max / 2)) {
                enemyFactory = new WestMapEnemyFactory();
            }
            else {
                enemyFactory = new EastMapEnemyFactory();
            }
        }
        this.spawnEnemy(location);
    }
}
