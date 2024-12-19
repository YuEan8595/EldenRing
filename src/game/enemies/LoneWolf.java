package game.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;

/**
 * BEHOLD, DOG!
 * A lone wolf enemy.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lim Yu Ean
 *
 */
public class LoneWolf extends LandEnemy {

    /**
     * A constructor for LoneWolf class to initialize its name, displayChar, and hit points.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, RandomNumberGenerator.getRandomInt(55, 1470));
    }

    /**
     * Returns the intrinsic weapon of the Lone Wolf.
     * @return the intrinsic weapon of the Lone Wolf.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
