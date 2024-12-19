package game.enemies;

import game.utils.EnemyType;


/**
 * A class that represents a land enemy
 * This class is used to create a land enemy
 */
public abstract class LandEnemy extends Enemy{

    /**
     * An integer that represents the amount of rune drop by the Dog
     */
    protected int runeDrop;

    /**
     * A constructor for Land Enemy class to initialize its name, displayChar, and hit points
     *
     * @param name        the name of the enemy
     * @param displayChar the display character of the enemy
     * @param hitPoints   the hit points of the enemy
     */
    public LandEnemy(String name, char displayChar, int hitPoints, int runeDrop) {
        super(name, displayChar, hitPoints);
        this.runeDrop = runeDrop;
        this.addCapability(EnemyType.NOT_HOSTILE_TO_WOLF_DOG);
    }

    /**
     * Returns the type of enemy.
     * @return the type of enemy.
     */
    @Override
    public EnemyType getEnemyType() {
        return EnemyType.NOT_HOSTILE_TO_WOLF_DOG;
    }

    /**
     * Returns the amount of runes dropped by the LandEnemy when it dies.
     * @return the amount of runes dropped by the LandEnemy when it dies.
     */
    @Override
    public int getRuneDrop() {
        return this.runeDrop;
    }
}
