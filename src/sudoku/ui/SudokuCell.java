package sudoku.ui;

import javafx.scene.control.TextField;

public class SudokuCell extends TextField {
    private final int x;
    private final int y;


    public SudokuCell(int x, int y){
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void replaceText(int i, int i1, String s) {
        if(s.equals("0") || s.isEmpty()) clear();
        else if((s.length() ==1  && s.matches("[1-9]")) || s.isEmpty()){
            setText(s);
            return;
        }

    }

    @Override
    public void replaceSelection(String s) {
        if(s.equals("0") || s.isEmpty()) clear();
        else if(s.length() <=1 && s.matches("[1-9]")){
            setText(s);
            return;
        }
    }
}
