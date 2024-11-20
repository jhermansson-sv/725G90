public class Shovel extends Tool {

	public Shovel(String name) {
		super(name);
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts[0].equalsIgnoreCase("dig")) {
			handleDigCommand(player);
			return true;
		}
		return false;
	}

	private boolean handleDigCommand(Player player) {
		if (!player.checkIfItemInInventory("shovel")) {
			System.out.println("You don't have shovel in your backpack");
			return true;
		}

		if (!player.getLocation().getDiggable()) {
			System.out.println("You can't dig here");
			return true;
		}
		
		if (!player.getLocation().getHasDigged()) {
			System.out.println(player.getLocation().getDigMessage());
			player.getLocation().setHasDigged(true);
			return true;
		} else {
			System.out.println("You already dug here");
			return true;
		}
	}
}