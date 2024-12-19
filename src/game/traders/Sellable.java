package game.traders;

/**
 * Sellable interface that has a method to get the sell price of the weapon
 * Only the weapon that is sellable to the trader will implement this interface
 * Created by:
 * @author Lim Yu Ean
 */
public interface Sellable {

    /**
     * A method to get the sell price of the weapon
     * @return the sell price of the weapon
     */
    int getSellCost();
}
