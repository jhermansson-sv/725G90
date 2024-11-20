import java.util.ArrayList;

public class Animals {
	private static int currentYear;
	private String name;
	private int birthyear;
	private Animals friend;
	private Toy toy;

	ArrayList<Toy> ToyList = new ArrayList<Toy>();

	Animals(String name, int age) {
		this.name = name;
		this.birthyear = Animals.currentYear - age;
	}

	public void introduceYourself() {
		System.out.println("Morr, jag är ett djur som heter" + getName());
		System.out.println("Jag är " + getAge() + " år gammal.");
	}

	public void fly() {
		System.out.println("Jag kan inte flyga!");
	}

	public void makeSound() {
		System.out.println("Hmmhmm");
	}

	public static void setYear(int currentYear) {
		Animals.currentYear = currentYear;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return Animals.currentYear - this.birthyear;
	}

	public void setFriend(Animals friend) {
		this.friend = friend;
	}

	public void print() {
		introduceYourself();
		if (friend != null) {
			System.out.println("\nHär är uppgifter om min kompis:");
			friend.introduceYourself();
			if (friend.hasToy()) {
				System.out.println("Jag har följande leksaker:");
				friend.printToy();
			} else {
				System.out.println("Jag har ingen leksak\n");
			}
		} else {
			System.out.println("Jag har ingen kompis.\n");
		}
	}

	public void printToy() {
		for (Toy toy : ToyList) {
			toy.print();
		}
		System.out.println("\n");
	}

	public void addToy(Toy toy) {
		ToyList.add(toy);
	}

	public Boolean hasToy() {
		if (!ToyList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}