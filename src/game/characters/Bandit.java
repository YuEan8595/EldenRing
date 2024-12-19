package game.characters;

import game.weapons.GreatKnife;

/**
 * A class that represents a Bandit and starts with a Great Knife.
 * Created by:
 * @author Lim Yu Ean
 */
public class Bandit extends Player{

    /**
     * A constructor for Bandit class to initialize its name, displayChar, and hit points
     */
    public Bandit() {
        super("Tarnished", '@', 414);
        this.addWeaponToInventory(new GreatKnife());
    }
}
