package models;

import strategies.botplayingstrategy.BotFactory;
import strategies.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player{
    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, String name, DifficultyLevel difficultyLevel) {
        super(symbol, name, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotFactory.getBotStrategyBasedOnDifficulty(this.difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public Cell makeMove(Board board) {
        return this.botPlayingStrategy.makeMove(board);
    }
}
