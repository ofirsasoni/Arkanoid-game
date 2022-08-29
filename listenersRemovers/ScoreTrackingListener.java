package listenersRemovers;

import core.Counter;
import gameObjects.Ball;
import gameObjects.Block;

/**
 * The class represents the listener of the score track.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param currentScore current score of the game.
     */
    public ScoreTrackingListener(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * Get method.
     * @return the current score of the game.
     */
    public int getScore() {
        return currentScore.getValue();
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(beingHit.getHitListeners().get(0));
    }
}
