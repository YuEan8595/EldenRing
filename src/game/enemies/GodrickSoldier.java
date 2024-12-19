package game.enemies;

import game.utils.RandomNumberGenerator;
import game.weapons.Club;

/**
 * A class that represents the Godrick Soldier, it is a subclass of GodrickEnemy.
 * Created by:
 * author Lim Yu Ean
 */
public class GodrickSoldier extends GodrickEnemy{

    /**
     * A constructor for Enemy class to initialize its name, displayChar, and hit points
     *
     */
    public GodrickSoldier() {
        super("GodrickSoldier", 'p', 198, RandomNumberGenerator.getRandomInt(38, 70));
        this.addWeaponToInventory(new Club());
    }
}
