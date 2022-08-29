package listenersRemovers;

// Ofir Sasoni
// 325690386

import gameObjects.Ball;
import gameObjects.Block;

/**
 * The interface represents a listener to a hit.
 */
public interface HitListener {
    /**
     * The function guide what to do when an object got hit by a ball.
     * @param beingHit an object who got hit by the given ball.
     * @param hitter the ball which hit the given object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
