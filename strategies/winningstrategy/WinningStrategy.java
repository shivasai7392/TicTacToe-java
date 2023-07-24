package strategies.winningstrategy;

import models.Board;
import models.Move;

public interface WinningStrategy {
    boolean checkWinner(Move move);
    void undoMove(Move move);
}
