package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * SellWeaponAction is an abstract class that represents the selling of a weapon.
 * Created by:
 * @author Lai Khairong
 */
public class SellWeaponAction extends SellAction {

    /**
     * The weapon to sell
     */
    private final WeaponItem weaponItem;

    /**
     * Constructor.
     * @param weaponItem the weapon to sell
     */
    public SellWeaponAction(WeaponItem weaponItem, int sellCost) {
        super(weaponItem, sellCost);
        this.weaponItem = weaponItem;
    }

    /**
     * Executes the sell action.
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return a description of the action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeWeaponFromInventory(weaponItem);
        return super.execute(actor, map);
    }
}
