package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Ball;
import gameObjects.Block;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface represents the information about a specific level.
 */
public interface LevelInformation {
    /**
     * The number of balls in this level.
     * @return the amount of balls in the game level.
     */
    int numberOfBalls();

    /**
     * The method returns the initial velocities of the balls in this level as a list.
     * @return initial velocities of the balls in this level as a list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The method returns a list of all the balls in this level.
     * @return a list of all the balls in this level.
     */
    List<Ball> balls();

    /**
     * The method returns the paddle speed.
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * The method returns the paddle width.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * The method returns the paddle rectangle shape.
     * @return paddle rectangle shape.
     */
    Rectangle paddleRectangle();

    /**
     * The method returns the level name.
     * @return level name.
     */
    String levelName();

    /**
     * The method returns the background color.
     * @return background color.
     */
    Color getBackgroundColor();

    /**
     * The method returns the background of the level as a sprite.
     * @return background of the level.
     */
    Sprite getBackground();

    /**
     * The method returns a list of all the background ingredients.
     * @return list of all the background ingredients.
     */
    ArrayList<Sprite> background();

    /**
     * The method returns a list of all the blocks in the level.
     * @return list of all the blocks in the level.
     */
    List<Block> blocks();

    /**
     * The method returns the amount of blocks we have to remove in the level.
     * @return amount of blocks we need to remove.
     */
    int numberOfBlocksToRemove();
}
