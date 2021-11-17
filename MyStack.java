/*
 * This class is created to implement the StackADT class
 * It will have the methods described in the interface
 * This class is created by Dhyey Patel 
 */


public class MyStack<T> implements MyStackADT<T> {
	// Initialize the needed variables for the class
	private T[] arrayStack;
	private int numItems=0, maximumSize, size;
	private final int upperBound = 1000000;
	
	// This is the constructor with no parameters, it will create an array of size 10, and will set the max size to 1000
	public MyStack() {
		arrayStack = (T[]) new Object[10];
		size = 10;
		maximumSize = 1000;
	}
	
	// This is the constructor with two parameters, it will create an array of the given size, and set the max size to the given amount
	public MyStack(int initialSize, int maxCap) {
		arrayStack = (T[]) new Object[initialSize];
		size = initialSize;
		maximumSize = maxCap;
	}
	
	// Private method that will expand the capacity of the arrayStack according to its size
	private void expand() {
		T[] largeArrayStack;
		if (size>=60 && size<upperBound){
			size = size+100;
			largeArrayStack = (T[])new Object[size];
		}
		else {
			size = size*3;
			largeArrayStack = (T[])new Object[size];
		}
		for (int i=0; i<arrayStack.length; i++) {
			largeArrayStack[i] = arrayStack[i];
		}
		arrayStack = largeArrayStack;
	}
	
	// Method to add an item to the top of the arrayStack, throws an exception if the size > maximumSize
	public void push (T dataItem) throws OverflowException{
		if (numItems+1>maximumSize) {
			throw new OverflowException("MORE ITEMS THAN MAXIMUM SIZE");
		}
		else if(numItems+1>size) {
			expand();
			arrayStack[numItems] = dataItem;
			numItems++;
		}
		else {
			arrayStack[numItems] = dataItem;
			numItems++;
		} 			
	}
	
	// Method to remove the top item from the arrayStack and return that element, throws an exception if the arrayStack is empty 
	public T pop () throws EmptyStackException{
		if(numItems == 0) {
			throw new EmptyStackException("There are no elements");
		}
		else {
			numItems--;
			return(arrayStack[numItems]);
		}
	}
	
	//Method to look at the top item, throws an exception if the arrayStack is empty 
	public T peek () throws EmptyStackException{
		if(numItems == 0) {
			throw new EmptyStackException("There are no elements");
		}
		else {
			return(arrayStack[numItems-1]);
		}
	}
	
	//Method to check if the arrayStack is empty 
	public boolean isEmpty() {
		if (numItems == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Method to get the size of the arrayStack
	public int size() {
		return numItems;
	}
	
	// Method to return each element as a string 
	public String toString() {
		String result = "";
		for (int index=0; index < numItems; index++) {
				result = result + arrayStack[index].toString( ) + "\n";
		}
		return result;
	}

}
