package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import game.runes.RuneManager;
import game.utils.RandomNumberGenerator;

/**
 * Class representing the Golden Runes consumable item.
 * Created by:
 * @author Lai Khairong
 */
public class GoldenRune extends ConsumableItem {

    /**
     * Constructor that initializes the name, displayChar and portability.
     */
    public GoldenRune() { super("Golden Rune", '*', true); }

    /**
     * Method to consume the consumable item.
     * @param actor The actor performing the action.
     * @return a string describing the result of the action
     */
    @Override
    public String consume(Actor actor) {
        int random = RandomNumberGenerator.getRandomInt(200,10000);
        RuneManager.getInstance().addRuneAmount(random);
        actor.removeItemFromInventory(this);
        return actor + " consumed " + this + " and gained " + random + " runes.";
    }
}
