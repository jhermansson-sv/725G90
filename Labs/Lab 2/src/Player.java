import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Commandable {
	private String name;
	private Location location;
	private WearableItem wearable;
	private int health;
//	private boolean hasWeapon;
	ArrayList<Item> inventory = new ArrayList<>();
	ArrayList<Item> currentlyWearing = new ArrayList<>();

	public Player(String name, Location currentLocation) {
		this.name = name;
		this.location = currentLocation;
		this.health = 100;
//		this.hasWeapon = false;
	}

	public boolean checkIfItemInInventory(String item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getName().equalsIgnoreCase(item)) {
				return true;
			}
		}
		return false;
	}

	public void moveTo(String direction) {
		Exit exit = location.getExit(direction);
		Scanner keyboard = new Scanner(System.in);

		if (exit == null) {
			System.out.println("You cannot move " + direction + ".");
			return;
		}

		Location destination = exit.getDestination();
		if (!destination.getHasLight(getLocation())) {
			if (!checkIfItemInInventory("Torch")) {
				System.out.println("Looks dark. Return with a lightsource!");
				return;
			}
			System.out.println("It's dark in there, you need to light your torch to enter (use command 'light'): ");
			String isLit = keyboard.nextLine();
			if (!isLit.equalsIgnoreCase("light")) {
				System.out.println("You need to light your torch to enter.");
				return;
			}
		}
		location = destination;
		System.out.println("You move " + direction + ". ");
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		switch (parts[0].toLowerCase()) {
		case "go":
			return handleGoCommand(parts);
		case "backpack":
			return handleBackpackCommand();
		case "health":
			return handleHealthCommand();
		case "help":
			return handleHelpCommand();
		default:
			return false;
		}
	}

	private boolean handleGoCommand(String[] parts) {
		if (parts.length > 1) {
			moveTo(parts[1]);
			Game.setMovedLocation(true);
			return true;
		}
		return false;
	}

	private boolean handleBackpackCommand() {
		if (inventory != null && !inventory.isEmpty()) {
			System.out.println("This is in your backpack: ");
			for (Item name : inventory) {
				System.out.print(name.getName() + "\n");
			}
		} else {
			System.out.println("There is nothing in your backpack.");
		}
		return true;
	}

	private boolean handleHealthCommand() {
		System.out.println("Current health: " + health + ".");
		return true;
	}

	private boolean handleHelpCommand() {
		System.out.println(
				"Commands:\ngo 'location' \ntake 'item' \nuse 'item' \nwear 'item' \n'backpack'  \n'dig' \n'health'");
		return true;
	}

	public int getHealth() {
		return health;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public String getName() {
		return this.name;
	}

	public Item getItem(String itemName) {
		for (Item item : inventory) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public void setPlayerHealth(int health) {
		this.health = health;
	}

	public boolean hasAnyWeapon() {
		for (Item i : inventory) {
			if (i.getIsWeapon()) {
			return true;
			}
		}
		return false;
	}

}