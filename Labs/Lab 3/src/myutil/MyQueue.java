package myutil;


public class MyQueue<E> {

	private Node<E> front;
	private Node<E> rear;
	int size;

	public MyQueue() {
		size = 0;
		front = null;
		rear = null;
	}

	public void enqueue(E element) {
		Node<E> newNode = new Node<>(element, null);
		if (rear == null) {
			front = newNode;
			rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		size++;
	}

	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Error");
		} else {
			E element = front.getValue();
			front.value = null;
			front = front.getNext();
			if (front == null) {
				rear = null;
			}
			size--;
			return element;
		}
	}

	public boolean isEmpty() {
		return front == null;
	}

	public int size() {
		return size;
	}

	public static void queueTest() {
		MyQueue<String> testQueue = new MyQueue<String>();
		testQueue.enqueue("Jonte");
		testQueue.enqueue("Melvin");
		testQueue.enqueue("Peter");
		testQueue.enqueue("Max");

		try {
			System.out.println(testQueue.dequeue());
			System.out.println(testQueue.dequeue());
			System.out.println(testQueue.dequeue());
			System.out.println(testQueue.dequeue());
			System.out.println(testQueue.dequeue());
		} catch (EmptyQueueException e ) {
			System.out.println("The queue is empty");

		} 
	}
}