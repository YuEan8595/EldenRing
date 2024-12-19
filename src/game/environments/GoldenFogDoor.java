package game.environments;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

/**
 * A class that represents the GoldenFogDoor which can brings the player
 * from one map to another map.
 * Created by:
 * @author Lim Yu Ean
 */
public class GoldenFogDoor extends Ground {

    /**
     * The action that allows the player to travel to another map.
     */
    private Action travelAction;

    /**
     * Constructor.
     * @param travelAction the action that allows the player to travel to another map.
     */
    public GoldenFogDoor(Action travelAction) {
        super('D');
        this.travelAction = travelAction;
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(this.travelAction);
        }
        return actions;
    }
}
