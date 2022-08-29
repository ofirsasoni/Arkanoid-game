package listenersRemovers;

// Ofir Sasoni
// 325690386

import core.Counter;
import gameObjects.Ball;
import gameObjects.Block;
import game.GameLevel;

/**
 * The class "BallRemover" implements the hit listener interface and its goal is to remove a ball who bounced off the
 * screen.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game the game.
     * @param remainingBalls the amount of remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The function removes the ball who bounced off the screen from the game and decrease the number of remaining
     * balls by 1.
     * @param beingHit the "death block".
     * @param hitter the ball who bounced off the screen and hit the death block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
