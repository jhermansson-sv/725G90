
public class Human extends Npc {

	private Item item;
	private boolean interactedWith;

	public Human(String name, Location home, Item item) {
		super(name, home);
		this.item = item;
		this.interactedWith = false;
		
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts[0].equalsIgnoreCase("interact")) {
			interactWith(player);
			return true;
		}
		return false;
	}

	public void interactWith(Player player) {
		if (!interactedWith) {
			System.out.println(this.getName() + " gives you a " + this.item.getName() + "!");
			player.inventory.add(this.item);
			this.interactedWith = true;
		} else {
			System.out.println("I already provided you with what I had, get out there and explore further.");
		}
	}
}
