package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * SellItemAction is an abstract class that represents the selling of an item.
 * Created by:
 * @author Lai Khairong
 */
public class SellItemAction extends SellAction {

    /**
     * The item to sell
     */
    private final Item item;

    /**
     * Constructor.
     * @param item the item to sell
     */
    public SellItemAction(Item item, int sellCost) {
        super(item, sellCost);
        this.item = item;
    }

    /**
     * Executes the sell action.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        return super.execute(actor, map);
    }
}
