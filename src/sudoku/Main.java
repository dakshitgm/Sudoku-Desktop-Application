package sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.constants.Levels;
import sudoku.ui.BoardController;
import sudoku.ui.InitiateBoard;
import sudoku.backend.Game;



public class Main extends Application{
    public static final int LENGTH = 9;

    @Override
    public void start(Stage primaryStage) throws Exception{
        InitiateBoard board = new InitiateBoard(primaryStage);
        Game sudokuGame = new Game(LENGTH, Levels.MEDIUM);
        BoardController boardController = new BoardController(sudokuGame, board);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
