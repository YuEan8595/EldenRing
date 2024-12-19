package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.actions.SellWeaponAction;
import game.traders.Purchasable;
import game.traders.Sellable;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * This weapon is purchasable from and sellable to the trader
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lim Yu Ean
 *
 */
public class Club extends WeaponItem implements Purchasable, Sellable {

    /**
     * The action to sell the weapon
     */
    private final SellAction sellAction;

    /**
     * A Constructor for Club class to initialize its name, displayChar, damage, verb, and hit rate
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.sellAction = new SellWeaponAction(this, getSellCost());
    }

    /**
     * A tick method to check if the actor carrying this WeaponItem, so that it can be
     * purchased from and sell to the trader
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        this.removeAction(sellAction);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Exit exit : currentLocation.getExits()){
                Location destination = exit.getDestination();
                if (destination.containsAnActor()){
                    Actor target = destination.getActor();
                    if (target.hasCapability(Status.CAN_TRADE)){
                        this.addAction(sellAction);
                    }
                }
            }
        }
    }

    /**
     * A getter for the purchase price of the weapon
     * @return the purchase price of the weapon
     */
    @Override
    public int getPurchaseCost(){ return 600; }

    /**
     * A getter for the sell price of the weapon
     * @return the sell price of the weapon
     */
    @Override
    public int getSellCost(){ return 100; }
}
