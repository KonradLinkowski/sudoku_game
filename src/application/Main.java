package application;
import java.io.FileNotFoundException;

import core.Generator;
import core.Sudoku;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * <h1>Sudoku main class</h1>
 * <p>A simple sudoku game with generator and solver.</p>
 * @author Konrad Linkowski
*/
public class Main extends Application {

	public static void main (String[] args) throws FileNotFoundException {
		launch (args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Sudoku mySudoku = Generator.generate(System.nanoTime(), 30);
		FXMLLoader loader = new FXMLLoader(getClass ().getResource("MainScreen.fxml"));
		Parent root = loader.load();
		
		Controller myCont = loader.getController();
		myCont.parseSudoku(mySudoku);
		stage.setTitle ("My Stage");
		stage.setScene (new Scene (root, 800, 600));
		stage.setResizable(false);
		stage.show();
	}
}
