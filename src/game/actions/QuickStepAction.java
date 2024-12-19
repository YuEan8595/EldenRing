package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.weapons.GreatKnife;
import java.util.Random;

/**
 * An action representing the QuickStep unique skill.
 * Created by:
 * @author Lim Yu Ean
 *
 */
public class QuickStepAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Location of the actor after performing QuickStep
     */
    private Location location;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public QuickStepAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }


    /**
     * Execute the QuickStep action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        AttackAction attackAction = new AttackAction(target, direction, this.weapon);
        String result = attackAction.execute(actor, map);
        Location location = map.locationOf(actor);
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (!destination.containsAnActor()) {   // move to the first exit with no actor
                map.moveActor(actor, exit.getDestination());
                this.location = exit.getDestination();
                result += " and " + actor + " performs QuickStep to " + exit.getName();
                return result;
            }
        }
        return null;
    }

    /**
     * Get the location of the actor after performing QuickStep
     * @return a String, representing the location of the actor after performing QuickStep
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + weapon + " and perform QuickStep";
    }
}
