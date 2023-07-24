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

        Game game = gameController.createGame(3,
                List.of(
                        new Player(new Symbol('X'),"Shiva", PlayerType.HUMAN),
                        new Bot(new Symbol('O'),"Lakshmi", DifficultyLevel.EASY)
                ),
                List.of(
                        new ColumnWinningStrategy(),
                        new DiagonalWinningStrategy(),
                        new RowWinningStrategy()
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
