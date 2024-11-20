public class WearableItem extends Item {

	private int healthPoints;
	private WearableItem wearable;
	private boolean isWeapon;

	WearableItem(String name, Integer healthPoints) {
		super(name);
		this.healthPoints = healthPoints;
		this.isWeapon = false;
	}

	@Override
	public int getItemHealthPoints(Item item) {
		return this.healthPoints;
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts.length > 1 && parts[0].equalsIgnoreCase("wear")) {
			String item = parts[1];
			handleWearCommand(item, player);
			return true;
		}
		return false;
	}

	@Override
	public WearableItem getWearable() {
		return wearable;
	}

	@Override
	public boolean getIsWearable() {
		return true;
	}

	@Override
	public boolean getIsWeapon() {
		return isWeapon;
	}

	private boolean handleWearCommand(String item, Player player) {
		if (player.getInventory() == null || player.getInventory().isEmpty()) {
			System.out.println("Your backpack is empty.");
			return true;
		}
		
		Item wearable = player.getItem(item);
		if (wearable == null) {
			System.out.println("You don't have " + item + " in your backpack.");
			return true;
		}

		if (player.currentlyWearing.contains(wearable)) {
			System.out.println("You are already wearing " + item);
			return true;
		}

		if (player.getItem(item).getIsWearable() ) {
			System.out.println("You are now wearing " + item + ", +" + wearable.getItemHealthPoints(wearable) + " health.");
			player.currentlyWearing.add(wearable);
			player.getInventory().remove(wearable);
			player.setPlayerHealth(player.getHealth() + wearable.getItemHealthPoints(wearable));
			return true;
	}
	else {
		System.out.println("This item is not wearable");
		return true;
	}

}
}
