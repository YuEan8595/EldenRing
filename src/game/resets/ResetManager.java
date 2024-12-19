package game.resets;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lai Khairong
 */
public class ResetManager {

    /**
     * The list of resettables.
     */
    private List<Resettable> resettables;
    /**
     * The instance of the ResetManager.
     */
    private static ResetManager instance;
    /**
     * The death count of the player.
     */
    private int deathCount = 0;

    /**
     * Constructor.
     * Creates a new ArrayList of resettables.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Method to get the instance of the ResetManager.
     * @return the instance of the ResetManager.
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Method to run the resettables.
     */
    public void run() {
        for (Resettable resettable : resettables) {
            resettable.reset();
        }
    }

    /**
     * Method to register a resettable.
     * @param resettable the resettable to be registered.
     */
    public void registerResettable(Resettable resettable) { resettables.add(resettable); }

    /**
     * Method to remove a resettable.
     * @param resettable the resettable to be removed.
     */
    public void removeResettable(Resettable resettable) { resettables.remove(resettable); }

    /**
     * Method to add to the death count.
     */
    public void addDeathCount() {
        deathCount++;
    }

    /**
     * Method to get the death count.
     * @return the death count.
     */
    public int getDeathCount() { return deathCount; }

    /**
     * Method to reset the death count.
     */
    public void resetDeathCount() {
        deathCount = 1;
    }
}
