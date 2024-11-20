
public class Dog extends Animals {
	
	Dog(String name, int age) {
		super(name, age);
	}
	
		
	public void introduceYourself() {
		System.out.println("Vovv!! Jag är " + getName());
		System.out.println("Jag är " + getAge() + " år gammal.");
	}
	
	public void makeSound() {
		System.out.println("Vof, vov, vovvvv!!!");
	}
	
}
