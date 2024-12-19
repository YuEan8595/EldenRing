package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.actions.ConsumeAction;
import game.actions.DropConsumableItemAction;
import game.actions.PickUpConsumableItemAction;

/**
 * Class representing a consumable item.
 * Created by:
 * @author Lai Khairong
 */
public abstract class ConsumableItem extends Item {

    /**
     * The action to consume the consumable item.
     */
    private final ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * Constructor that initializes the name, displayChar and portability.
     * @param name The name of the consumable item.
     * @param displayChar The character to use to represent the consumable item when it is on the ground.
     * @param portable True if and only if the consumable item can be picked up.
     */
    public ConsumableItem(String name, char displayChar, boolean portable) { super(name, displayChar, portable); }

    /**
     * Method to consume the consumable item.
     * @param actor The actor performing the action.
     * @return a string describing the result of the action
     */
    public abstract String consume(Actor actor);

    /**
     * Method to get the action to pick up the consumable item.
     * @param actor The actor performing the action.
     * @return a PickUpAction to pick up the consumable item.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpConsumableItemAction(this);
        return null;
    }

    /**
     * Method to get the action to drop the consumable item.
     * @param actor The actor performing the action.
     * @return a DropAction to drop the consumable item.
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        if(portable)
            return new DropConsumableItemAction(this);
        return null;
    }

    /**
     * Method to add the ConsumeAction to the actor's action list.
     */
    public void addConsumeAction() { addAction(consumeAction); }

    /**
     * Method to remove the ConsumeAction from the actor's action list.
     */
    public void removeConsumeAction() { removeAction(consumeAction); }
}
