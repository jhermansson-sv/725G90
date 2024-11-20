public class Weapon extends Item {

	private int damage;


	public Weapon(String name, int damage) {
		super(name);
		this.damage = damage;
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		return false;
	}

	@Override
	public boolean getIsWeapon() {
		return true;
	}

	public int getDamage() {
		return damage;
	}
}
