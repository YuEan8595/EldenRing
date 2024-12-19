package game.enemies;

import game.utils.EnemyType;


/**
 * A class that represents a Godrick enemy.
 * This class is used to create a Godrick enemy.
 */
public abstract class GodrickEnemy extends Enemy{

    /**
     * An integer that represents the amount of rune drop by the Dog
     */
    protected int runeDrop;

    /**
     * A constructor for Enemy class to initialize its name, displayChar, and hit points
     *
     * @param name        the name of the enemy
     * @param displayChar the display character of the enemy
     * @param hitPoints   the hit points of the enemy
     * @param runeDrop    the amount of rune dropped by the enemy when it dies
     */
    public GodrickEnemy(String name, char displayChar, int hitPoints, int runeDrop) {
        super(name, displayChar, hitPoints);
        this.runeDrop = runeDrop;
        this.addCapability(EnemyType.NOT_HOSTILE_TO_GODRICK);
    }

    /**
     * Returns the type of enemy.
     * @return the type of enemy.
     */
    @Override
    public EnemyType getEnemyType() {
        return EnemyType.NOT_HOSTILE_TO_GODRICK;
    }

    /**
     * Returns the amount of runes dropped by the GodrickEnemy when it dies.
     * @return the amount of runes dropped by the GodrickEnemy when it dies.
     */
    @Override
    public int getRuneDrop() {
        return this.runeDrop;
    }
}
