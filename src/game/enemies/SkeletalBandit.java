package game.enemies;


import game.utils.RandomNumberGenerator;
import game.weapons.Scimitar;

/**
 * A skeletal bandit enemy.
 * created by:
 * @author Lai Khairong
 */
public class SkeletalBandit extends BoneEnemy {

    /**
     * A constructor for SkeletalBandit class to initialize its name, displayChar, and hit points.
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, RandomNumberGenerator.getRandomInt(35, 892));
        this.addWeaponToInventory(new Scimitar());
    }
}
