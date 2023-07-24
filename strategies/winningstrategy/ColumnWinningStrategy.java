package strategies.winningstrategy;

import models.Board;
import models.Move;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        int dimension = board.getSize();
        char shape = move.getPlayer().getSymbol().getShape();
        if (board.getColCounter().containsKey(col)) {
            if (board.getColCounter().get(col).containsKey(shape)) {
                int val = board.getColCounter().get(col).get(shape) + 1;
                board.getColCounter().get(col).put(shape, val);
                if (val == dimension){
                    return true;
                }
            } else {
                board.getColCounter().get(col).put(shape, 0);
            }
        }
        else{
            board.getColCounter().put(col, new HashMap<>());
            HashMap<Character, Integer> map = board.getColCounter().get(col);
            map.put(shape, 1);
        }
        return false;
    }
}
