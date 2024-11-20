package myutil;

import java.util.EmptyStackException;

public class MyStack<E> {

	int size;
	Node<E> top;

	public MyStack() {
		size = 0;
		top = null;
	}

	public void push(E newValue) {
		Node<E> newElement = new Node<E>(newValue, top);
		top = newElement;
		size++;
	}

	public E pop() {
		Node<E> oldTop = top;
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		top = top.getNext();
		size--;
		return oldTop.getValue();
	}

	public E peek() {
		if (size == 0) {
			return null;
		}
		return top.getValue();
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public void empty() {
		size = 0;
		top = null;
	}

	public static void stackTest() {
		MyStack<String> myStringStack = new MyStack<String>();

		myStringStack.push("Melvin");
		myStringStack.push("Jonte");
		

		System.out.println(myStringStack.peek());

		try {
			System.out.println(myStringStack.pop());
			System.out.println(myStringStack.pop());
			System.out.println(myStringStack.pop());
		} catch (EmptyStackException e) {
			System.out.println("The stack is empty.");
		}
	}

}
