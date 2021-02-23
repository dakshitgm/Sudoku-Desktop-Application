package sudoku.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppWindow {
    private static final double WINDOW_Y = 732;
    private static final double WINDOW_X = 668;
    private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(0, 150, 136);
    public static final String NAME = "Sudoku";

    protected final Stage stage;
    protected final Group root;

    protected AppWindow(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        drawBackGround(root);
        drawTitle(root);
    }

    private void drawBackGround(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BACKGROUND_COLOR);
        stage.setScene(scene);
    }

    private void drawTitle(Group root) {
        Text title = new Text(235, 690, NAME);
        title.setFill(Color.WHITE);
        Font titleFont =new Font(43);
        title.setFont(titleFont);
        root.getChildren().add(title);
    }

}
