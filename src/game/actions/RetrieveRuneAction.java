package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runes.Rune;
import game.runes.RuneManager;

/**
 * Action for retrieving a Rune from the ground.
 * Created by:
 * @author Lai Khairong
 */
public class RetrieveRuneAction extends PickUpAction {

    /**
     * The Rune to be retrieved.
     */
    private Rune rune;

    /**
     * Constructor for RetrieveRuneAction class.
     * @param rune the Rune to be retrieved.
     */
    public RetrieveRuneAction(Rune rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * Returns a String describing the action of retrieving a Rune.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describing the action of retrieving a Rune.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().addRuneAmount(rune.getRuneAmount());
        super.execute(actor, map);
        return "Player retrieved Runes (value: " + rune.getRuneAmount() + ")";
    }

    /**
     * Returns a String describing the action of retrieving a Rune.
     * @param actor The actor performing the action.
     * @return a String describing the action of retrieving a Rune.
     */
    @Override
    public String menuDescription(Actor actor) { return "Player retrieves Runes (value: " + rune.getRuneAmount() + ")"; }
}
