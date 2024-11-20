public class Potion extends Item {

	private int healthPoints;


	public Potion(String name, int healthPoints) {
		super(name);
		this.healthPoints = healthPoints;
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts.length > 1 && parts[0].equalsIgnoreCase("drink")) {
			String item = parts[1];
			drink(item, player);
			return true;
		}
		return false;
	}

	public void drink(String item, Player player) {
		Item potion = player.getItem(item);
		if (potion == null) {
			System.out.println("You don't have " + item + " in your backpack.");
		} else {
			player.setPlayerHealth(player.getHealth() + this.healthPoints);
			System.out.println("You healed for " + this.healthPoints + ".");
			player.getHealth();
			player.inventory.remove(this);
		}
	}
}
