package game.behaviours;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;

/**
 * A class that represents the behaviour of an Enemy to attack a target.
 * Created by:
 * @author Lim Yu Ean
 */
public class AttackBehaviour implements Behaviour{

    /**
     * An Actor object that represents the target of the attack.
     */
    private final Actor target;
    /**
     * A String that represents the direction of the attack.
     */
    private String direction;
    /**
     * A Weapon object that represents the weapon used for the attack.
     */
    private Weapon weapon;

    /**
     * A constructor for AttackBehaviour class.
     * @param subject An Actor object that represents the target of the attack.
     * @param direction A String that represents the direction of the attack.
     */
    public AttackBehaviour(Actor subject, String direction) {
        this.target = subject;
        this.direction = direction;
        this.weapon = null;
    }

    /**
     * A constructor for AttackBehaviour class if the enemy has a weapon.
     * @param subject An Actor object that represents the target of the attack.
     * @param direction A String that represents the direction of the attack.
     * @param weapon A Weapon object that represents the weapon used for the attack.
     */
    public AttackBehaviour(Actor subject, String direction, Weapon weapon) {
        this.target = subject;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * A method that returns an Action object that represents the attack action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return An Action object that represents the attack action.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;
        return new AttackAction(target, direction, weapon);
    }
}
