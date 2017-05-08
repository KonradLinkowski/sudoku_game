package application;

import java.net.URL;
import java.util.ResourceBundle;

import core.Generator;
import core.Sudoku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Controller implements Initializable {

	@FXML
    private GridPane gridPane;
	
	private GridPane[][] subPanes;
	private Node[][] cells;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("it works");
		subPanes = new GridPane [3][3];
		cells = new Node [9][9];
		try {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					subPanes [i][j] = new GridPane ();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void onGownoClick () {
		
	}
	
	public void parseSudoku (Sudoku sud) {
		System.out.println("test2");
		try {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sud.table [i][j].isChangeable()) {
					cells [i][j] = new ChoiceBox<Integer> (FXCollections.observableArrayList(
						    1, 2, 3, 4, 5, 6, 7, 8, 9));
				} else {
					cells [i][j] = new Label (Integer.toString(sud.table [i][j].getNumber()));
					//cells [i][j] = new Label ("1");
				}
			}
		}
		} catch (Exception e) {
			System.out.println("parseSudoku " + e);
		}
		fillPanes (sud);
	}
	//TODO poprawiæ
	private void fillPanes (Sudoku sud) {
		System.out.println("test");
		try {
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				GridPane.setHgrow(subPanes[i][j], Priority.ALWAYS);
				GridPane.setVgrow(subPanes[i][j], Priority.ALWAYS);
				gridPane.add(subPanes [i][j], i, j);
			}
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				GridPane.setHgrow(cells [i][j], Priority.ALWAYS);
				GridPane.setVgrow(cells [i][j], Priority.ALWAYS);
				subPanes [i / 3][j / 3].add(cells [i][j], i % 3, j % 3);
			}
		}
		} catch (Exception e) {
			System.out.println("fllPanes " + e);
		}
		System.out.println(subPanes [0][0].getChildren().toString());
	}
}
