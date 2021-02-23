package sudoku.ui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sudoku.backend.Game;
import sudoku.constants.Levels;

import static sudoku.Main.LENGTH;

public class LevelSelector extends AppWindow implements EventHandler<MouseEvent> {

    private static final int BTN_WIDTH= 100;
    private static final int BTN_HEIGHT= 50;
    private static final int POS_X= 275;
    private static final int POS_Y= 250;
    private static final int MARGIN = 20;

    private static final String EASY = "Easy";
    private static final String MEDIUM = "Medium";
    private static final String HARD = "Hard";


    public LevelSelector(Stage stage) {
        super(stage);
        addLevels(root);
        stage.show();
    }

    private void addLevels(Group root) {
        int no = 0;
        createButton(EASY, no++);
        createButton(MEDIUM, no++);
        createButton(HARD, no);
    }

    private void createButton(String btnName, int no){
        Button btn = new Button(btnName);
        int y = POS_Y + (no * (BTN_HEIGHT + MARGIN));

        btn.setTextAlignment(TextAlignment.CENTER);
        btn.setAlignment(Pos.CENTER);
        btn.setLayoutX(POS_X);
        btn.setLayoutY(y);
        btn.setMinWidth(BTN_WIDTH);
        btn.setMinHeight(BTN_HEIGHT);
        btn.setOnMouseClicked(this);
        root.getChildren().add(btn);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int selectedLevel = switch (((Button) mouseEvent.getSource()).getText()) {
            case MEDIUM -> Levels.MEDIUM;
            case HARD -> Levels.HARD;
            default -> Levels.EASY;
        };

        InitiateBoard board = new InitiateBoard(stage);
        Game sudokuGame = new Game(LENGTH, selectedLevel);
        new BoardController(sudokuGame, board);

    }
}
