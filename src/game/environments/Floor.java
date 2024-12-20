package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Returns true if the actor is hostile to the enemy.
	 * @param actor the Actor to check
	 * @return true if the actor is hostile to the enemy
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
	}
}
