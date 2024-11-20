import java.util.*;

public class Game {
	private Scanner keyboard;
	private Player player;
	private ArrayList<Commandable> AllCommandableObjects = new ArrayList<>();
	private static boolean gameOver = false;
	private static boolean inFight = false;
	private static boolean movedLocation = true;

	public static void setGameOver(boolean value) {
		gameOver = value;
	}

	public static void setInFight(boolean value) {
		inFight = value;
	}

	public static void setMovedLocation(boolean value) {
		movedLocation = value;
	}

	public Game() {
		keyboard = new Scanner(System.in);
	}

	public void run() {

		initLocations loc = new initLocations();
		loc.init();

		String name;

		System.out.println("Welcome to the adventure game!\nWhat is your name?");
		name = keyboard.nextLine();
		player = new Player(name, loc.locations.get(0));
		System.out.println("Hello " + name
				+ ", we have been waiting for you! You need to help us. The monster by the lake has been terrorizing the village for eternity. Gear up and kill the monster and you will forever be our hero! \nYou can move around by typing 'go' followed by north/south/west/east. You will have to learn more commands as you play the game! (Hint: there is a command \"help\").");

		while (!gameOver) {

			if (movedLocation) {
				if (!inFight) {
					player.getLocation().describeYourself();
				}
			}

			this.AllCommandableObjects.clear();
			AllCommandableObjects.add(player);
			AllCommandableObjects.addAll(player.getInventory());
			AllCommandableObjects.add(player.getLocation());
			AllCommandableObjects.add(player.getLocation().getLocalNPC());
			AllCommandableObjects.addAll(player.getLocation().getItems());

			String command;

			System.out.println("\nWhat do you want to do?");
			command = keyboard.nextLine();
			String[] parts = command.split(" ");

			setMovedLocation(false);

			boolean commandExecuted = false;
			for (Commandable com : AllCommandableObjects) {
				if (com != null) {
					commandExecuted = com.doCommand(parts, player);
					if (commandExecuted) {
						break;
					}
				}
			}
			if (!commandExecuted) {
				System.out.println("Invalid command. (Hint: there is a command \"help\").");
			}
			System.out.println("_________________________________________");
		}
	}
}
