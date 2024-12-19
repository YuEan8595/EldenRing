package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.RuneManager;

/**
 * SellAction is an abstract class that represents the selling of an item.
 * Created by:
 * @author Lim Yu Ean
 */
public abstract class SellAction extends Action {
    /**
     * The item to sell
     */
    private final Item item;

    /**
     * The selling price
     */
    private final int sellCost;

    /**
     * Constructor.
     * @param item the item to sell
     */
    public SellAction(Item item, int sellCost) {
        this.item = item;
        this.sellCost =  sellCost;
    }

    /**
     * Executes the sell action.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().addRuneAmount(sellCost);
        return actor + " has sold " + item;
    }

    /**
     * Returns a description of the action suitable for the menu.
     * @param actor the actor performing the action
     * @return a string, e.g. "Player sells a sword for 10 runes"
     */
    @Override
    public String menuDescription(Actor actor) { return actor + " sells " + item + " for " + sellCost + " runes"; }
}
