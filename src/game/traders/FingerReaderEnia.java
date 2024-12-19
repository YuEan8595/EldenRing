package game.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ExchangeAction;
import game.items.RemembranceOfTheGraftedManager;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * Class representing the Finger Reader Enia trader.
 * Created by:
 * @author Lai Khairong
 */
public class FingerReaderEnia extends Trader {

    /**
     * Constructor.
     */
    public FingerReaderEnia() { super("Finger Reader Enia", 'E', 0); }

    /**
     * Returns the list of actions that the otherActor can do to the trader.
     * @param otherActor the Actor that might be performing actions
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList object containing the list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.getItemInventory().contains(RemembranceOfTheGraftedManager.getInstance())) {
            actions.add(new ExchangeAction(new AxeOfGodrick(), RemembranceOfTheGraftedManager.getInstance()));
            actions.add(new ExchangeAction(new GraftedDragon(), RemembranceOfTheGraftedManager.getInstance()));
        }
        return actions;
    }
}
