package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.SellWeaponAction;
import game.traders.Purchasable;
import game.traders.Sellable;
import game.utils.Ability;
import game.utils.Status;

/**
 * A weapon which is a curve sword, it is the weapon carried by the BanditSwordsman.
 * It deals 118 damage with 88% hit rate
 * This weapon has a unique skill which can perform an area attack to hit
 * all creatures within the user surrounding (8 exits) with the same damage and hit rate
 * This weapon is purchasable from and sellable to the trader
 * Created by:
 * @author Lai Khairong
 */
public class Scimitar extends WeaponItem implements Purchasable, Sellable {

    /**
     * The action to sell the weapon
     */
    private final SellWeaponAction sellWeaponAction;

    /**
     * A Constructor for Scimitar class to initialize its name, displayChar, damage, verb, and hit rate
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "hits", 88);
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
     * A method to get the action of the weapon
     * @param target The actor to attack
     * @param direction String representing the direction of the target
     * @return An AreaAttackAction
     */
    public Action getSkill(Actor target, String direction){
        return new AreaAttackAction();
    }


    /**
     * A method to get the buy price of the weapon
     * @return An integer representing the buy price of the weapon
     */
    @Override
    public int getPurchaseCost(){
        return 600;
    }

    /**
     * A method to get the sell price of the weapon
     * @return An integer representing the sell price of the weapon
     */
    @Override
    public int getSellCost() { return 100; }
}
