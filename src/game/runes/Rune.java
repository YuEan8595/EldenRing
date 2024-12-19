package game.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RetrieveRuneAction;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.utils.Status;

/**
 * Rune class is a class that represents a rune item
 * created by:
 * @author Tan Jian Kai
 * modified by: Lim Yu Ean
 */
public class Rune extends Item implements Resettable {

    /**
     * The amount of rune
     */
    private int runeAmount;


    /**
     * A constructor for Rune class to initialize its name, displayChar, and hit points
     * @param runeAmount the amount of rune
     */
    public Rune(int runeAmount){
        super("Runes", '$', true);
        this.runeAmount = runeAmount;
    }

    /**
     * A method to get the amount of rune
     * @return the amount of rune
     */
    public int getRuneAmount(){
        return this.runeAmount;
    }

    /**
     * A method to set the amount of rune
     * @param runeAmount the amount of rune
     */
    public void setRuneAmount(int runeAmount){
        this.runeAmount = runeAmount;
    }

    /**
     * A method to get the retrieve action
     * @param actor the actor to retrieve the rune
     * @return the retrieve action
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) { return new RetrieveRuneAction(this); }

    /**
     * A method to remove the rune from the location and reset the death count
     * @param currentLocation the current location
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.DESPAWNABLE) && ResetManager.getInstance().getDeathCount() == 2) {
            ResetManager.getInstance().resetDeathCount();
            currentLocation.removeItem(this);
        }
    }

    /**
     * A method to reset the rune
     */
    @Override
    public void reset() { this.addCapability(Status.DESPAWNABLE); }
}
