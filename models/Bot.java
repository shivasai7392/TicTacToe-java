package models;

public class Bot extends Player{
    private DifficultyLevel difficultyLevel;

    public Bot(Symbol symbol, String name, DifficultyLevel difficultyLevel) {
        super(symbol, name, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
