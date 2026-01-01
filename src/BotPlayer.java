import java.util.ArrayList;
import java.util.List;

public class BotPlayer extends Player{

    public BotPlayer() {
        super("O");
    }

    public int[] botChooseMove(String[][] gameField){
        // Checks which Fields are empty and picks random one
        List<int[]> allEmptyFields = new ArrayList<>();

        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                if (gameField[r][c] == null){
                    allEmptyFields.add(new int[]{r, c});
                }
            }
        }

        if (allEmptyFields.isEmpty()){
            return null;
        }

        int index = (int) ((Math.random() * allEmptyFields.size()));
        return allEmptyFields.get(index);
    }


}
