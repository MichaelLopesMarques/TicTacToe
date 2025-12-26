import java.util.ArrayList;
import java.util.List;

public class BotPlayer extends Player{

    public BotPlayer() {
        super("O");
    }

    public int[] botChooseMove(String[][] spielFeld){
        List<int[]> freieFelder = new ArrayList<>();

        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                if (spielFeld[r][c] == null){
                    freieFelder.add(new int[]{r, c});
                }
            }
        }

        if (freieFelder.isEmpty()){
            return null;
        }

        int index = (int) ((Math.random() * freieFelder.size()));
        return freieFelder.get(index);
    }


}
