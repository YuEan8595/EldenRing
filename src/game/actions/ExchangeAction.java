package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Action for exchanging an item for a weapon.
 * Created by:
 * @author Lai Khairong
 */
public class ExchangeAction extends Action {

    /**
     * Item to be exchanged.
     */
    private final Item item;
    /**
     * Weapon to be exchanged for.
     */
    private final WeaponItem weaponItem;

    /**
     * Constructor.
     * @param weaponItem Weapon to be exchanged for.
     * @param item Item to be exchanged.
     */
    public ExchangeAction(WeaponItem weaponItem, Item item) {
        this.weaponItem = weaponItem;
        this.item = item;
    }

    /**
     * Executes the action of exchanging an item for a weapon.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        actor.addWeaponToInventory(weaponItem);
        return actor + " has exchanged " + item + " for " + weaponItem;
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) { return actor + " exchanges " + item + " for " + weaponItem; }
}
