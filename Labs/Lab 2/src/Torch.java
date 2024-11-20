
public class Torch extends Tool {
	
	private boolean isLit;


	public Torch(String name) {
		super(name);
		this.isLit = false;

	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts[0].equalsIgnoreCase("light")) {
			light();
			return true;
		}
		return false;
	}

	public void light() {
		System.out.println("Torch is lit");
	}

	public boolean getIsLit(Item item) {
		return isLit;
	}

	public void setIsLit() {
		this.isLit = true;
	}

}
