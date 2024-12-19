package game.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.weapons.DogSlam;


/**
 * Class representing a Giant Dog.
 * created by:
 * @author Lai Khairong
 */
public class GiantDog extends LandEnemy {

    /**
     * A constructor for GiantDog class to initialize its name, displayChar, and hit points.
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, RandomNumberGenerator.getRandomInt(313, 1808));
        this.addWeaponToInventory(new DogSlam());
    }

    /**
     * Returns the intrinsic weapon of the Giant Dog.
     * @return the intrinsic weapon of the Giant Dog.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams", 90);
    }
}
