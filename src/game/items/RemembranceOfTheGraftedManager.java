package game.items;

/**
 * Class to manage the Remembrance of the Grafted item.
 * Created by:
 * @author Lai Khairong
 */
public class RemembranceOfTheGraftedManager {

    /**
     * Singleton instance of the class.
     */
    private static RemembranceOfTheGrafted instance = null;

    /**
     * Constructor.
     */
    public static RemembranceOfTheGrafted getInstance() {
        if (instance == null) {
            instance = new RemembranceOfTheGrafted();
        }
        return instance;
    }
}
