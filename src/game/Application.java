package game;

import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.characters.Bandit;
import game.characters.Player;
import game.characters.Samurai;
import game.characters.Wretch;
import game.consumables.GoldenRune;
import game.consumables.GoldenSeed;
import game.consumables.PoisonApple;
import game.enemies.Dog;
import game.enemies.GodrickSoldier;
import game.enemies.LoneWolf;
import game.environments.*;
import game.traders.FingerReaderEnia;
import game.traders.MerchantKale;
import game.utils.FancyMessage;
import game.utils.MenuManager;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Lim Yu Ean, Lai Khairong
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor()
				, new Graveyard(), new GustOfWind(), new PuddleOfWater(), new Cliff(), new Cage(),
				new Barrack());

		List<String> limGrave = Arrays.asList(
				"..nnnn................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"..nnnn................#..___....____#.........................+++++........",
				"......................#...........__#....~~~~~~~~~~~~~~..........++........",
				"......................#_____........#.......~~~~~~~~~~~...........+++......",
				"......................#............_#..........~~~~~~~~............+++.....",
				"......................######...######..............~~~~....................",
				"...........................................................................",
				"....~~~~~~~~~~~~~..........................................................",
				"........++++....~~~~~~~~~~~~~......###___###...............................",
				"........+++++++.....~~~~~~~.......________#.......nnnn.....................",
				"..........+++.........~~~~~.......#________.......nnnn.....................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+............................................................",
				"..............++...........................................................",
				"..............................................++...........................",
				"..................++++..&&&.................+++.......&&&.....######..##...",
				"#####___######....++....&&&....................+++....&&&.....#....____....",
				"_____________#.....++++.&&&......................+....&&&.......__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");
		List<String> stormVeilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		List<String> roundTableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");

		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

		GameMap limGraveMap = new GameMap(groundFactory, limGrave);
		limGraveMap.at(37, 12).setGround(new SiteOfLostGrace("The First Step"));
		world.addGameMap(limGraveMap);

		GameMap stormVeilMap = new GameMap(groundFactory, stormVeilCastle);
		world.addGameMap(stormVeilMap);

		GameMap roundTableMap = new GameMap(groundFactory, roundTableHold);
		world.addGameMap(roundTableMap);

		GameMap bossMap = new GameMap(groundFactory, bossRoom);
		world.addGameMap(bossMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		limGraveMap.at(31, 10).addActor(new LoneWolf());
		limGraveMap.at(32, 11).addActor(new Dog());
		limGraveMap.at(32, 10).addActor(new GodrickSoldier());

		// HINT: what does it mean to prefer composition to inheritance?
		MenuManager menuManager = MenuManager.getInstance();
		Player player;
		int option = menuManager.menu();
		switch (option) {
			case 1 -> player = new Samurai();
			case 2 -> player = new Bandit();
			case 3 -> player = new Wretch();
			default -> player = null;
		}

		world.addPlayer(player, limGraveMap.at(34, 10));
		// limGraveMap.at(33, 10).setGround(new Cliff());
		Location location = limGraveMap.at(35, 10);
		location.addItem(new GoldenRune());

		Location location2 = limGraveMap.at(36, 10);
		location2.addItem(new GoldenSeed());

		Location location3 = limGraveMap.at(37, 10);
		location3.addItem(new PoisonApple());

//		world.addPlayer(player, stormVeilMap.at(5, 0));
		player.setLimGraveMap(limGraveMap);
		limGraveMap.at(38, 10).addActor(new MerchantKale());

		limGraveMap.at(38,13).addActor(new FingerReaderEnia());

		// Limgrave to Roundtable Hold
		GoldenFogDoor limGraveDoorToRoundTable = new GoldenFogDoor(new MoveActorAction(roundTableMap.at(9, 10), "travels to the RoundTable Hold"));
		limGraveMap.at(6, 23).setGround(limGraveDoorToRoundTable);

		// Limgrave to Stormveil Castle
		GoldenFogDoor limGraveDoorToStormVeil = new GoldenFogDoor(new MoveActorAction(stormVeilMap.at(38, 23), "travels to Stormveil Castle"));
		limGraveMap.at(29, 0).setGround(limGraveDoorToStormVeil);

		// Roundtable Hold to Limgrave
		GoldenFogDoor roundTableDoorToLimGrave = new GoldenFogDoor(new MoveActorAction(limGraveMap.at(6, 23), "travels to the Limgrave"));
		roundTableMap.at(9, 10).setGround(roundTableDoorToLimGrave);

		// Stormveil Castle to Limgrave
		GoldenFogDoor stormVeilDoorToLimGrave = new GoldenFogDoor(new MoveActorAction(limGraveMap.at(29, 0), "travels to the Limgrave"));
		stormVeilMap.at(38, 23).setGround(stormVeilDoorToLimGrave);

		// Stormveil Castle to Boss Room
		GoldenFogDoor stormVeilDoorToBossRoom = new GoldenFogDoor(new MoveActorAction(bossMap.at(10, 1), "travels to the Boss Room"));
		stormVeilMap.at(5, 0).setGround(stormVeilDoorToBossRoom);
		world.run();
	}
}
