package sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.constants.Levels;
import sudoku.ui.AppWindow;
import sudoku.ui.BoardController;
import sudoku.ui.InitiateBoard;
import sudoku.backend.Game;
import sudoku.ui.LevelSelector;


public class Main extends Application{
    public static final int LENGTH = 9;

    @Override
    public void start(Stage primaryStage){
        new LevelSelector(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
