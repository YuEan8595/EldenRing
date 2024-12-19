package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lim Yu Ean
 *
 */
public class RandomNumberGenerator {
    /**
     * Get a random integer
     * @param bound the upper bound of the random number
     * @return a random integer
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Get a random integer
     * @param lowerBound the lower bound of the random number
     * @param upperBound the upper bound of the random number
     * @return a random integer
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
