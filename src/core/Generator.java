package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
/**
 *<p>Class for generating, solving, saving loading sudoku.</p>
 * @author Konrad Linkowski
 */
public class Generator {

	/** Seed. */
	private static long randomSeed; //System.nanoTime();
	/** Random object */
	private static Random ran = new Random (randomSeed);
	/** Saves given sudoku to a specified file.
	 * @param filename Path to a file with name.
	 * @param sudoku Sudoku.
	 * @return boolean
	 * @deprecated Isn't finished yet.
	*/
	public static boolean saveToFile (String filename, Sudoku sudoku) {
		return false;
	}
	/**
	 * Loading data from specified file and creates sudoku grid based on this data.
	 * Sets difficulty of this sudoku.
	 * @param fileName Path with name of file.
	 * @return Sudoku
	 * @throws FileNotFoundException Exception if file wasn't found
	 */
	public static Sudoku loadFromFile (String fileName) throws FileNotFoundException {
		Sudoku pomSud = new Sudoku ();
		Scanner sc = new Scanner(new File("src/" + fileName));
		int pomDif = 0;
		for (int i = 0; i < Sudoku.SIZE; i++) {
			for (int j = 0; j < Sudoku.SIZE; j++) {
				pomSud.table [i][j] = new Cell (i, j, sc.nextInt(), false);
				if (pomSud.table [i][j].getNumber () != 0) {
					pomSud.table [i][j].setChangeable(false);
				} else {
					pomDif ++;
				}
			}
		}
		
		pomSud.setDifficulty(pomDif);
		sc.close ();
		return pomSud;
	}
	/**
	 * Tries to generate sudoku game with specified difficulty with given seed.
	 * Returns the highest possible difficulty for this combination.
	 * @param seed Seed.
	 * @param difficulty Prefered difficulty of sudoku.
	 * @return Sudoku
	 */
	public static Sudoku generate (long seed, int difficulty) {
		setRandomSeed (seed);
		return removeElements (generatorCore (new Sudoku (difficulty)));
	}
	/**
	 * Generate sudoku without setting blank cells.
	 * @param seed Seed.
	 * @return Sudoku
	 */
	public static Sudoku generate (long seed) {
		setRandomSeed (seed);
		return generatorCore (new Sudoku ());
	}
	
	/**
	 * Core of the generator.
	 * @param pomSud
	 * @return Sudoku
	 */
	private static Sudoku generatorCore (Sudoku pomSud) {
		int pom;	/// zmienna pomocnicza
		int i = 0, j = 0;
		while (i != 9 && j != 9) {
			if (!pomSud.table [i][j].availableNumbers.isEmpty()) {	/// dopóki availableNumbers nie jest pusta
				pom = ran.nextInt (pomSud.table[i][j].availableNumbers.size());	/// losowy int od 0 do wielkoœci listy
				pomSud.table [i][j].setNumber (pomSud.table [i][j].availableNumbers.get(pom));	/// ustawianie numeru
				if (conflict (pomSud, pomSud.table [i][j])) {	/// jeœli zachodzi konflikt
					pomSud.table [i][j].availableNumbers.remove(pom);	/// usuwanie z listy pom
					continue;
				}
				j++;
				if (j >= 9) {
					j = 0;
					i++;
				}
				for (int g = 0; g < j; g++) {
					if (i < 9 && j < 9) {
						pomSud.table [i][j].removeNumber (pomSud.table [i][g].getNumber ());
					}
				}
				for (int g = 0; g < i; g++) {
					if (i < 9 && j < 9) {
						pomSud.table [i][j].removeNumber (pomSud.table [g][j].getNumber ());
					}
				}
			} else {
				pomSud.table [i][j] = new Cell (i, j);
				if (j == 0) {
					i--;
					j = 8;
				} else {
					j--;
				}
				pomSud.table [i][j].removeNumber(pomSud.table[i][j].getNumber ());
			}
		}
		return pomSud;
	}
	
	
	/**
	 * Check if there is a conflict of cells.
	 * @param pomSud
	 * @param o
	 * @return boolean
	 */
	private static boolean conflict (Sudoku pomSud, Cell o) {
		/// linie
		/// poziome
		for (int i = 0; i < o.getX (); i++) {
			if (pomSud.table [i][o.getY()].getNumber () == o.getNumber ()) {
				return true;
			}
		}
		/// pionowe
		for (int i = 0; i < o.getY(); i++) {
			if (pomSud.table [o.getX ()][i].getNumber () == o.getNumber ()) {
				return true;
			}
			
		}
		/// kwadraciki
		int pomX, pomY;
		pomX = o.getX () / 3;
		pomY = o.getY() / 3;
		for (int i = 3 * pomX; i < 3 * pomX + 3; i++) {
			for (int j = 3 * pomY; j < 3 * pomY + 3; j++) {
				if (i == o.getX () || j == o.getY()) continue;
				if (pomSud.table [i][j].getNumber () == o.getNumber ()) return true;
			}
		}
		return false;
	}
	/**
	 * Solves a sudoku starting front or back.
	 * @param sudoku Given sudoku.
	 * @param front true - starts checking from 0 to 9; false - starts checking from 9 to 0.
	 * @return Sudoku
	 */
	public static Sudoku solve (Sudoku sudoku, boolean front) {
		return solver (sudoku, front);
	}
	/**
	 * Tries to set blank sudoku cells.
	 * @param sudoku
	 * @return Sudoku
	 */
	private static Sudoku removeElements (Sudoku sudoku) {
		Sudoku pomSud = new Sudoku (sudoku);
		ArrayList <Cell> allElements = new ArrayList <Cell> ();
		//ArrayList <Cell> removedEls = new ArrayList <Cell> ();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				allElements.add(pomSud.table [i][j]);
			}
		}
		Collections.shuffle(allElements, ran);
		int pom, iter = 0;
		for (Cell element : allElements) {
			if (iter >= pomSud.getDifficulty()) {
				break;
			}
			pom = element.getNumber ();
			element.setChangeable(true);
			element.setNumber(0);
			if (solver (pomSud, true).getSudokuString()
					.equals (solver (pomSud, false).getSudokuString())) {
				iter++;
				continue;
			}
			element.setChangeable(false);
			element.setNumber(pom);
		}
		pomSud.setDifficulty(iter);
		return pomSud;
	}
	
	/**
	 * Solves a sudoku.
	 * @param sudoku
	 * @param preOrder
	 * @return Sudoku
	 */
	private static Sudoku solver (Sudoku sudoku, boolean preOrder) {
		ArrayList <Cell> elements = new ArrayList <Cell> ();
		Sudoku pomSud = new Sudoku (sudoku);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (pomSud.table [i][j].isChangeable()) {
					elements.add(pomSud.table [i][j]);
				}
			}
		}
		
		int i = 0;
		while (i != elements.size()) {
			if (!elements.get(i).availableNumbers.isEmpty()) {	/// dopóki availableNumbers nie jest pusta
				if (preOrder) {
					elements.get(i).setNumber (elements.get(i).availableNumbers.get(0));	/// ustawianie numeru
					if (conflictFront (pomSud, elements.get(i))) {	/// jeœli zachodzi konflikt
						elements.get(i).availableNumbers.remove(0);	/// usuwanie z listy pom
						continue;
					}
				} else {
					elements.get(i).setNumber
							(elements.get(i).availableNumbers.get
									(elements.get(i).availableNumbers.size() - 1));
					if (conflictFront (pomSud, elements.get(i))) {	/// jeœli zachodzi konflikt
						elements.get(i).availableNumbers.remove
						(elements.get(i).availableNumbers.size() - 1);
						continue;
					}
				}
				i++;
			} else {
				elements.get(i).resetAvailableNumbers();
				elements.get(i).setNumber(0);
				i--;
				elements.get(i).removeNumber(elements.get(i).getNumber ());
				
			}
		}
		return pomSud;
	}
	
	/**
	 * Check if there is confict of cells.
	 * @param pomSud
	 * @param o
	 * @return boolean
	 */
	private static boolean conflictFront (Sudoku pomSud, Cell o) {
		/// linie
		/// poziome
		for (int i = 0; i < Sudoku.SIZE; i++) {
			if (i == o.getX()) continue;
			if (pomSud.table [i][o.getY()].getNumber () == o.getNumber ()) {
				return true;
			}
		}
		/// pionowe
		for (int i = 0; i < Sudoku.SIZE; i++) {
			if (i == o.getY()) continue;
			if (pomSud.table [o.getX ()][i].getNumber () == o.getNumber ()) {
				return true;
			}
			
		}
		/// kwadraciki
		int pomX, pomY;
		pomX = o.getX () / 3;
		pomY = o.getY() / 3;
		for (int i = 3 * pomX; i < 3 * pomX + 3; i++) {
			for (int j = 3 * pomY; j < 3 * pomY + 3; j++) {
				if (i == o.getX () || j == o.getY()) continue;
				if (pomSud.table [i][j].getNumber () == o.getNumber ()) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Returns seed.
	 * @return long
	 */
	public static long getRandomSeed() {
		return randomSeed;
	}
	/**
	 * Sets seed.
	 * @param randomSeed Seed.
	 */
	public static void setRandomSeed(long randomSeed) {
		System.out.println(randomSeed);
		System.out.println();
		Generator.randomSeed = randomSeed;
		ran.setSeed(randomSeed);
	}
}