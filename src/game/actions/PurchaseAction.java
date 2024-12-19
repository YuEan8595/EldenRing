package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action to purchase a weapon from trader.
 * Created by:
 * @author Lim Yu Ean
 */
public abstract class PurchaseAction extends Action {
    /**
     * The item that is to be purchased
     */
    private Item item;
    /**
     * The cost of the item
     */
    private int purchaseCost;

    /**
     * Constructor.
     *
     * @param item the item to purchase
     * @param purchaseCost the cost of the item
     */
    public PurchaseAction(Item item, int purchaseCost){
        this.item = item;
        this.purchaseCost = purchaseCost;
    }

    /**
     * Executes the purchase action.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Returns a description of the action suitable for the menu.
     * @param actor the actor performing the action
     * @return a string, e.g. "Player purchases a sword for 10 runes"
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " purchases " + item + " for "  + purchaseCost + " runes";
    }
}
