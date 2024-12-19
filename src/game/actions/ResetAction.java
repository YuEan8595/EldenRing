package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.ResetManager;
import game.utils.Status;

/***
 * Class representing the ResetAction.
 * Created by:
 * @author Lai Khairong
 */
public class ResetAction extends Action {

    /**
     * Method to execute the ResetAction.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action after it is processed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(!(actor.hasCapability(Status.RESTING))) { ResetManager.getInstance().addDeathCount(); }
        ResetManager.getInstance().run();
        return null;
    }

    /**
     * Method to display the menu description of the ResetAction.
     * @param actor The actor performing the action.
     * @return a description of the action suitable for displaying in the menu.
     */
    @Override
    public String menuDescription(Actor actor) { return null; }
}
