package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.characters.Player;
import game.enemies.Enemy;
import game.enemies.PileOfBones;
import game.resets.ResetManager;
import game.runes.Rune;
import game.runes.RuneManager;
import game.utils.Ability;
import game.utils.FancyMessage;
import game.utils.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lim Yu Ean
 *
 */
public class DeathAction extends Action {
    /**
     * The actor that killed the target
     */
    private Actor attacker;

    /**
     * Constructor.
     *
     * @param actor the actor that killed the target
     */
    public DeathAction(Actor actor) { this.attacker = actor; }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        // player dies, game resets
        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            ResetAction reset = new ResetAction();
            reset.execute(target, map);

            result += System.lineSeparator() + FancyMessage.YOU_DIED;
            result += System.lineSeparator() + target + " drops " + RuneManager.getInstance().getRuneAmount() + " runes." + System.lineSeparator();

            Rune rune = new Rune(RuneManager.getInstance().getRuneAmount());
            RuneManager.getInstance().setRuneAmount(0);
            map.locationOf(target).addItem(rune);
            ResetManager.getInstance().registerResettable(rune);

            Player player = (Player) target;
            map.moveActor(target, player.getLimGraveMap().at(37, 12));

            return result;
        }

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);
        // remove actor
        Location location = map.locationOf(target);
        map.removeActor(target);
        if (target.hasCapability(Ability.PILEOFBONES)){  //skeleton's unique ability
            map.addActor(new PileOfBones(), location);
        }
        if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY) && target.hasCapability(Status.HOSTILE_TO_ALL)){
            Enemy enemy = (Enemy) target;
            result += System.lineSeparator() + target + " drops " + enemy.getRuneDrop() + " runes.";
            RuneManager.getInstance().addRuneAmount(enemy.getRuneDrop());
        }
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player is killed."
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
