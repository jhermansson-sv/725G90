class Main {

	public static void main(String[] args) {

		Animals.setYear(2020);

		Animals kurre = new Cat("Kurre", 6);
		Animals vilma = new Dog("Vilma", 3);
		Animals bamse = new Cat("Bamse", 12);
		Animals smilla = new Dog("Smilla", 1);

		Toy ball = new Toy("Boll");
		Toy shoe = new Toy("Tuggsko");
		Toy mouse = new Toy("Plastmus");

		House house = new House();

		house.addAnimal(kurre);
		house.addAnimal(vilma);
		house.addAnimal(bamse);
		house.addAnimal(smilla);

		kurre.setFriend(vilma);
		vilma.setFriend(smilla);
		bamse.setFriend(kurre);

		kurre.addToy(ball);
		kurre.addToy(mouse);
		vilma.addToy(shoe);
		vilma.addToy(ball);

		house.print();
		
		
	}
}
