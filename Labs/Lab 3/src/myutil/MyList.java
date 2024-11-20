package myutil;

public class MyList<E> {

	private int size;
	private Node<E> first;
	private Node<E> last;

	public MyList() {
		size = 0;
		first = null;
		last = null;
	}

	public void add(E element) {
		Node<E> newEntry = new Node<E>(element, last);
		if (last == null) {
			first = newEntry;
			last = newEntry;
		} else {
			last.next = newEntry;
			last = newEntry;
		}
		size++;
	}

	public E getElementAt(int i) throws OutOfBoundsException {
		if (i < 0 || i >= size) {
			throw new OutOfBoundsException("Error");
		}

		Node<E> current = first;
		int counter = 0;

		while (counter < i) {
			current = current.next;
			counter++;
		}
		return current.value;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public static void listTest() {
		MyList<String> TestList = new MyList<String>();
		TestList.add("Melvin");
		TestList.add("Jonte");
		TestList.add("Peter");
		TestList.add("Max");

		try {
	    System.out.println(TestList.getElementAt(0));
		System.out.println(TestList.getElementAt(3));
		System.out.println(TestList.getElementAt(10));
		} catch (OutOfBoundsException e) {
		System.out.println("Out of bounds");
		}
		
	}
}
