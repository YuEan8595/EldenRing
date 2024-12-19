package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import game.utils.Status;

/**
 * Class representing a Poison Apple.
 * Created by:
 * @author Lai Khairong
 */
public class PoisonApple extends ConsumableItem {

    /**
     * Constructor.
     */
    public PoisonApple() { super("Poison Apple", '%', true); }

    /**
     * Returns a string describing the action of consuming this item.
     * @param actor The actor performing the action.
     * @return a string describing the result of the action
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.addCapability(Status.POISONED);
        return actor + " consumed " + this + " and is now poisoned.";
    }
}
