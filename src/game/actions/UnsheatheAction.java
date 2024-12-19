package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import java.util.Random;

/**
 * An Action to represent the Unsheathe unique skill.
 * Created by:
 * @author Lim Yu Ean
 *
 */
public class UnsheatheAction extends Action {

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
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public UnsheatheAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Execute the Unsheathe action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) <= (weapon.chanceToHit()-20))) {  // original is 80% but Unsheathe is 60%
            return actor + " misses " + target + ".";
        }
        int damage = weapon.damage() * 2;   // deals 2x damage
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }
        return result;
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player unsheathe Fred at North with Uchigatana"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathe " + target + " at " + direction + " with " + weapon;
    }
}
