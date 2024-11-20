import java.util.ArrayList;
import java.util.Random;

public class initLocations {
	ArrayList<Location> locations;
	static ArrayList<String> weathers;

	public void init() {
		// Creating list of locations
		locations = new ArrayList<>();

		weathers = new ArrayList<>();
		weathers.add("Sunny: A bright and sunny day with clear blue skies and warm temperatures.");
		weathers.add("Rainy: A rainy day with pouring rain, thunderstorms, and cool temperatures.");
		weathers.add("Windy: A windy day with gusty winds and a mix of sun and clouds.");
		weathers.add("Snowy: A snowy day with heavy snowfall and cold temperatures.");
		weathers.add("Cloudy: A cloudy day with overcast skies and moderate temperatures.");

		// Creating new locations
		Location startingLocation = new OutdoorsArea("Starting location", "You are at the starting location.",
				"A plain open field with tall grass swaying in the breeze. "
						+ "The surrounding trees offer some shade. At first glance it seems like nothing bad could ever happen here."
						+ "\nBut the village is counting on you and you have to explore further to find out what is going on.",
				weathers,
				"Nothing here");
		Location Lake = new OutdoorsArea("Lake", "You are at the lake.",
				"A peaceful lake surrounded by lush trees and rolling hills with clear, calm water and soft sandy beaches.",
				weathers, "Sand, sand and more sand.");
		Location Jungle = new OutdoorsArea("Jungle", "You are in the jungle.",
				"A dense and lush jungle filled with exotic plants and animals and a maze-like interior.", weathers,
				"Dirt, dirt and more dirt.");

		Location Cave = new Room("Cave", "You are in the cave.",
				"A dark and ominous cave with cold stone walls. In the center of the cave lies a large stone sarcophagus with strange symbols etched into its surface.",
				25, false);
		Location Castle = new Room("Castle", "You are at the castle.",
				"A grand castle with rough stone walls, jagged turrets, and a musty interior. Adorned with ancient tapestries and guarded by suits of armor.",
				75, true);

		// Creating new items
		Item chestplate = new WearableItem("chestplate", 15);
		Item helmet = new WearableItem("helmet", 10);
		Item shield = new WearableItem("shield", 25);

		Item shovel = new Shovel("shovel");
		Item torch = new Torch("torch");
		Item healthPotion = new Potion("healthpotion", 35);

		Item sword = new Weapon("sword", 25);
		Item knife = new Weapon("knife", 25);

		// Creating npc
		Npc Tarzan = new Human("Tarzan", Jungle, torch);
		Npc Monster = new Monster("Monster", Lake, 100, 75, healthPotion);
		Npc Villager = new Human("Villager", startingLocation, sword);

		// Adding NPCs to locations
		Jungle.addLocalNpc(Tarzan);
		Lake.addLocalNpc(Monster);
		startingLocation.addLocalNpc(Villager);

		// Adding items to locations
		startingLocation.addItem(chestplate);
		Castle.addItem(shovel);
		Castle.addItem(helmet);
		Cave.addItem(knife);
		Cave.addItem(sword);
		Cave.addItem(shield);

		// Adding locations to the ArrayList "locations"
		locations.add(startingLocation);
		locations.add(Castle);
		locations.add(Lake);
		locations.add(Jungle);
		locations.add(Cave);

		// Adding exits to locations
		Exit startingLocationToCastle = new Exit(locations.get(1), "Castle", "You see a castle!");
		Exit castleToStartingLocation = new Exit(locations.get(0), "Starting Location",
				"You see your starting location");
		Exit castleToLake = new Exit(locations.get(2), "Lake", "You see a lake!");
		Exit lakeToCastle = new Exit(locations.get(1), "Castle", "You see a castle!");
		Exit lakeToJungle = new Exit(locations.get(3), "Jungle", "You see a ");
		Exit jungleToLake = new Exit(locations.get(2), "Lake", "You see a lake!");
		Exit castleToCave = new Exit(locations.get(4), "Cave", "This is the cave");
		Exit caveToCastle = new Exit(locations.get(1), "Castle", "You see a castle!");

		// Adding which direction the exit is
		locations.get(0).addExit("north", startingLocationToCastle);
		locations.get(1).addExit("south", castleToStartingLocation);
		locations.get(1).addExit("west", castleToLake);
		locations.get(2).addExit("east", lakeToCastle);
		locations.get(2).addExit("south", lakeToJungle);
		locations.get(3).addExit("north", jungleToLake);
		locations.get(1).addExit("north", castleToCave);
		locations.get(4).addExit("south", caveToCastle);

	}

	public static ArrayList<String> getWeathers() {
		return weathers;
	}
}
