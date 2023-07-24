package strategies.winningstrategy;

import models.Board;
import models.Move;

public class DiagonalWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int dimension = board.getSize();
        char shape = move.getPlayer().getSymbol().getShape();
        if (row == col){
            if (board.getDiagCounter().get(1).containsKey(shape)){
                int val = board.getDiagCounter().get(1).get(shape) + 1;
                board.getDiagCounter().get(1).put(shape, val);
                if (val == dimension)
                    return true;
            }
            else{
                board.getDiagCounter().get(1).put(shape, 1);
            }
        }
        if (row + col == dimension-1) {
            if (board.getDiagCounter().get(2).containsKey(shape)){
                int val = board.getDiagCounter().get(2).get(shape) + 1;
                board.getDiagCounter().get(2).put(shape, val);
                if (val == dimension)
                    return true;
            }
            else{
                board.getDiagCounter().get(2).put(shape, 1);
            }
        }
        return false;
    }
}
