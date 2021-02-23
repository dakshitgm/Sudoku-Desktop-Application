package sudoku.backend;

import sudoku.constants.Status;


public class Game{

    private final int length;
    private final int lengthRoot;
    private final int emptySpaces;
    private int emptySpacesRemaining;
    private int[][] solved, unSolved;
    private final boolean[][] isMutable;



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
        Status current = Status.ACTIVE;
        if(unSolved[x][y]==0 && value>0){
            emptySpacesRemaining--;
        } else if(value == 0 && unSolved[x][y]>0){
            emptySpacesRemaining++;
        }
        if(value!=0 && !gameValidator.checkIfSafe(x,y, value, unSolved))
            current = Status.CONFLICTS;

        unSolved[x][y] = value;

        if(emptySpacesRemaining == 0){
            if(gameValidator.checkIfValid(unSolved)){
                return Status.WIN;
            } else {
                return Status.CONFLICTS;
            }
        }

        return current;
    }


}
