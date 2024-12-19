package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.environments.SiteOfLostGrace;
import game.utils.Status;

/***
 * Class representing the RestAction.
 * Created by:
 * @author Lai Khairong
 */
public class RestAction extends Action {

    /**
     * The Site of Lost Grace where the Actor is resting.
     */
    private final SiteOfLostGrace siteOfLostGrace;

    /**
     * Constructor.
     * @param siteOfLostGrace the Site of Lost Grace where the Actor is resting.
     */
    public RestAction(SiteOfLostGrace siteOfLostGrace) { this.siteOfLostGrace = siteOfLostGrace; }

    /**
     * Method to execute the RestAction.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action after it is processed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.RESTING);
        ResetAction reset = new ResetAction();
        reset.execute(actor, map);
        actor.removeCapability(Status.RESTING);
        return actor + " rested at " + siteOfLostGrace;
    }

    /**
     * Method to display the menu description of the RestAction.
     * @param actor The actor performing the action.
     * @return a description of the action suitable for displaying in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + siteOfLostGrace;
    }
}
