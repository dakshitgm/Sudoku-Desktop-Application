package sudoku.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InitiateBoard extends AppWindow{

    public final SudokuCell[][] boardCells;

    private static final double BOARD_PADDING = 50;
    private static final double BOARD_X_AND_Y = 576;
    private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(224, 242, 136);

    public InitiateBoard(Stage stage) {
        super(stage);
        this.boardCells = new SudokuCell[9][9];
        initializeUi();
    }

    public void initializeUi() {
        drawSudokuBoard(root);
        drawCells(root);
        drawGridLines(root);
        stage.show();
    }

    private void drawSudokuBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BOARD_PADDING);
        boardBackground.setY(BOARD_PADDING);

        boardBackground.setWidth(BOARD_X_AND_Y);
        boardBackground.setHeight(BOARD_X_AND_Y);
        boardBackground.setFill(BOARD_BACKGROUND_COLOR);
        root.getChildren().addAll(boardBackground);

    }
    private void drawCells(Group root) {
        final int xOrigin = 50, yOrigin = 50, xAndYDelta = 64;

        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                int x = xOrigin + xIndex * xAndYDelta;
                int y = yOrigin + yIndex * xAndYDelta;

                SudokuCell cell = new SudokuCell(xIndex, yIndex);
                styleSudokuCell(cell, x, y);

                boardCells[xIndex][yIndex] = cell;

                root.getChildren().add(cell);
            }
        }

    }

    private void styleSudokuCell(SudokuCell cell, int x, int y) {
          Font numberFont = new Font(32);
          cell.setFont(numberFont);
          cell.setAlignment(Pos.CENTER);

          cell.setLayoutX(x);
          cell.setLayoutY(y);
          cell.setPrefHeight(64);
          cell.setPrefWidth(64);

          cell.setBackground(Background.EMPTY);

    }

    private void drawGridLines(Group root) {
        int xAndY =114, index = 0;

        while(index<8){
            int thickness;
            if(index == 2 || index == 5){
                thickness = 3;
            } else {
                thickness = 2;
            }

            Rectangle verticaLine = getLine(
                    xAndY + 64*index,
                    BOARD_PADDING,
                    BOARD_X_AND_Y,
                    thickness
            );
            Rectangle horizontalLine = getLine(
                    BOARD_PADDING,
                    xAndY + 64*index,
                    thickness,
                    BOARD_X_AND_Y
            );

            root.getChildren().addAll( verticaLine, horizontalLine);
            index++;
        }
    }


    private Rectangle getLine(double x, double y, double height, double width){
        Rectangle line = new Rectangle();
        line.setX(x);
        line.setY(y);
        line.setHeight(height);
        line.setWidth(width);

        return line;
    }

}
