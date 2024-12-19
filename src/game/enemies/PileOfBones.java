package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.behaviours.AreaAttackBehaviour;
import game.behaviours.AttackBehaviour;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.utils.Ability;
import game.utils.EnemyType;
import game.utils.Status;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

import java.util.Random;

/**
 * A pile of bones that will become a HeavySkeletonSwordsman or Skeletal Bandit after 3 turns,
 * if it is not hit by the player.
 * If it is hit by the player, it will drop a Grossmesser.
 */
public class PileOfBones extends Actor implements Resettable {

    /**
     * A random number generator
     */
    private Random rand = new Random();
    /**
     * The number of turns the pile of bones has been alive.
     */
    private int tickCount = 0;

    /**
     * A constructor for PileOfBones class to initialize its name, displayChar, and hit points.
     */
    public PileOfBones() {
        super("Pile of Bones", 'X', 1);
        this.addCapability(Status.CANT_ATTACK_OTHERS);
        this.addCapability(EnemyType.NOT_HOSTILE_TO_SKELETAL);
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

        int max = map.getXRange().max();
        tickCount += 1;
        if (this.isConscious() && tickCount >= 3) { // if the pile of bones is not hit within 3 turns, it will become a HeavySkeletonSwordsman
            HeavySkeletonSwordsman heavySkeletonSwordsman = new HeavySkeletonSwordsman();
            SkeletalBandit skeletalBandit = new SkeletalBandit();
            Location location = map.locationOf(this);
            map.removeActor(this);
            if(location.x() < (max/2)) {
                map.addActor(heavySkeletonSwordsman, location);
            }
            else {
                map.addActor(skeletalBandit, location);
            }
        }
        else if (!this.isConscious()){   // if the pile of bones dead, it will drop a Grossmesser
            Location location = map.locationOf(this);
            map.removeActor(this);
            if(location.x() < (max/2)) {
                location.addItem(new Grossmesser());
            }
            else {
                location.addItem(new Scimitar());
            }
        }
        return new DoNothingAction();
    }

    /**
     * Returns a collection of the Actions that the otherActor can perform on this Actor.
     *
     * @param otherActor the Actor acting
     * @param direction  the direction of the other Actor relative to this one
     * @param map        the map containing the Actor
     * @return a collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {    // if the attacker is the player
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
                game.enemies.Enemy enemy = (game.enemies.Enemy) otherActor;
                if (otherActor.getWeaponInventory().size() > 0) {  // if the attacker is enemy, then first weapon is chosen
                    WeaponItem weaponItem = otherActor.getWeaponInventory().get(0);
                    Action specialSkill = weaponItem.getSkill(this, direction); // for now only grossmesser has special skill
                    if (weaponItem.getSkill(this, direction) != null) {
                        if (rand.nextInt(100) <= 50) {  // enemy has 50% chance of using the weapon skill
                            if (otherActor.hasCapability(Ability.CRABSLAM) || otherActor.hasCapability(Ability.SPINNINGATTACK) || otherActor.hasCapability(Ability.DOGSLAM) || otherActor.hasCapability(Ability.FISHSLAM)) {
                                actions.add(new AreaAttackAction(this, direction, weaponItem));
                                enemy.addBehaviour(game.enemies.Enemy.areaAttackPriority, new AreaAttackBehaviour(this, direction, weaponItem));
                            }
                            else {
                                actions.add(specialSkill);
                            }
                        }
                    }
                    actions.add(new AttackAction(this, direction, weaponItem));
                    enemy.addBehaviour(game.enemies.Enemy.attackPriority, new AttackBehaviour(this, direction, weaponItem));
                }
                else {
                    actions.add(new AttackAction(this, direction));  // intrinsic weapon
                    enemy.addBehaviour(game.enemies.Enemy.attackPriority, new AttackBehaviour(this, direction));
                }
            }
        }
        return actions;
    }

    /**
     * Returns the EnemyType of the PileOfBones
     *
     * @return the EnemyType of the PileOfBones
     */
    public EnemyType getEnemyType() {
        return EnemyType.NOT_HOSTILE_TO_SKELETAL;
    }

    /**
     * A method to reset the PileOfBones
     */
    @Override
    public void reset() { this.addCapability(Status.DESPAWNABLE); }
}