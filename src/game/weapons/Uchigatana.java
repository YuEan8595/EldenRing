package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellWeaponAction;
import game.actions.UnsheatheAction;
import game.traders.Purchasable;
import game.traders.Sellable;
import game.utils.Ability;
import game.utils.Status;

/**
 * A weapon which is a katana type, it can be used to attack the enemy.
 * It deals 115 damage with 80% hit rate
 * This weapon has a unique skill which is unsheathe that deal 2 * damage with 60% hit rate
 * This weapon is purchasable from and sellable to the trader
 * Created by:
 * @author Lim Yu Ean
 */

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * The action to sell the weapon
     */
    private final SellWeaponAction sellWeaponAction;

    /**
     * A Constructor for Uchigatana class to initialize its name, displayChar, damage, verb, and hit rate
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
        this.addCapability(Ability.UNSHEATHE);
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
        return new UnsheatheAction(target, direction, this);
    }

    /**
     * A getter for the purchase price of the weapon
     * @return the purchase price of the weapon
     */
    @Override
    public int getPurchaseCost(){
        return 5000;
    }

    /**
     * A getter for the sell price of the weapon
     * @return the sell price of the weapon
     */
    @Override
    public int getSellCost(){
        return 500;
    }
}
