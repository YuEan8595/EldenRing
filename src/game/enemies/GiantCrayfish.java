package game.enemies;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.weapons.FishSlam;

/**
 * A giant crayfish enemy.
 * created by:
 * @author Lai Khairong
 */
public class GiantCrayfish extends WaterEnemy {

    /**
     * A constructor for GiantCrayfish class to initialize its name, displayChar, and hit points.
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, RandomNumberGenerator.getRandomInt(500, 2374));
        this.addWeaponToInventory(new FishSlam());
    }

    /**
     * Returns the intrinsic weapon of the Giant Crayfish.
     * @return the intrinsic weapon of the Giant Crayfish.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }
}
