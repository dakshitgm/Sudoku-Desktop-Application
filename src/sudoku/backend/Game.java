package sudoku.backend;

import sudoku.constants.Status;

import java.util.ArrayList;

public class Game{

    private int length;
    private int level;
    private int lengthRoot;
    private int emptySpaces, emptySpacesRemaining;
    private int[][] solved, unSolved;
    private boolean[][] isMutable;



    public GameValidator gameValidator;

    public Game(int length, int level){
        this.length=length;
        this.lengthRoot = (int) Math.sqrt(length);
        this.emptySpaces = level;
        this.emptySpacesRemaining = level;
        gameValidator = new GameValidator(length, lengthRoot);

        try {
            GameGenerator gameGenerator = new GameGenerator(length, lengthRoot, gameValidator);
            this.solved = gameGenerator.generateGame();
            if(!gameValidator.checkIfValid(solved))
                throw new Exception("algo not providing valid game");

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.unSolved = this.solved.clone();
        isMutable = new boolean[length][length];
        removeSomeDigits();
    }

    public int getAt(int x, int y){
        return unSolved[x][y];
    }
    // remove digits according to level
    private void removeSomeDigits() {
        int count = emptySpaces;
        while (count != 0) {
            int num = length*length;
            int cellId = (int) Math.floor((Math.random()*num));

            int i = (cellId/length);
            int j = cellId%length;
            if (j != 0)  j--;

            if (unSolved[i][j] != 0) {
                count--;
                unSolved[i][j] = 0;
                isMutable[i][j] = true;
            }
        }
    }
    public int getLength() {
        return length;
    }

    public Status changeCell(int value, int x, int y){
        if(!isMutable[x][y]) return Status.UNCHANGED;
        if(unSolved[x][y]==0 && value>0){
            emptySpacesRemaining--;
        } else if(value == 0 && unSolved[x][y]>0){
            emptySpacesRemaining++;
        }
        unSolved[x][y] = value;

        //todo: check for conflicts

        if(emptySpacesRemaining == 0){
            if(gameValidator.checkIfValid(unSolved)){
                return Status.WIN;
            } else {
                return Status.CONFLICTS;
            }
        }

        return Status.ACTIVE;
    }


}
