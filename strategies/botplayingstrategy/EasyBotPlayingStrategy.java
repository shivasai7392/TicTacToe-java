package strategies.botplayingstrategy;

import models.Board;
import models.Cell;
import models.CellStatus;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        List<List<Cell>> grid= board.getGrid();
        for (List<Cell> row : grid ){
            for (Cell cell : row){
                if (cell.getCellStatus().equals(CellStatus.EMPTY)){
                    return cell;
                }
            }
        }

        return null;
    }
}
