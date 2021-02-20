package sudoku.backend;

public class Game {
    public int length, level, lengthRoot;
    private int empty;
    private int[][] solved;
    public int[][] unSolved;
    public GameValidator gameValidator;

    public Game(int length, int level){
        this.length=length;
        this.lengthRoot = (int) Math.sqrt(length);
        this.empty = level;
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
        removeSomeDigits();
    }

    // remove digits according to level
    private void removeSomeDigits() {
        int count = empty;
        while (count != 0) {
            int num = length*length +1;
            int cellId = (int) Math.floor((Math.random()*num));

            int i = (cellId/length);
            int j = cellId%length;
            if (j != 0)  j--;

            if (unSolved[i][j] != 0) {
                count--;
                unSolved[i][j] = 0;
            }
        }
    }




}
