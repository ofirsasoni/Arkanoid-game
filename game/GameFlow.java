package game;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import core.Counter;
import levels.LevelInformation;

import java.util.List;

/**
 * The class represents the option to run multiple levels one after the other.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score;

    /**
     * Constructor.
     * @param score the current score of the game.
     */
    public GameFlow(Counter score) {
        this.score = score;
    }

    /**
     * The method runs the levels in the given list.
     * @param levels list which have the levels we want to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (int i = 0; i < levels.size(); i++) {
            GameLevel level = new GameLevel(levels.get(i), score, i == levels.size() - 1);

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getRemainingBlocks().getValue() == 0) {
                score.increase(100);
            }
        }
    }
}
