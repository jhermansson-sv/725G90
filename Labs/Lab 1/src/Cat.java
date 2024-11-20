
public class Cat extends Animals {

	Cat(String name, int age) {
		super(name, age);
	}
		
	public void introduceYourself() {
		System.out.println("Meow, jag är " + getName());
		System.out.println("Jag är " + getAge() + " år gammal.");
	}
	
	public void makeSound() {
		System.out.println("Purrrrrrr");
	}
	
}
