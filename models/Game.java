package models;

import strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;

    public static Builder getBuilder(){
        return new Builder();
    }

    public Game(int dimension, List<WinningStrategy> winningStrategies, List<Player> players) {
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.players = players;
        this.currentPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void printBoard(){
        this.board.print();
    }

    public void printWinner(){
        GameStatus gameStatus = this.getGameStatus();

        if (gameStatus.equals(GameStatus.ENDED)){
            this.printBoard();
            System.out.println(getWinner().getName() + " has won the game.");
        }
        else{
            System.out.println("Game is DRAW");
        }
    }

    public void makeMove(){
        Player currentPlayer = players.get(this.currentPlayerIndex);
        System.out.println(currentPlayer.getName()+"'s turn.");

        Cell proposedCell = currentPlayer.makeMove(this.board);

        System.out.println("Move made at row : "+proposedCell.getRow()+" at col : "+proposedCell.getCol()+".");

        if (!this.validateMove(proposedCell)){
            System.out.println("Invalid Move. Retry");
        }
        else{
            Move move = new Move(currentPlayer, proposedCell);
            this.moves.add(move);

            Cell cellInBoard = this.board.getGrid().get(proposedCell.getRow()).get(proposedCell.getCol());
            cellInBoard.setPlayer(currentPlayer);
            cellInBoard.setCellStatus(CellStatus.FILLED);

            if (checkGameWinner(currentPlayer, move)) return;

            if (checkGameDraw()) return;

            this.currentPlayerIndex += 1;
            this.currentPlayerIndex %= this.players.size();
        }

    }

    private boolean checkGameDraw() {
        if (this.moves.size() == this.board.getSize()*this.board.getSize()){
            //we are assuming there are no blocked cells
            this.gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameWinner(Player currentPlayer, Move move) {
        for (WinningStrategy winningStrategy : this.winningStrategies){
            if (winningStrategy.checkWinner(move)){
                this.gameStatus = GameStatus.ENDED;
                this.winner = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public boolean validateMove(Cell cell){
        Cell cellInBoard = this.board.getGrid().get(cell.getRow()).get(cell.getCol());
        return (cell.getRow() >= 0 && cell.getCol() >= 0) && (cell.getRow() < this.board.getSize() && cell.getCol() < this.board.getSize()) && (cellInBoard.getCellStatus().equals(CellStatus.EMPTY));
    }

    public void undo(){
        if (this.moves.size() == 0){
            System.out.println("Undo is not Available.");
            return;
        }
        Move lastMove = this.moves.get(this.moves.size()-1);
        Cell cell = lastMove.getCell();
        Cell cellInBoard = this.board.getGrid().get(cell.getRow()).get(cell.getCol());
        cellInBoard.setPlayer(null);
        cellInBoard.setCellStatus(CellStatus.EMPTY);
        this.moves.remove(this.moves.size()-1);

        for (WinningStrategy winningStrategy : this.winningStrategies){
            winningStrategy.undoMove(lastMove);
        }

        this.currentPlayerIndex -= 1;
        this.currentPlayerIndex += this.board.getSize();
        this.currentPlayerIndex %= this.board.getSize();
    }

    public static class Builder{

        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder(){

        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private boolean validate(){
            if (this.dimension <= 2){
                return false;
            }

            if (this.players.size() != this.dimension - 1){
                return false;
            }

            int bot_count = 0;
            for (Player player: this.players){
                if (player instanceof Bot){
                    bot_count += 1;
                }
                if (bot_count > 1){
                    return false;
                }
            }

            return this.winningStrategies.size() >= 1;
        }
        public Game build() {
            if (!validate()){
                System.out.println("Invalid Game.");
                return null;
            }

            return new Game(this.dimension, this.winningStrategies, this.players);

        }
    }
}
