import java.io.FileNotFoundException;

/**
 * <h1>Sudoku main class</h1>
 * <p>A simple sudoku game with generator and solver.</p>
 * @author Konrad Linkowski
*/
public class Main {

	
	public static void main (String[] args) throws FileNotFoundException {
		Sudoku sudoku, test1, test2;
		/*
		sudoku = Generator.generate(253392337025110l, 64);
		sudoku.show();
		
		test1 = Generator.solve(sudoku, true);
		System.out.println(test1.getSudokuString());
		test1.show();
		test2 = Generator.solve(sudoku, false);
		System.out.println(test2.getSudokuString());
		test2.show();
		*/
		for (int i = 0; i < 10; i++) {
			sudoku = Generator.generate(System.nanoTime(), 30);
			System.out.println(sudoku.getDifficulty());
			sudoku.show();
			Generator.solve(sudoku, true).show();;
		}
	}
}
