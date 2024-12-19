package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.SellWeaponAction;
import game.traders.Sellable;
import game.utils.Ability;
import game.utils.Status;

/**
 * A weapon which is a curve sword, it is the weapon carried by the Heavy Skeletal Swordsman.
 * It deals 115 damage with 85% hit rate
 * This weapon has a unique skill which can perform an area attack to hit
 * all creatures within the user surrounding (8 exits) with the same damage and hit rate
 * This weapon is not purchasable from but sellable to the trader
 * Created by:
 * @author Lim Yu Ean
 */

public class Grossmesser extends WeaponItem implements Sellable {
    /**
     * The action to sell the weapon
     */
    private final SellWeaponAction sellWeaponAction;

    /**
     * A Constructor for Grossmesser class to initialize its name, displayChar, damage, verb, and hit rate
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "hits", 85);
        this.addCapability(Ability.SPINNINGATTACK);
        this.sellWeaponAction = new SellWeaponAction(this, getSellCost());
    }

    /**
     * A tick method to check if the actor carrying this WeaponItem, so that it can be
     * purchased from and sell to the trader
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        this.removeAction(sellWeaponAction);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Exit exit : currentLocation.getExits()){
                Location destination = exit.getDestination();
                if (destination.containsAnActor()){
                    Actor target = destination.getActor();
                    if (target.hasCapability(Status.CAN_TRADE)){
                        this.addAction(sellWeaponAction);
                    }
                }
            }
        }
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

    /**
     * A getter for the sell price of the weapon
     * @return the sell price of the weapon
     */
    @Override
    public int getSellCost(){ return 100; }
}
