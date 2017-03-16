/**
 *<p>Here is located the definition of sudoku class.</p>
 * @author Konrad Linkowski
 */
public class Sudoku {

	/** Grid. */
	public Cell table [][];
	/** Constant size of grid. */
	public static final int SIZE = 9;
	private int difficulty;
	/**
	 * Creates blank sudoku grid.
	 */
	public Sudoku () {
		table = new Cell [SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				table [i][j] = new Cell (i, j);
			}
		}
	}
	/**
	 * Creates blank sudoku grid with predefined difficulty.
	 * @param difficulty Difficulty of sudoku between 1 and 64.
	 */
	public Sudoku (int difficulty) {
		table = new Cell [SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				table [i][j] = new Cell (i, j);
			}
		}
		this.difficulty = difficulty;
	}
	
	/**
	 * Creates a copy of existing sudoku object.
	 * @param copy Sudoku which will be copied.
	 */
	public Sudoku (Sudoku copy) {
		table = new Cell [SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				table[i][j] = new Cell (copy.table [i][j], true);
			}
		}
		this.difficulty = copy.difficulty;
	}
	
	/**
	 * Prints sudoku grid to the standard output.
	 * @deprecated
	 */
	public void show () {	// printing table to the standard output
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(table [i][j].getNumber ());
			}
			System.out.println();
		}
		System.out.println ();
		
	}
	/**
	 * @return grid nicely formated to a string.
	 */
	public String getSudokuString () {
		String pomSudS = new String ();
		int i = 0, j = 0;
		while (true) {
			pomSudS += table [i][j].getNumber();
			if (i == 8) {
				j++;
				i = 0;
			}
			if (j == 9) {
				break;
			}
			i++;
		}
		return pomSudS;
	}
	/*
	public Cell getTabEl (int x, int y) {
		return table [x][y];
	}
	
	public void setTabEl (int x, int y, Cell o) {
		table [x][y] = o;
	}
	*/

	/**
	 * Returns difficulty of this sudoku.
	 * @return int
	 */
	public int getDifficulty() {
		return difficulty;
	}
	/**
	 * Setting difficulty of this sudoku.
	 * @param difficulty Difficulty.
	 */
	public void setDifficulty(int difficulty) {
		if (difficulty > 64) {
			this.difficulty = 64;
			return;
		}
		if (difficulty < 9) {
			this.difficulty = 9;
			return;
		}
		this.difficulty = difficulty;
	}
}
