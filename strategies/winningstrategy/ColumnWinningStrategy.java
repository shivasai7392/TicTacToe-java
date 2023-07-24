package strategies.winningstrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColumnWinningStrategy implements WinningStrategy{

    private final List<HashMap<Character, Integer>> colCounter;
    private final int dimension;

    public ColumnWinningStrategy(int dimension, List<Player> players) {
        this.dimension = dimension;
        this.colCounter = new ArrayList<>();
        for (int i = 0; i<dimension; ++i){
            HashMap<Character, Integer> map = new HashMap<>();
            for (Player player : players){
                map.put(player.getSymbol().getShape(), 0);
            }
            this.colCounter.add(map);
        }
    }

    @Override
    public void undoMove(Move move) {
        int col = move.getCell().getCol();
        HashMap<Character, Integer> map = this.colCounter.get(col);
        int char_count = map.get(move.getPlayer().getSymbol().getShape());
        map.put(move.getPlayer().getSymbol().getShape(), char_count-1);
    }

    @Override
    public boolean checkWinner(Move move) {
        int col = move.getCell().getCol();
        HashMap<Character, Integer> map = this.colCounter.get(col);
        int char_count = map.get(move.getPlayer().getSymbol().getShape());
        char_count += 1;
        map.put(move.getPlayer().getSymbol().getShape(), char_count);
        return char_count == this.dimension;
    }
}
