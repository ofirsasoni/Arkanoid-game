package listenersRemovers;

// Ofir Sasoni
// 325690386

import core.Counter;
import gameObjects.Ball;
import gameObjects.Block;
import game.GameLevel;

/**
 * The class "BlockRemover" implements the hit listener interface and its goal is to remove a block which hit by a ball.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game the game.
     * @param remainingBlocks the amount of remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The function removes the block which got hit by the ball from the game, removes it hit listener and decrease the
     * amount of blocks counts by one.
     * @param beingHit the block we want to remove.
     * @param hitter the ball which hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(beingHit.getHitListeners().get(0));
        remainingBlocks.decrease(1);
    }
}
