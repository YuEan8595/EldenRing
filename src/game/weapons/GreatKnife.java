package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickStepAction;
import game.actions.SellWeaponAction;
import game.traders.Purchasable;
import game.traders.Sellable;
import game.utils.Ability;
import game.utils.Status;

/**
 * A weapon which is a dagger type, it can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * This weapon has a unique skill which is QuickStep that move the user away from
 * the enemy, evading their attack
 * This weapon is purchasable from and sellable to the trader
 * Created by:
 * @author Lim Yu Ean
 */
public class GreatKnife extends WeaponItem implements Purchasable, Sellable {

    /**
     * The action to sell the weapon
     */
    private final SellWeaponAction sellWeaponAction;

    /**
     * A Constructor for GreatKnife class to initialize its name, displayChar, damage, verb, and hit rate
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "slashes", 70);
        this.addCapability(Ability.QUICKSTEP);
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
        return new QuickStepAction(target, direction, this);
    }

    /**
     * A getter for the purchase price of the weapon
     * @return the purchase price of the weapon
     */
    @Override
    public int getPurchaseCost(){ return 3500; }

    /**
     * A getter for the sell price of the weapon
     * @return the sell price of the weapon
     */
    @Override
    public int getSellCost(){ return 350; }
}
