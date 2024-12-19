package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.utils.Ability;

/**
 * An intrinsic weapon of the Giant Dog that can be used to attack the enemy.
 * It deals 314 damage with 90% hit rate
 * This weapon has a unique skill which can perform an area attack to slam
 * all creatures within the user surrounding (8 exits) with the same damage and hit rate
 * This weapon is not purchasable from and not sellable to the trader
 * Created by:
 * @author Lai Khairong
 */
public class DogSlam extends WeaponItem  {

    /**
     * A Constructor for DogSlam class to initialize its name, displayChar, damage, verb, and hit rate
     */
    public DogSlam() {
        super("DogSlam", 'k', 314, "slams",90 );
        this.addCapability(Ability.DOGSLAM);
    }

    /**
     * A getter for the unique skill of the weapon
     * @param target the target of the unique skill to be performed
     * @param direction the direction of the unique skill to be performed
     * @return the special action of the unique skill
     */
    public Action getSkill(Actor target, String direction){
        return new AreaAttackAction();
    }
}
