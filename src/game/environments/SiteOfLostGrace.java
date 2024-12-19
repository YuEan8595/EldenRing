package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;

/***
 * Class representing a Site of Lost Grace.
 * Created by:
 * @author Lai Khairong
 */
public class SiteOfLostGrace extends Ground {

    /**
     * The name of the Site of Lost Grace.
     */
    private final String name;

    /**
     * Constructor.
     * @param name the name of the Site of Lost Grace.
     */
    public SiteOfLostGrace(String name) {
        super('U');
        this.name = name;
    }

    /**
     * Method to add RestAction to the ActionList of the Actor.
     * @return ActionList containing RestAction.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        actionList.add(new RestAction(this));
        return actionList;
    }

    /**
     * Method to display the name of the Site of Lost Grace.
     * @return the name of the Site of Lost Grace.
     */
    @Override
    public String toString() { return name + " Site of Lost Grace"; }
}
