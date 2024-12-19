package game.enemies;

import game.utils.Ability;
import game.utils.EnemyType;

/**
 * A class that represents a bone enemy.
 * This class is used to create a bone enemy.
 */
public abstract class BoneEnemy extends Enemy{

    /**
     * An integer that represents the amount of rune drop by the Dog
     */
    protected int runeDrop;

    /**
     * Constructor.
     *
     * @param name        Name to call the enemy in the UI
     * @param displayChar Character to represent the enemy in the UI
     * @param hitPoints   Amount of damage the enemy can take before it dies
     * @param runeDrop    The amount of rune dropped by the enemy when it dies
     */
    public BoneEnemy(String name, char displayChar, int hitPoints, int runeDrop){
        super(name, displayChar, hitPoints);
        this.runeDrop = runeDrop;
        this.addCapability(Ability.PILEOFBONES);
        this.addCapability(EnemyType.NOT_HOSTILE_TO_SKELETAL);
    }

    /**
     * Returns the type of enemy.
     * @return the type of enemy.
     */
    @Override
    public EnemyType getEnemyType() {
        return EnemyType.NOT_HOSTILE_TO_SKELETAL;
    }

    /**
     * Returns the amount of runes dropped by the BoneEnemy when it dies.
     * @return the amount of runes dropped by the BoneEnemy when it dies.
     */
    @Override
    public int getRuneDrop() {
        return this.runeDrop;
    }
}
