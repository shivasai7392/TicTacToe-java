package strategies.botplayingstrategy;

import models.DifficultyLevel;

public class BotFactory {
    public static BotPlayingStrategy getBotStrategyBasedOnDifficulty(DifficultyLevel difficultyLevel){
        return switch (difficultyLevel){
            case EASY -> new EasyBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
        };
    }
}
