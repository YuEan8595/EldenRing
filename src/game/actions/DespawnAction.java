package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action to despawn an Actor.
 * Created by:
 * @author Lim Yu Ean
 */
public class DespawnAction extends Action {

    /**
     * The Actor that is to be despawned
     */
    private Actor target;

    /**
     * The map where the actor is located
     */
    private GameMap map;

    /**
     * Constructor.
     *
     * @param actor the Actor to despawn
     * @param map the map where the actor is located
     */
    public DespawnAction(Actor actor, GameMap map) {
        this.target = actor;
        this.map = map;
    }

    /**
     * Despawns the actor from the map.
     *
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " has been despawned";
    }

    /**
     * Returns a description of the action suitable for the menu.
     *
     * @param actor the actor performing the action
     * @return a string, e.g. "Player has been despawned"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been despawned";
    }
}
