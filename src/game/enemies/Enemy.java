package game.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.behaviours.*;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.utils.Ability;
import game.utils.EnemyType;
import game.utils.RandomNumberGenerator;
import game.utils.Status;
import java.util.*;

/**
 * An abstract class that represents an enemy in the game.
 * It has a treemap of behaviours that it will execute in order which is used to
 * determine which behaviour has the highest priority to be executed.
 * created by
 * @author Lim Yu Ean
 */
public abstract class Enemy extends Actor implements Resettable{

    /**
     * A map of behaviours that the enemy will execute in order.
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    /**
     * A constant that represents the priority of the area attack behaviour.
     */
    public static final int areaAttackPriority = 1;
    /**
     * A constant that represents the priority of the attack behaviour.
     */
    public static final int attackPriority = 2;
    /**
     * A constant that represents the priority of the follow behaviour.
     */
    public static final int followPriority = 3;
    /**
     * A constant that represents the priority of the wander behaviour.
     */
    public static final int wanderPriority = 999;
    /**
     * A boolean that represents whether the enemy is following the player.
     */
    protected boolean followingPlayer = false;

    /**
     * A constructor for Enemy class to initialize its name, displayChar, and hit points
     * @param name the name of the enemy
     * @param displayChar the display character of the enemy
     * @param hitPoints the hit points of the enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(wanderPriority, new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_ALL);
        ResetManager.getInstance().registerResettable(this);
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
        if (this.hasCapability(Status.DESPAWNABLE)) {
            return new DespawnAction(this, map);
        }

        this.behaviours.put(wanderPriority, new WanderBehaviour());

        if(RandomNumberGenerator.getRandomInt(100) <= 10 && !followingPlayer){    // 10% removed from the map unless following player
            return new DespawnAction(this, map);
        }
        for (int i: this.behaviours.keySet()) {
            Action action = this.behaviours.get(i).getAction(this, map);
            if (action != null) {
                for (Action actionInActionList : actions) {
                    boolean result = action.getClass().equals(actionInActionList.getClass());
                    if (result) {
                        if (i == followPriority) {
                            followingPlayer = true;
                        } else {
                            followingPlayer = false;
                        }
                        this.behaviours.clear();
                        return action;
                    }
                }
            }
        }
        this.behaviours.clear();
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {    // if the attacker is the player

            this.addBehaviour(Enemy.followPriority, new FollowBehaviour(otherActor));
            if (this.getWeaponInventory().size() > 0){
                this.addBehaviour(Enemy.attackPriority, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
            }
            else{
                this.addBehaviour(Enemy.attackPriority, new AttackBehaviour(otherActor, direction));
            }
            if (this.hasCapability(Ability.SPINNINGATTACK) || this.hasCapability(Ability.CRABSLAM) || otherActor.hasCapability(Ability.DOGSLAM) || otherActor.hasCapability(Ability.FISHSLAM)){
                if (this.getWeaponInventory().size() > 0){
                    this.addBehaviour(Enemy.areaAttackPriority, new AreaAttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
                }
                else{
                    this.addBehaviour(Enemy.areaAttackPriority, new AreaAttackBehaviour(otherActor, direction));
                }
            }

            if (otherActor.getWeaponInventory().size() > 0) {
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    actions.add(new AttackAction(this, direction, weaponItem));
                }
            }
            actions.add(new AttackAction(this, direction));     // attack with intrinsic weapon

            if (otherActor.hasCapability(Ability.UNSHEATHE)) {
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    if (weaponItem.hasCapability(Ability.UNSHEATHE)) {
                        actions.add(new UnsheatheAction(this, direction, weaponItem));
                        break;
                    }
                }
            }
            if (otherActor.hasCapability(Ability.QUICKSTEP)){
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    if (weaponItem.hasCapability(Ability.QUICKSTEP)) {
                        actions.add(new QuickStepAction(this, direction, weaponItem));
                        break;
                    }
                }
            }
            if (otherActor.hasCapability(Ability.SPINNINGATTACK)) {
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    if (weaponItem.hasCapability(Ability.SPINNINGATTACK)) {
                        actions.add(new AreaAttackAction(this, direction, weaponItem));
                        break;
                    }
                }
            }
            if (otherActor.hasCapability(Ability.CRABSLAM)) {
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    if (weaponItem.hasCapability(Ability.CRABSLAM)) {
                        actions.add(new AreaAttackAction(this, direction, weaponItem));
                        break;
                    }
                }
            }
            if (otherActor.hasCapability(Ability.DOGSLAM)) {
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    if (weaponItem.hasCapability(Ability.DOGSLAM)) {
                        actions.add(new AreaAttackAction(this, direction, weaponItem));
                        break;
                    }
                }
            }
            if (otherActor.hasCapability(Ability.FISHSLAM)) {
                for (WeaponItem weaponItem : otherActor.getWeaponInventory()) {
                    if (weaponItem.hasCapability(Ability.FISHSLAM)) {
                        actions.add(new AreaAttackAction(this, direction, weaponItem));
                        break;
                    }
                }
            }
        }

        if(otherActor.hasCapability(Status.HOSTILE_TO_ALL)){    // if the attacker is the enemy
            boolean isEnemy = this.findCapabilitiesByType(EnemyType.class).equals(otherActor.findCapabilitiesByType(EnemyType.class));  // we don't want to attack same type
            if (!isEnemy) {  // not the player and not enemy of the same type
                if (this.getWeaponInventory().size() > 0){
                    this.addBehaviour(Enemy.attackPriority, new AttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
                }
                else{
                    this.addBehaviour(Enemy.attackPriority, new AttackBehaviour(otherActor, direction));
                }
                if (this.hasCapability(Ability.SPINNINGATTACK) || this.hasCapability(Ability.CRABSLAM) || otherActor.hasCapability(Ability.DOGSLAM) || otherActor.hasCapability(Ability.FISHSLAM)){
                    if (this.getWeaponInventory().size() > 0){
                        this.addBehaviour(Enemy.areaAttackPriority, new AreaAttackBehaviour(otherActor, direction, this.getWeaponInventory().get(0)));
                    }
                    else{
                        this.addBehaviour(Enemy.areaAttackPriority, new AreaAttackBehaviour(otherActor, direction));
                    }
                }

                if (otherActor.getWeaponInventory().size() > 0) {  // if the attacker is enemy, then first weapon is chosen
                    WeaponItem weaponItem = otherActor.getWeaponInventory().get(0);
                    Action specialSkill = weaponItem.getSkill(this, direction); // for now only grossmesser has special skill
                    if (weaponItem.getSkill(this, direction) != null) {
                        if (RandomNumberGenerator.getRandomInt(100) <= 50) {  // enemy has 50% chance of using the weapon skill
                            if (otherActor.hasCapability(Ability.CRABSLAM) || otherActor.hasCapability(Ability.SPINNINGATTACK) || otherActor.hasCapability(Ability.DOGSLAM) || otherActor.hasCapability(Ability.FISHSLAM)) {
                                actions.add(new AreaAttackAction(this, direction, weaponItem));
                            }
                            else {
                                actions.add(specialSkill);
                            }
                        }
                    }
                    actions.add(new AttackAction(this, direction, weaponItem));
                }
                else {
                    actions.add(new AttackAction(this, direction));  // intrinsic weapon
                }
            }
        }
        return actions;
    }


    /**
     * A method to get the status of the enemy
     * @return the status
     */
    public abstract EnemyType getEnemyType();

    /**
     * A method to get the rune drop of the enemy, when the enemy is killed
     * @return the rune drop
     */
    public abstract int getRuneDrop();

    /**
     * A method to add the behaviour to the enemy
     * @param priority the priority of the behaviour
     * @param behaviour the behaviour
     */
    public void addBehaviour(int priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
    }

    /**
     * A method to reset the enemy
     */
    @Override
    public void reset() { this.addCapability(Status.DESPAWNABLE); }
}