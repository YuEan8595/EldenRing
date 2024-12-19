package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.ConsumableItem;

/**
 * Action for consuming a consumable item.
 * Created by:
 * @author Lai Khairong
 */
public class ConsumeAction extends Action {

    /**
     * The consumable item to be consumed.
     */
    private final ConsumableItem consumableItem;

    /**
     * Constructor.
     * @param consumableItem the consumable item to be consumed
     */
    public ConsumeAction(ConsumableItem consumableItem) { this.consumableItem = consumableItem; }

    /**
     * Execute the action of consuming the consumable item.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) { return consumableItem.consume(actor); }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumableItem;
    }
}
