import java.util.ArrayList;
/**
 * <h1>Definition of single sudoku cell</h1>
 * @author Konrad Linkowski
 */
public class Cell {
	
	/** List of available numbers for that cell. */
	ArrayList <Integer> availableNumbers = new ArrayList <Integer> ();
	
	/** Vertical position. */
	private int x;
	/** Horizontal postion. */
	private int y;
	/** Current digit of this cell. */
	private int number;
	/** Tells if cell is able to be changed. */
	private boolean changeable;
	/**
	 * Creates cell.
	 */
	public Cell () {
		resetAvailableNumbers();
	}
	/**
	 * Creates cell.
	 * @param x Vertical position of cell.
	 * @param y Horizontal position of cell.
	 */
	public Cell (int x, int y) {
		resetAvailableNumbers();
		this.x = x;
		this.y = y;
	}
	/**
	 * Creates cell.
	 * @param x Vertical position of cell.
	 * @param y Horizontal position of cell.
	 * @param number New digit.
	 * @param changeable Sets cells changeable.
	 */
	public Cell (int x, int y, int number, boolean changeable) {
		resetAvailableNumbers ();
		this.x = x;
		this.y = y;
		this.number = number;
		this.changeable = changeable;
	}
	/**
	 * Creates copy of cell.
	 * @param copy Cell to copy.
	 * @param resetNumbers Should it reset available numbers?.
	 */
	public Cell (Cell copy, boolean resetNumbers) {
		if (resetNumbers) {
			resetAvailableNumbers ();
		}
		this.x = copy.x;
		this.y = copy.y;
		this.number = copy.number;
		this.changeable = copy.changeable;
	}
	/**
	 * Resets available numbers.
	 */
	public void resetAvailableNumbers () {
		availableNumbers.clear();
		for (int i = 1; i <= 9; i++) {
			availableNumbers.add (new Integer (i));
		}
	}
	/**
	 * Remove specified number from cells available numbers.
	 * @param x Number to remove.
	 */
	public void removeNumber (int x) {
		availableNumbers.remove (new Integer (x));
	}
	/**
	 * @return Vertical postion.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Sets vertical position of cell.
	 * @param x New vertical postion.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return Horizontal position.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets horizontal postion of cell.
	 * @param y New horizontal position.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return Current cell's digit.
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Sets current cell's digit.
	 * @param number New digit.
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return true if cell is able to be changed else false
	 */
	public boolean isChangeable() {
		return changeable;
	}
	/**
	 * Sets cell's ability/disability to be changed.
	 * @param changeable true - true; false - false
	 */
	public void setChangeable(boolean changeable) {
		this.changeable = changeable;
	}
}
