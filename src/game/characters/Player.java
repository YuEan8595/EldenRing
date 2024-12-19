package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.consumables.FlaskOfCrimsonTearsManager;
import game.items.RemembranceOfTheGraftedManager;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.runes.Rune;
import game.runes.RuneManager;
import game.utils.Ability;
import game.utils.Status;


/**
 * Abstract Class representing the Player. It implements the Resettable interface.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lim Yu Ean, Lai Khairong
 */
public abstract class Player extends Actor implements Resettable {

    /**
     * A menu to display the options for the player.
     */
    private final Menu menu = new Menu();

    /**
     * The LimGrave map where the player starts the game.
     */
    private GameMap limGraveMap;

    /**
     * The number of turns the player has been poisoned.
     */
    private int poisonedTurns = 0;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        Rune rune = new Rune(0);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addItemToInventory(FlaskOfCrimsonTearsManager.getInstance());
        this.addItemToInventory(RemembranceOfTheGraftedManager.getInstance());
        ResetManager.getInstance().registerResettable(this);
        RuneManager.getInstance().setRune(rune);
    }

    /**
     * Set the LimGrave map where the player starts the game.
     * @param limGraveMap the LimGrave map where the player starts the game
     */
    public void setLimGraveMap(GameMap limGraveMap) {
    	this.limGraveMap = limGraveMap;
    }

    /**
     * Returns the LimGrave map where the player starts the game.
     * @return the LimGrave map where the player starts the game
     */
    public GameMap getLimGraveMap() {
    	return this.limGraveMap;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location location = map.locationOf(this);
        Ground ground = location.getGround();
        // If the player steps on a cliff, the player instantly get killed
        if (ground.hasCapability(Status.CLIFF)){
            return new DeathAction(this);
        }

        // when the player die
        if (!this.isConscious()) {
            return new DeathAction(this);
        }

        // when the player is poisoned
        if(this.hasCapability(Status.POISONED)) {
            poisonedTurns++;
            if(poisonedTurns == 3) {
                this.removeCapability(Status.POISONED);
                poisonedTurns = 0;
            }
            this.hurt(50);
            display.println(this + " is poisoned and lost 50 HP");
            if(!this.isConscious()) {
                this.removeCapability(Status.POISONED);
                return new DeathAction(this);
            }
        }

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        display.println("Tarnished " + printHp() + ", runes: " + RuneManager.getInstance().getRuneAmount());
        return menu.showMenu(this, actions, display);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Ability.UNSHEATHE)){
            for (WeaponItem weapon : otherActor.getWeaponInventory()) {
                if (weapon.hasCapability(Ability.UNSHEATHE))
                    actions.add(new UnsheatheAction(this, direction, weapon));
                    break;
            }
        }

        // AreaAttack has the highest priority followed by Attack then Follow
        if(otherActor.hasCapability(Ability.SPINNINGATTACK) || otherActor.hasCapability(Ability.CRABSLAM) || otherActor.hasCapability(Ability.DOGSLAM) || otherActor.hasCapability(Ability.FISHSLAM)) {
            if (otherActor.getWeaponInventory().size() > 0) {
                actions.add(new AreaAttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
            }
            else{
                actions.add(new AreaAttackAction(this, direction));
            }

            if (otherActor.hasCapability(Status.HOSTILE_TO_ALL)) {
                if (otherActor.getWeaponInventory().size() > 0) {
                    actions.add(new AttackAction(otherActor, direction, otherActor.getWeaponInventory().get(0)));
                }
                else{
                    actions.add(new AttackAction(otherActor, direction));
                }
                Behaviour followBehaviour = new FollowBehaviour(this);
                actions.add(followBehaviour.getAction(otherActor, map));
            }
        }
        else {	// if the enemy does not have the unique ability
            if (otherActor.hasCapability(Status.HOSTILE_TO_ALL)) {
                if (otherActor.getWeaponInventory().size() > 0) {
                    actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
                }
                else {
                    actions.add(new AttackAction(this, direction));
                }
                Behaviour followBehaviour = new FollowBehaviour(this);
                actions.add(followBehaviour.getAction(otherActor, map));
            }
        }
        return actions;
    }

    /**
     * Method to reset the player's hp to the max hp.
     */
    @Override
    public void reset() {
        super.resetMaxHp(super.getMaxHp());
    }

    /**
     * Returns an IntrinsicWeapon object that represents the player's attack.
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(11, "hits");
    }
}