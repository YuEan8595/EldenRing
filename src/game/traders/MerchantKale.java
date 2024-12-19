package game.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseWeaponAction;
import game.weapons.*;

/**
 * A class that represents a trader named Merchant Kale.
 * Merchant Kale sells and buys weapons to the player.
 * Player and Enemy cannot attack Merchant Kale.
 * Created by:
 * @author Lim Yu Ean
 */

public class MerchantKale extends Trader{

    /**
     * A constructor for MerchantKale class to initialize its name, displayChar, and hit points
     */
    public MerchantKale(){
        super("Merchant Kale", 'K', 0);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        Uchigatana uchigatana = new Uchigatana();
        GreatKnife greatKnife = new GreatKnife();
        Club club = new Club();
        Scimitar scimitar = new Scimitar();
        actions.add(new PurchaseWeaponAction(uchigatana, uchigatana.getPurchaseCost()));
        actions.add(new PurchaseWeaponAction(greatKnife, greatKnife.getPurchaseCost()));
        actions.add(new PurchaseWeaponAction(club, club.getPurchaseCost()));
        actions.add(new PurchaseWeaponAction(scimitar, scimitar.getPurchaseCost()));
        return actions;
    }
}
