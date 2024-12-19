package game.characters;

import game.weapons.Club;

/**
 * A class that represents a Wretch and starts with a Club.
 * Created by:
 * @author Lim Yu Ean
 */
public class Wretch extends Player{

    /**
     * A constructor for Wretch class to initialize its name, displayChar, and hit points
     */
    public Wretch() {
        super("Tarnished", '@', 414);
        this.addWeaponToInventory(new Club());
    }
}
