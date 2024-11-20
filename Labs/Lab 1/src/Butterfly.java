
public class Butterfly extends Animals {
	
	
	Butterfly(String name, int age) {
		super(name, age);
	}
	
		
	public void introduceYourself() {
		System.out.println("Hej, jag är fjärilen " + getName());
		System.out.println("Jag är " + getAge() + " år gammal.");

	}
	
	public void fly() {
		System.out.println("Flax, flax, jag flaxar med vingarna!");
	}
	
}

