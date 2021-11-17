import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * This class is created to create a path for the drone to go from the UWO Store to the customer's house
 * This class is created by Dhyey Patel 
 */


public class ComputePath {
	private Map cityMap;
	
	// Constructor of the class, that runs the constructor of Map 
	public ComputePath(String fileName) throws InvalidMapException, FileNotFoundException, IOException {
		cityMap = new Map(fileName);
	}
	
	// private method to get the location of the UWOStore
	private MapCell getStart() {
		return cityMap.getStart();
	}
	
	// private method to check if any of the cells next to the current cell is a tower cell
	private boolean interferance(MapCell cell) {
		MapCell neighbourCell;
		for (int i=0; i <= 5; i++) {
			neighbourCell = cell.getNeighbour(i);
			if (neighbourCell != null) {
				if (neighbourCell.isTower()) {
					return true;
				}
			}
		}
		return false;
	}
	
	// Private method to find the best cell to go to, and returns null if there are no possible elements
	private MapCell nextCell(MapCell cell) {
		MapCell next;
		for (int i = 0; i<6; i++) {
			next = cell.getNeighbour(i);
			if (next != null) {
				if (!(next.isMarked()) && next.isCustomer()) {
					return next;
				}
			}		
		}
		for (int i = 0; i<6; i++) {
			next = cell.getNeighbour(i);
			if (next != null) {
				if (!(next.isMarked()) && (next.isFree())) {
					return next;
				}
			}		
		}
		for (int i = 0; i<6; i++) {
			next = cell.getNeighbour(i);
			if (next != null) {
				if (!(next.isMarked()) && next.isHighAltitude()) {
					return next;
				}	
			}		
		}
		for (int i = 0; i<6; i++) {
			next = cell.getNeighbour(i);
			if (next != null) {
				if (!(next.isMarked()) && next.isThief()) {
					return next;
				}
			}			
		}
		return null;
	}
	
	public static void main(String[] args) {
		// try catch block to catch all the possible exceptions that can be thrown
		try {
			MapCell currentCell,nextCell, poppedCell;
			ComputePath path = new ComputePath(args[0]);
			MyStack pathStack = new MyStack();
			currentCell= path.getStart();
			pathStack.push(currentCell);
			currentCell.markInStack();
			// go through the loop until the stack is not empty or the customer cell is found
			while (!(pathStack.isEmpty()) && !(((MapCell) pathStack.peek()).isCustomer())) {
				currentCell = (MapCell) pathStack.peek();
				// check for interference first, and remove the element if there is interference
				if (path.interferance(currentCell)) {
					poppedCell = (MapCell) pathStack.pop();
					poppedCell.markOutStack();
				}
				// if there is no interference then find the next cell, using the nextCell method
				else {
					nextCell = path.nextCell(currentCell);
					if (nextCell == null) {
						poppedCell = (MapCell) pathStack.pop();
						poppedCell.markOutStack();
					}
					else {
						currentCell = nextCell;
						pathStack.push(currentCell);
						currentCell.markInStack();
					}
				}
			}
			// print if the destination was reached or not
			if (pathStack.isEmpty()) {
				System.out.println("The destination cannot be reached");
			}
			else {
				System.out.println("The destination has been reached in "+(pathStack.size()-1)+" cell(s)");
			}
			
		} 
		// handle all the exceptions, and print the error message
		catch (EmptyStackException e) {
			System.out.println("The stack is empty");
		}
		catch (OverflowException e) {
			System.out.println("Too many items in the Stack");
		}
		catch (InvalidMapException e) {
			System.out.println("Map cannot be made :(");
		}
		catch(FileNotFoundException e) {
			System.out.println("The file cannot be found");
		}
		catch (IOException e) {
			System.out.println("There is an Input/Output Error");
		}

	}

}
