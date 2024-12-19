package game.traders;

/**
 * Purchasable interface that has a method to get the purchase price of the weapon
 * Only the weapon that is purchasable from the trader will implement this interface
 * Created by:
 * @author Lim Yu Ean
 */
public interface Purchasable {

    /**
     * A method to get the purchase price of the weapon
     * @return the purchase price of the weapon
     */
    int getPurchaseCost();
}
