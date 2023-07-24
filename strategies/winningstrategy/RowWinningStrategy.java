package strategies.winningstrategy;

import models.Board;
import models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        char shape = move.getPlayer().getSymbol().getShape();
        int dimension = board.getSize();
        if (board.getRowCounter().containsKey(row)) {
            if (board.getRowCounter().get(row).containsKey(shape)) {
                int val = board.getRowCounter().get(row).get(shape) + 1;
                board.getRowCounter().get(row).put(shape, val);
                if (val == dimension){
                    return true;
                }
            } else {
                board.getRowCounter().get(row).put(shape, 0);
            }
        }
        else{
            board.getRowCounter().put(row, new HashMap<>());
            HashMap<Character, Integer> map = board.getRowCounter().get(row);
            map.put(shape, 1);
        }
        return false;
    }
}
