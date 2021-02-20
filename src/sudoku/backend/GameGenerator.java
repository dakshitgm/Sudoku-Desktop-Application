package sudoku.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameGenerator {
    private int length;
    private int lengthRoot;
    private GameValidator gameValidator;
    private int game[][];


    public GameGenerator(int length, int lengthRoot, GameValidator gameValidator) throws Exception {
        this.length = length;
        this.lengthRoot = lengthRoot;
        if (length < 4 || lengthRoot * lengthRoot != length) {
            throw new IllegalArgumentException("the length must be perfect square grater than 1");
        }
        this.game = new int[length][length];
        this.gameValidator = gameValidator;
    }

    public int[][] generateGame(){

        //start by filling diagonal first
        fillDiagonals();

        //then fill other cell by backtracking
        fillRemaining(0, lengthRoot);

        return game;
    }

    private void fillDiagonals(){
        ArrayList<Integer> arr = new ArrayList<>(length);
        for (int i = 1; i <= length; i++) {
            arr.add(i);
        }
        for(int i=0; i<length; i+=lengthRoot){
            fillBox(i, i, arr);
        }
    }

    private void fillBox(int row, int col, ArrayList<Integer> arr) {
        Collections.shuffle(arr);
        int n = 0;
        for (int i = 0; i < lengthRoot; i++) {
            for (int j = 0; j < lengthRoot; j++) {
                game[row + i][col + j] = arr.get(n++);
            }
        }

    }

    private boolean fillRemaining(int i, int j){
        //if last element of row move next
        if (j>=length && i<length-1){
            i++;
            j = 0;
        }
        //if both greater than length return
        if (i>=length && j>=length)
            return true;


        if (i < lengthRoot){
            if (j < lengthRoot) j = lengthRoot;

        } else if (i < length-lengthRoot) {
            if (j==(int)(i/lengthRoot)*lengthRoot)
                j += lengthRoot;
        }
        else if (j == length-lengthRoot) {
            i++;
            j = 0;
            if (i>=length) return true;
        }

        for (int num = 1; num<=length; num++) {
            if (gameValidator.checkIfSafe(i, j, num, game)) {
                game[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;
                game[i][j] = 0;
            }
        }
        return false;

    }


}