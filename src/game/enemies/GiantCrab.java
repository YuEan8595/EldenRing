package game.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.weapons.CrabSlam;

/**
 * A giant crab enemy.
 * created by:
 * @author Lim Yu Ean
 */
public class GiantCrab extends WaterEnemy {

    /**
     * A constructor for GiantCrab class to initialize its name, displayChar, and hit points.
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407, RandomNumberGenerator.getRandomInt(318, 4961));
        this.addWeaponToInventory(new CrabSlam());
    }

    /**
     * Returns the intrinsic weapon of the Giant Crab.
     * @return the intrinsic weapon of the Giant Crab.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slam", 90);
    }
}
