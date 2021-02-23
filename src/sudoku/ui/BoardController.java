package sudoku.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sudoku.backend.Game;
import sudoku.constants.Messages;
import sudoku.constants.Status;

public class BoardController implements EventHandler<KeyEvent> {
    private Game game;
    private InitiateBoard board;


    public BoardController(Game game, InitiateBoard board) {
        this.game = game;
        this.board = board;
        fillBoard(board.boardCells, game);
    }

    private void fillBoard(SudokuCell[][] boardCells, Game game) {
        for (int x = 0; x < game.getLength(); x++) {
            for (int y = 0; y < game.getLength(); y++) {
                int value = game.getAt(x, y);
                if( value != 0) {
                    boardCells[x][y].setText(String.valueOf(value));
                    boardCells[x][y].setDisable(true);
                }
                boardCells[x][y].setOnKeyPressed(this::handle);
            }
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED){
            SudokuCell sudokuCell = (SudokuCell) keyEvent.getSource();
            if(keyEvent.getText().equals("1")
                    || keyEvent.getText().equals("2")
                    || keyEvent.getText().equals("3")
                    || keyEvent.getText().equals("4")
                    || keyEvent.getText().equals("5")
                    || keyEvent.getText().equals("6")
                    || keyEvent.getText().equals("7")
                    || keyEvent.getText().equals("8")
                    || keyEvent.getText().equals("9")
            ){
                int value = Integer.parseInt(keyEvent.getText());
                handleChange(value, sudokuCell.getX(), sudokuCell.getY());
            }else if (keyEvent.getCode() == KeyCode.BACK_SPACE){
                sudokuCell.setText("");
                handleChange(0, sudokuCell.getX(), sudokuCell.getY());
            }
        }

        keyEvent.consume();
    }

    private void handleChange(int value, int x, int y){
        Status current=game.changeCell(value, x, y);
        switch(current){
            case UNCHANGED: return;
            case ACTIVE: //todo remove if any conflicts
                break;
            case WIN: showDialog(Messages.GAME_WIN);
                break;
//            case CONFLICTS: showDialog(Messages.CONFLICTS);
//                //aisa nahi karna chahiye lekin kya kare
//                if(value>0)
//                    board.boardCells[x][y].setText(String.valueOf(value));
//                break;
        }
    }

    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();
    }

}
