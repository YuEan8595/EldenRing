package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.ConsumableItem;

/**
 * Class representing the action to pick up the Golden Rune.
 * Created by:
 * @author Lai Khairong
 */
public class PickUpConsumableItemAction extends PickUpItemAction {

    /**
     * The consumable item to be picked up.
     */
    private final ConsumableItem consumableItem;

    /**
     * Constructor that initializes the consumable item to be picked up.
     * @param consumableItem The consumable item to be picked up.
     */
    public PickUpConsumableItemAction(ConsumableItem consumableItem) {
        super(consumableItem);
        this.consumableItem = consumableItem;
    }

    /**
     * Method to override the execute method to add a ConsumeAction to the actor's action list.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumableItem.addConsumeAction();
        return super.execute(actor, map);
    }
}
