package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Lim Yu Ean
 *
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Returns false, so actors can't walk through walls.
	 *
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Returns true, so walls can block thrown objects.
	 *
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
