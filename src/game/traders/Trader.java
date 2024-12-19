package game.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;

/**
 * A class that represents a trader.
 * Created by:
 * @author Lim Yu Ean
 */
public class Trader extends Actor {

    /**
     * A constructor for Trader class to initialize its name, displayChar, and hit points
     * @param name the name of the trader
     * @param displayChar the character that represents the trader
     * @param hitPoints the hit points of the trader
     */
    public Trader(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.CAN_TRADE);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
