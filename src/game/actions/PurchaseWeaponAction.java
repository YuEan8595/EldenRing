package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.runes.RuneManager;

/**
 * An Action to purchase a weapon from trader.
 * Created by:
 * @author Lai Khairong
 */
public class PurchaseWeaponAction extends PurchaseAction {

    /**
     * The weapon that is to be purchased
     */
    private final WeaponItem weaponItem;
    /**
     * The cost of the weapon
     */
    private final int purchaseCost;

    /**
     * Constructor.
     * @param weaponItem the weapon to purchase
     * @param purchaseCost the cost of the weapon
     */
    public PurchaseWeaponAction(WeaponItem weaponItem, int purchaseCost) {
        super(weaponItem, purchaseCost);
        this.weaponItem = weaponItem;
        this.purchaseCost = purchaseCost;
    }

    /**
     * Executes the purchase action.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(RuneManager.getInstance().getRuneAmount() >= purchaseCost){
            RuneManager.getInstance().deductRuneAmount(purchaseCost);
            actor.addWeaponToInventory(weaponItem);
            return actor + " has bought " + weaponItem;
        }
        else {
            return actor + " does not have enough runes to purchase " + weaponItem + "!";
        }
    }
}
