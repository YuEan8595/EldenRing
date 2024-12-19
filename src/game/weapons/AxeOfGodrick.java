package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellWeaponAction;
import game.traders.Sellable;
import game.utils.Status;

/**
 * A class that represents a weapon named Axe of Godrick.
 * Created by:
 * @author Lai Khairong
 */
public class AxeOfGodrick extends WeaponItem implements Sellable {

    /**
     * SellWeaponAction object to sell the weapon.
     */
    private final SellWeaponAction sellWeaponAction;

    /**
     * A constructor for AxeOfGodrick class to initialize its name, displayChar, damage, and verb
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "hits", 84);
        this.sellWeaponAction = new SellWeaponAction(this, getSellCost());
    }

    /**
     * A tick method to check if the actor carrying this WeaponItem, so that it can be
     * purchased from and sell to the trader
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        this.removeAction(sellWeaponAction);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Exit exit : currentLocation.getExits()){
                Location destination = exit.getDestination();
                if (destination.containsAnActor()){
                    Actor target = destination.getActor();
                    if (target.hasCapability(Status.CAN_TRADE)){
                        this.addAction(sellWeaponAction);
                    }
                }
            }
        }
    }

    /**
     * A method to get the sell cost of the weapon.
     * @return The sell cost of the weapon
     */
    @Override
    public int getSellCost() { return 100; }
}
