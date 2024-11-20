import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Location implements Commandable {
	private Location location;	
	private Npc localNpc;
	private String name;
	private String shortDescription;
	private String description;
	private String pathType;
	private boolean visited;
	private boolean hasDigged;	
	private boolean hasLight;
	private Map<String, Exit> exits;
	private ArrayList<Item> items;

	Location(String name, String shortDescription, String description) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
		this.visited = false;
		this.hasDigged = false;
		this.hasLight = true;
		this.pathType = "road";

		exits = new HashMap<>();
		items = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public boolean getHasLight(Location location) {
		return this.hasLight;
	}

	public String getPathType() {
		return this.pathType;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public boolean getDiggable() {
		return false;
	}
	
	public void setHasDigged(boolean hasDigged) {
		this.hasDigged = hasDigged;
	}

	public boolean getHasDigged() {
		return hasDigged;
	}

	public String getDigMessage() {
		return "no diggity";
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts.length > 1 && parts[0].equalsIgnoreCase("take")) {
			String item = parts[1];
			pickItem(item, player);
			return true;
		} else if (parts[0].equalsIgnoreCase("look")) {
			describeYourself();
			return true;
		} else {
			return false;
		}
	}

	public void addExit(String direction, Exit exit) {
		exits.put(direction, exit);
	}

	public Exit getExit(String direction) {
		return exits.get(direction);
	}

	public Location getLocation() {
		return location;
	}

	public boolean getIfOutside() {
		return false;
	}

	public void randomizeWeathers() {
		Collections.shuffle(initLocations.getWeathers());
		String randomWeather = initLocations.getWeathers().get(0);
		System.out.println(randomWeather);
	}

	public void describeYourself() {
		System.out.println("\n" + getDescription() + "\n");

		if (getIfOutside()) {
			System.out.print("The weather is ");
			randomizeWeathers();
		}
		printPossibleActions();
	}

	public String getDescription() {
		if (visited) {
			return shortDescription;
		} else {
			visited = true;
			return description;
		}
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public void printAvailableItems() {
		if (items != null && !items.isEmpty()) {
			System.out.print("Items in this location: |");
			for (Item i : items) {
				System.out.print(" " + i.getName() + " |");
			}
			System.out.println(" pick up items by using command 'take' (eg. take chestplate).");
		} else {
			System.out.println("There are no items here.");
		}
	}

	public void pickMessage(Item pickedItem) {
		System.out.println("\nYou found a " + pickedItem.getName());

		if (pickedItem.getIsWearable()) {
			System.out.println("Use command 'wear " + pickedItem.getName() + "' to put it on!");
			return;
		} else if (pickedItem.getIsWeapon()) {
			System.out.println("Use it to fight nasty creatures!");
			return;
		} else if (pickedItem.getIsTool()) {
			System.out.println("Use command 'dig' to use it!");
			return;
		}
	}

	public void pickItem(String item, Player player) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equalsIgnoreCase(item)) {
				Item pickedItem = items.get(i);
				player.inventory.add(pickedItem);
				removeItem(pickedItem);
				pickMessage(pickedItem);
				return;
			}
		}
		System.out.println("You can't pick up " + item);
	}

	public void printAvailableDirections() {
		System.out.print("\n");
		for (String direction : exits.keySet()) {
				System.out.println(
						"There is a " + getExit(direction).getDestination().getPathType() + " leading " + direction);
		}
		return;
	}

	public void addLocalNpc(Npc newNpc) {
		localNpc = newNpc;
	}

	public void removeLocalNpc() {
		localNpc = null;
	}

	public Npc getLocalNPC() {
		return localNpc;
	}

	public void printLocalNpc() {
		if (localNpc != null) {
			System.out.println("This is the home of " + localNpc.getName() + " 'interact' to see what's up!\n");
		}
	}

	public void printPossibleActions() {
		printLocalNpc();
		printAvailableItems();
		printAvailableDirections();
	}

}
