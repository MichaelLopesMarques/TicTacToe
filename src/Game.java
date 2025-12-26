public class Game{

    private String[][] gameBoard = new String[3][3];

    private Player playerX;
    private Player playerO;
    private Player currentPlayer;

    public Game(){
        playerX = new HumanPlayer();
        playerO = new BotPlayer();
        currentPlayer = playerX;
    }

    public boolean makeMove(int row, int col){
        if (gameBoard[row][col] == null){
            gameBoard[row][col] = currentPlayer.getSymbol();
            switchPlayer();
            return true;
        } else{
            System.out.println("Feld ist belegt!");
            return false;
        }
    }

    private void switchPlayer(){
        if(currentPlayer == playerX){
            currentPlayer = playerO;
        } else{
            currentPlayer = playerX;
        }
    }

    public int[] botMove(){
            BotPlayer bot = (BotPlayer) currentPlayer;
            int[] move = bot.botChooseMove(gameBoard);

            if (move == null) return null;

            makeMove(move[0], move[1]);
            return move;
    }

    public boolean isBotTurn(){
        return currentPlayer instanceof BotPlayer;
    }

    public String getField(int row, int col){
        return gameBoard[row][col];
    }

    public String checkWinner(){
        if ((gameBoard[0][0] != null) && (gameBoard[0][0].equals(gameBoard[0][1])) && (gameBoard[0][1].equals(gameBoard[0][2]))) return gameBoard[0][0];
        if ((gameBoard[0][0] != null) && (gameBoard[0][0].equals(gameBoard[1][1])) && (gameBoard[1][1].equals(gameBoard[2][2]))) return gameBoard[0][0];
        if ((gameBoard[0][0] != null) && (gameBoard[0][0].equals(gameBoard[1][0])) && (gameBoard[1][0].equals(gameBoard[2][0]))) return gameBoard[0][0];
        if ((gameBoard[1][0] != null) && (gameBoard[1][0].equals(gameBoard[1][1])) && (gameBoard[1][1].equals(gameBoard[1][2]))) return gameBoard[1][0];
        if ((gameBoard[2][0] != null) && (gameBoard[2][0].equals(gameBoard[2][1])) && (gameBoard[2][1].equals(gameBoard[2][2]))) return gameBoard[2][0];
        if ((gameBoard[2][0] != null) && (gameBoard[2][0].equals(gameBoard[1][1])) && (gameBoard[1][1].equals(gameBoard[0][2]))) return gameBoard[2][0];
        if ((gameBoard[0][1] != null) && (gameBoard[0][1].equals(gameBoard[1][1])) && (gameBoard[1][1].equals(gameBoard[2][1]))) return gameBoard[0][1];
        if ((gameBoard[0][2] != null) && (gameBoard[0][2].equals(gameBoard[1][2])) && (gameBoard[1][2].equals(gameBoard[2][2]))) return gameBoard[0][2];
        else return null;
    }

    public boolean tie(){
        int emptyFields = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (gameBoard[i][j] == null){
                    emptyFields++;
                }
            }
        }
        if ((emptyFields == 0) && (checkWinner() == null)) return true;
        else return false;
    }

}
