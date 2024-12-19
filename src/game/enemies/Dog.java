package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents the Dog, it is a subclass of GodrickEnemy.
 * Created by:
 * author Lim Yu Ean
 */
public class Dog extends GodrickEnemy{

    /**
     * A constructor for Enemy class to initialize its name, displayChar, and hit points
     *
     */
    public Dog() {
        super("Dog", 'a', 104, RandomNumberGenerator.getRandomInt(52, 1390));
    }

    /**
     * Returns the intrinsic weapon of the Dog.
     * @return the intrinsic weapon of the Dog.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }
}
