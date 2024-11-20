import java.util.Random;

public class Monster extends Npc {
	private Item item;
	private boolean interactedWith;
	private int health;
	private int damage;
	private Item drop;

	public Monster(String name, Location home, int health, int damage, Item drop) {
		super(name, home);
		this.interactedWith = false;
		this.health = health;
		this.damage = damage;
		this.drop = drop;
	}

	@Override
	public boolean doCommand(String[] parts, Player player) {
		if (parts.length == 2 && parts[1].equalsIgnoreCase("attack")) {
			String weapon = parts[0];
			attack(player, weapon);
			return true;
		} else if (parts.length >= 1 && parts[0].equalsIgnoreCase("interact")) {
			if (!player.hasAnyWeapon()) {
				System.out.println("I suggest you to find a weapon for this....");
			} else {
				System.out.println("The " + this.getName()
						+ " is charging towards you! Quickly use 'weapon' followed by 'attack' to defend yourself! (eg. knife attack)");
				Game.setInFight(true);
			}
		}
		return true;
	}

	public void attack(Player player, String weaponName) {
		Item weapon = player.getItem(weaponName);
		if (weapon == null) {
			System.out.println("You do not have a " + weaponName + " in your inventory.");
		} else if (player.hasAnyWeapon()) {
			int damage = ((Weapon) weapon).getDamage();
			health -= damage;
			System.out.println("You attacked the " + this.getName() + " with a " + weaponName + " and dealt " + damage
					+ " damage.");
			System.out.println(this.getName() + "'s current health is: " + health);
			if (health <= 0) {
				System.out.println("The " + this.getName() + " has died. But your " + weapon.getName()
						+ " also broke. However, you recieved a " + this.drop.getName() + "."
						+ " Sip it up by doing 'drink " + this.drop.getName() + "'.");
				player.inventory.remove(weapon);
				player.inventory.add(this.drop);
				Game.setInFight(false);
				player.getLocation().removeLocalNpc();
				return;
			}

			// Monster attacks player with a random chance
			Random random = new Random();
			int chance = random.nextInt(100);
			if (chance < 25) {
				int monsterDamage = this.getDamage();
				player.setPlayerHealth(player.getHealth() - monsterDamage);
				System.out.println(this.getName() + " attacked you and dealt " + monsterDamage + " damage.");
				System.out.println("Your current health is: " + player.getHealth());
				if (player.getHealth() <= 0) {
					System.out.println("You were killed by the " + this.getName() + ", GAME OVER!");
					Game.setGameOver(true);
				}
			}
		} else {
			System.out.println("The item is not a weapon and cannot be used to attack the monster.");
		}
	}

	private int getDamage() {
		return this.damage;
	}

}
