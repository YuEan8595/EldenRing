package game.utils;

import edu.monash.fit2099.engine.displays.Display;

import java.util.Scanner;

/**
 * MenuManager class is a class that handles the menu options which allows the user to select
 * the combat archetype of the player character
 * created by:
 * @author Lim Yu Ean
 */
public class MenuManager {

    /**
     * The only instance of the MenuManager class
     */
    private static MenuManager instance;

    /**
     * A display object to display the menu options to the user
     */
    Display display = new Display();

    /**
     * A private constructor for the MenuManager class
     */
    private MenuManager() {
    }

    /**
     * A method to get the only instance of the MenuManager class
     * @return the only instance of the MenuManager class
     */
    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    /**
     * A method to show the menu item that has 3 options to the user
     * @return the options selected from the menu item
     */
    public int menu() {
        boolean intOnly = false;
        Scanner sel = new Scanner(System.in);
        int choice = 1;
        do {
            try {
                display.println("1) Samurai");
                display.println("2) Bandit");
                display.println("3) Wretch");
                display.print("Select one:");
                choice = Integer.parseInt(sel.nextLine());
                intOnly = true;
                display.println("Your choice:" + choice);
            }
            catch (NumberFormatException e){
                display.println("Please enter an integer");
            }
        } while (!intOnly);
        return choice;
    }
}
