package game.consumables;

/**
 * Class representing the Flask of Crimson Tears consumable item.
 * Created by:
 * @author Lai Khairong
 */
public class FlaskOfCrimsonTearsManager {

    /**
     * Singleton instance of the class.
     */
    private static FlaskOfCrimsonTears instance = null;

    /**
     * Constructor.
     */
    public static FlaskOfCrimsonTears getInstance() {
        if (instance == null) {
            instance = new FlaskOfCrimsonTears();
        }
        return instance;
    }
}
