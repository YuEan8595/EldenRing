package game.environments;


import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the Cliff, it instantly killed the player if the player
 * steps on it.
 * Created by:
 * @author Lim Yu Ean
 */
public class Cliff extends Ground {

    /**
     * Constructor.
     *
     */
    public Cliff() {
        super('+');
        this.addCapability(Status.CLIFF);
    }
}
