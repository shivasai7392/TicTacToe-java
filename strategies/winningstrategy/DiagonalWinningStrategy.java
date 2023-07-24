package strategies.winningstrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiagonalWinningStrategy implements WinningStrategy{
    private HashMap<Character, Integer> rightDiagCounter;
    private HashMap<Character, Integer> leftDiagCounter;

    private int dimension;

    public DiagonalWinningStrategy(int dimension, List<Player> players) {
        this.dimension = dimension;
        this.leftDiagCounter = new HashMap<>();
        this.rightDiagCounter = new HashMap<>();
        for (Player player : players) {
            this.leftDiagCounter.put(player.getSymbol().getShape(), 0);
            this.rightDiagCounter.put(player.getSymbol().getShape(), 0);
        }
    }

    @Override
    public void undoMove(Move move) {
        HashMap<Character, Integer> map;
        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        if (row + col == this.dimension-1){
            map = this.rightDiagCounter;
            int char_count = map.get(move.getPlayer().getSymbol().getShape());
            map.put(move.getPlayer().getSymbol().getShape(), char_count-1);
        }
        if (row == col) {
            map = this.leftDiagCounter;
            int char_count = map.get(move.getPlayer().getSymbol().getShape());
            map.put(move.getPlayer().getSymbol().getShape(), char_count-1);
        }
    }
    @Override
    public boolean checkWinner(Move move) {
        HashMap<Character, Integer> map;
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if (row + col == this.dimension-1){
            map = this.rightDiagCounter;
            int char_count = map.get(move.getPlayer().getSymbol().getShape());
            char_count += 1;
            map.put(move.getPlayer().getSymbol().getShape(), char_count);
            if (char_count == this.dimension){
                return true;
            }
        }
        if (row == col) {
            map = this.leftDiagCounter;
            int char_count = map.get(move.getPlayer().getSymbol().getShape());
            char_count += 1;
            map.put(move.getPlayer().getSymbol().getShape(), char_count);
            if (char_count == this.dimension){
                return true;
            }
        }
        return false;
    }
}
