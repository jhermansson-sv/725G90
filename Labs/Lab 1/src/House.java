import java.util.ArrayList;

public class House {
	private Animals animals;
	
	ArrayList<Animals> animalList = new ArrayList<Animals>(); 

	public void addAnimal(Animals animals) {
		animalList.add(animals);
	}
	
	public void print() {
	    System.out.println("Följande djur finns i huset:");
	    
	    for (Animals animal : animalList) {
	        animal.print();
	    }
	}
}
