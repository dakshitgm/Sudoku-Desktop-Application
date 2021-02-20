package sudoku.backend;

public class GameValidator {
    private int length;
    private int lengthRoot;
    public GameValidator(int length, int lengthRoot){
        this.length = length;
        this.lengthRoot = lengthRoot;
    }

    public boolean checkIfSafe(int i,int j, int num, int[][] gameBoard){
        return (unUsedInRow(i, num, gameBoard) &&
                unUsedInCol(j, num, gameBoard) &&
                unUsedInBox(i-i%lengthRoot, j-j%lengthRoot, num, gameBoard));
    }
    private  boolean unUsedInRow(int i,int num, int[][] gameBoard){
        for (int j = 0; j<length; j++)
            if (gameBoard[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    private boolean unUsedInCol(int j, int num, int[][] gameBoard)
    {
        for (int i = 0; i<length; i++)
            if (gameBoard[i][j] == num)
                return false;
        return true;
    }

    private boolean unUsedInBox(int rowStart, int colStart, int num, int[][] gameBoard){
        for (int i = 0; i<lengthRoot; i++)
            for (int j = 0; j<lengthRoot; j++)
                if (gameBoard[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    public boolean checkIfValid(int[][] gameBoard){
        for(int i = 0; i<length; i+=lengthRoot){
            for(int j = 0; j<length; j+=lengthRoot){
                if(!checkIsValidBox(i, j, gameBoard)) return false;
            }
        }

        return checkByRows(gameBoard) && checkByColumns(gameBoard);
    }

    private boolean checkIsValidBox(int row, int columns, int[][] gameBoard){
        boolean[] isFound = new boolean[9];
        for(int i = 0; i<lengthRoot; i++){
            for(int j =0; j<lengthRoot; j++){
                if(isFound[gameBoard[row+i][columns+j]-1]){
                    return false;
                }
                isFound[gameBoard[row+i][columns+j]-1]=true;
            }
        }
        return true;
    }

    private  boolean checkByRows(int[][] gameBoard){
        boolean[] isFound;

        for (int i=0; i<length; i++){
            isFound = new boolean[length];

            for (int j=0; j<length; j++){
                if (isFound[gameBoard[i][j]-1]){
                    return false;
                }
                isFound[gameBoard[i][j]-1] = true;
            }
        }
        return true;
    }

    private  boolean checkByColumns(int[][] gameBoard){
        boolean[] isFound;

        for (int j=0; j<length; j++){
            isFound = new boolean[length];

            for (int i=0; i<length; i++){
                if (isFound[gameBoard[i][j]-1]){
                    return false;
                }
                isFound[gameBoard[i][j]-1] = true;
            }
        }
        return true;
    }


}
