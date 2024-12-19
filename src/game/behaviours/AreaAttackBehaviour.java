package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AreaAttackAction;


/**
 * A class that represents the behaviour of an Enemy to perfrom area attack
 * to all targets in its surrounding area (8 exits).
 * Created by:
 * @author Lim Yu Ean
 */
public class AreaAttackBehaviour implements Behaviour{

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * A String that represents the direction of the area attack.
     */
    private String direction;

    /**
     * A Weapon object that represents the weapon used for the area attack.
     */
    private Weapon weapon;

    /**
     * Constructor with 3 arguments.
     * @param target the target to be attacked
     * @param direction the direction of incoming attack
     * @param weapon the weapon used for the attack
     */
    public AreaAttackBehaviour(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with 2 arguments.
     * @param target the target to be attacked
     * @param direction the direction of incoming attack
     */
    public AreaAttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
        this.weapon = null;
    }

    /**
     * A method that returns an Action object that represents the area attack action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return An Action object that represents the area attack action.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(actor))
            return null;
        if (map.contains(actor))
            return new AreaAttackAction(target, direction, weapon);
        return null;
    }
}
