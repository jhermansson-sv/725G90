
public class Bird extends Animals {

	Bird(String name, int age) {
		super(name, age);
	}
	
		
	public void introduceYourself() {
		System.out.println("jag är en fågel vid namn " + getName());
		System.out.println("Jag är " + getAge() + " år gammal.");

	}
	
	public void makeSound() {
		System.out.println("pip! pip!");
	}
	
	public void fly() {
		System.out.println("Flax, flax, jag flaxar med vingarna!");
	}
	
}

