import controllers.GameController;
import models.*;
import strategies.winningstrategy.ColumnWinningStrategy;
import strategies.winningstrategy.DiagonalWinningStrategy;
import strategies.winningstrategy.RowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        List<Player> players = List.of(
                new Player(new Symbol('X'),"Shiva", PlayerType.HUMAN)
        );

        int dimension = 3;

        Game game = gameController.createGame(dimension,
                players,
                List.of(
                        new ColumnWinningStrategy(dimension, players),
                        new DiagonalWinningStrategy(dimension, players),
                        new RowWinningStrategy(dimension, players)
                ));


        //
        // while game status is in progress
        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            //print board
            gameController.displayBoard(game);
            //ask for undo
            System.out.println("Does anyone want's to undo? (y/n)");
            String input = scanner.next();
            //if yes und0
            if (input.equalsIgnoreCase("y")){
                gameController.undo(game);
            }
            //else
            //ask for the player for move
            else{
                gameController.makeMove(game);
            }


        }

        gameController.printWinner(game);

    }
}
