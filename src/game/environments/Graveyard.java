package game.environments;


import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents the Graveyard, it can spawn HeavySkeletonSwordsman and Skeletal Bandit.
 * Created by:
 * @author Lim Yu Ean
 */
public class Graveyard extends SpawningGround {

    /**
     * A constructor for Graveyard class to initialize its displayChar
     */
    public Graveyard() {
        super('n');
    }

    /**
     * A method to spawn an BoneEnemy at the location
     * @param location the location where the BoneEnemy is spawned
     */
    @Override
    public void spawnEnemy(Location location) {
    	enemyFactory.spawnBoneEnemy(location);
    }
}
