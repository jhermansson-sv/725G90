public abstract class Item implements Commandable {
	
	private String name;
	private Item item;
	private int healthPoints;

	public Item(String name) {
		this.name = name;
	}

	@Override
	public abstract boolean doCommand(String[] parts, Player player);

	public String getName() {
		return this.name;
	}

	public boolean getIsWeapon() {
		return false;
	}

	public boolean getIsWearable() {
		return false;
	}
	
	public boolean getIsTool() {
		return false;
	}

	public int getItemHealthPoints(Item item) {
		return this.healthPoints;
	}

	public void printYourself() {
	}

	public Item getWearable() {
		return item;
	}
}
