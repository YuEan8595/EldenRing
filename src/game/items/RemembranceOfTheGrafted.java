package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellItemAction;
import game.traders.Sellable;
import game.utils.Status;

/**
 * Class representing the Remembrance of the Grafted item.
 * Created by:
 * @author Lai Khairong
 */
public class RemembranceOfTheGrafted extends Item implements Sellable {

    /**
     * SellAction object to sell the item.
     */
    private final SellItemAction sellItemAction;

    /**
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        this.sellItemAction = new SellItemAction(this, getSellCost());
    }

    /**
     * Tick method to check if the player is hostile to the enemy.
     * @param currentLocation The location of the actor
     * @param actor The actor that is holding the item
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        this.removeAction(sellItemAction);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Exit exit : currentLocation.getExits()){
                Location destination = exit.getDestination();
                if (destination.containsAnActor()){
                    Actor target = destination.getActor();
                    if (target.hasCapability(Status.CAN_TRADE)){
                        this.addAction(sellItemAction);
                    }
                }
            }
        }
    }

    /**
     * Method to get the sell cost of the item.
     * @return The sell cost of the item
     */
    @Override
    public int getSellCost() { return 20000; }
}
