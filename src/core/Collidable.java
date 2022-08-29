package core;

// Ofir Sasoni
// 325690386

import gameObjects.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface represents collidable objects.
 */
public interface Collidable {
    /**
     * The function creates the collision shape of the object.
     * @return the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The function get collision point and velocity and return the new velocity expected after the hit.
     * @param hitter the ball which hit the object.
     * @param collisionPoint the collision point of the object with velocity to the collidable object.
     * @param currentVelocity the velocity of the object.
     * @return new velocity of the object after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
