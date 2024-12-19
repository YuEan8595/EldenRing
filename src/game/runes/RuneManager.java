package game.runes;

/**
 * RuneManager class that manages the player's rune
 * created by:
 * @author Lai Khairong
 */
public class RuneManager {

    /**
     * The only instance of the RuneManager class
     */
    private static RuneManager instance;
    /**
     * The rune object
     */
    private Rune rune;

    /**
     * A private constructor for the RuneManager class
     */
    private RuneManager() {}

    /**
     * A method to get the only instance of the RuneManager class
     * @return the only instance of the RuneManager class
     */
    public static RuneManager getInstance() {
        if (instance == null){
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * A method to set the rune object
     */
    public void setRune(Rune rune) { this.rune = rune; }

    /**
     * A method to get the rune amount
     * @return the rune amount
     */
    public int getRuneAmount() { return rune.getRuneAmount(); }

    /**
     * A method to set the rune amount
     * @param amount the rune amount
     */
    public void setRuneAmount(int amount) { rune.setRuneAmount(amount); }

    /**
     * A method to add the rune amount
     * @param amount the rune amount
     */
    public void addRuneAmount(int amount) { rune.setRuneAmount(rune.getRuneAmount() + amount); }

    /**
     * A method to deduct the rune amount
     * @param amount the rune amount
     */
    public void deductRuneAmount(int amount) { rune.setRuneAmount(rune.getRuneAmount() - amount); }
}
