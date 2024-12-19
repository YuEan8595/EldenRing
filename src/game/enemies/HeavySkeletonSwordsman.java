package game.enemies;


import game.utils.RandomNumberGenerator;
import game.weapons.Grossmesser;

/**
 * A heavy skeleton swordsman enemy.
 * created by:
 * @author Lim Yu Ean
 */
public class HeavySkeletonSwordsman extends BoneEnemy{

    /**
     * A constructor for HeavySkeletonSwordsman class to initialize its name, displayChar, and hit points.
     */
    public HeavySkeletonSwordsman() {
        super("Heavy Skeleton Swordsman", 'q', 153, RandomNumberGenerator.getRandomInt(35, 892));
        this.addWeaponToInventory(new Grossmesser());
    }
}
