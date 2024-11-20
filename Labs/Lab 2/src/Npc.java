
public abstract class Npc implements Commandable {

	private String name;
	private Location home;
	private boolean interactedWith;

	public Npc(String name, Location home) {
		this.name = name;
		this.home = home;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts[0].equalsIgnoreCase("interact")) {
			if (!interactedWith) {
				System.out.println("Welcome to my home");
			} else {
				System.out.println("You again....");
			}
			return true;
		}
		return false;
	}

	public void describeYourself() {
		getDescription();
	}

	public void getDescription() {
		System.out.println("Hello! I am " + getName() + " welcome to my home, the " + this.home.getName());
	}

	public Location getHome() {
		return this.home;

	}
}
