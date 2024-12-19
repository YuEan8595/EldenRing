package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import game.resets.ResetManager;
import game.resets.Resettable;

/**
 * Flask of Crimson Tears.
 * created by:
 * @author Lai Khairong
 */
public class FlaskOfCrimsonTears extends ConsumableItem implements Resettable {

    /**
     * The maximum number of uses.
     */
    private int maximumUses = 2;
    /**
     * The current amount of uses left.
     */
    private int usesLeft = maximumUses;

    /**
     * A constructor for FlaskOfCrimsonTears class that initializes the name, displayChar and portability.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'c', false);
        addConsumeAction();
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Method to print out the usesLeft/maximumUses
     * @return A string showing the usesLeft/maximumUses
     */
    public String toString() { return super.toString() + " (" + usesLeft + "/" + maximumUses + ")"; }

    /**
     * Override the reset method to reset to the maximum number of uses
     */
    @Override
    public void reset() { usesLeft = maximumUses; }

    /**
     * Method to consume the consumable item.
     * @param actor The actor performing the action.
     * @return a string describing the result of the action
     */
    @Override
    public String consume(Actor actor) {
        int restoreAmount = 250;
        String result = "";
        if (usesLeft > 0) {
            usesLeft--;
            actor.heal(restoreAmount);
            result += actor + " consumed " + this;
        } else {
            result += this + " is empty";
        }
        return result;
    }

    /**
     * Method to increase the number of uses of the consumable item.
     */
    public void increaseNumberOfUses() {
        maximumUses++;
        reset();
    }
}
