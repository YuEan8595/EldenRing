package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents the action of an Actor to attack all targets in its surrounding area (8 exits).
 * Created by:
 * @author Lim Yu Ean
 */
public class AreaAttackAction extends Action {

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
     * Constructor with 3 arguments.
     * @param target the target to be attacked
     * @param direction the direction of incoming attack
     * @param weapon the weapon used for the attack
     */
    public AreaAttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with 2 arguments.
     * @param target the target to be attacked
     * @param direction the direction of incoming attack
     */
    public AreaAttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
        this.weapon = null;
    }

    /**
     * Constructor with empty argument.
     */
    public AreaAttackAction() {
    }

    /**
     * Executes the action of attacking all targets in the surrounding area (8 exits).
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        ArrayList<Actor> actors = new ArrayList<>();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            Actor destinationActor = destination.getActor();
            if (destinationActor != null)  	// for other enemies
                actors.add(destinationActor);
        }

        String result = "";
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        int i = 0;
        for (Actor targets : actors) { // probability is independent between each actor
            if ((rand.nextInt(100) <= weapon.chanceToHit())) {
                int damage = weapon.damage();
                if (i > 0){
                    result += System.lineSeparator();
                }
                result += actor + " " + weapon.verb() + " " + targets + " for " + damage + " damage";
                targets.hurt(damage);
                if (!targets.isConscious()) {
                    result += new DeathAction(actor).execute(targets, map);
                }
            }
            else{
                if (i > 0 && i < actors.size()){
                    result += System.lineSeparator();
                }
                result += actor + " missed " + targets;
            }
            i += 1;
        }
        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs area attack at 8 exits with " + weapon;
    }
}
