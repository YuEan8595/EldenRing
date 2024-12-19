package game.characters;

import game.weapons.Uchigatana;

/**
 * A class that represents a Samurai and starts with a Uchigatana.
 * Created by:
 * @author Lim Yu Ean
 */
public class Samurai extends Player{

    /**
     * A constructor for Samurai class to initialize its name, displayChar, and hit points
     */
    public Samurai() {
        super("Tarnished", '@', 455);
        this.addWeaponToInventory(new Uchigatana());
    }
}
