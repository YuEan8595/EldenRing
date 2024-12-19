package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.ConsumableItem;

/**
 * Class representing the action to drop the consumable item.
 * Created by:
 * @author Lai Khairong
 */
public class DropConsumableItemAction extends DropItemAction {

    /**
     * The consumable item to be dropped.
     */
    private final ConsumableItem consumableItem;

    /**
     * Constructor that initializes the consumable item to be dropped.
     * @param consumableItem The Golden Rune to be dropped.
     */
    public DropConsumableItemAction(ConsumableItem consumableItem) {
        super(consumableItem);
        this.consumableItem = consumableItem;
    }

    /**
     * Method to override the execute method to remove the ConsumeAction from the actor's action list.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.removeConsumeAction();
        return super.execute(actor, map);
    }
}
