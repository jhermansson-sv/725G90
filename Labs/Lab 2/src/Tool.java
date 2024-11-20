public class Tool extends Item {

	public Tool(String name) {
		super(name);
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts.length > 1 && parts[0].equalsIgnoreCase("use")) {
			String item = parts[1];
			use(item, player);
			return true;
		}
		return false;
	}

	public boolean getIsTool() {
		return true;
	}

	public void use(String item, Player player) {
		for (Item i : player.inventory) {
			if (i.getName().equals(item) && player.inventory.contains(i)) {
				System.out.println("You used " + item);
				break;
			} else {
				System.out.println("You don't have " + item + " in your backpack");
				break;
			}
		}
	}
}
