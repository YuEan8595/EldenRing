package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing the Golden Seed consumable item.
 * Created by:
 * @author Lai Khairong
 */
public class GoldenSeed extends ConsumableItem {

    /**
     * Constructor that initializes the name, displayChar and portability.
     */
    public GoldenSeed() {
        super("Golden Seed", ',', true);
    }

    /**
     * Method to consume the consumable item.
     * @param actor The actor performing the action.
     * @return a string describing the result of the action
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        FlaskOfCrimsonTearsManager.getInstance().increaseNumberOfUses();
        return actor + " consumed " + this + " and gained 1 use of Flask of Crimson Tears.";
    }
}
