package gameObjects;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The class represents the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle rectangle;
    private final Color color;
    private final int xVelocity;

    /**
     * Constructor.
     * @param keyboard the keyboard sensor of the paddle.
     * @param rectangle the rectangle represents the paddle.
     * @param color the color of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color, int xVelocity) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.xVelocity = xVelocity;
    }

    /**
     * Get method.
     * @return the rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * The function moves the paddle to the left if the move would not cross the left boarder.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() - xVelocity >= 25) {
            this.rectangle.setUpperLeftX(this.rectangle.getUpperLeft().getX() - xVelocity);
        } else {
            this.rectangle.setUpperLeftX(26);
        }
    }

    /**
     * The function moves the paddle to the right if the move would not cross the right boarder.
     */
    public void moveRight() {
        if (this.rectangle.getUpperRight().getX() + xVelocity <= 769) {
            this.rectangle.setUpperLeftX(this.rectangle.getUpperLeft().getX() + xVelocity);
        } else {
            this.rectangle.setUpperLeftX(769 - this.rectangle.getWidth());
        }
    }

    /**
     * The function moves the paddle to the left if the keyboard sensor got left key, and to the right if the keyboard
     * sensor got right key.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * The function draws the paddle on the surface.
     * @param d given draw surface.
     */
    public void drawOn(DrawSurface d) {
        Point upperLeft = this.rectangle.getUpperLeft();

        int upperLeftX = (int) upperLeft.getX();
        int upperLeftY = (int) upperLeft.getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        d.setColor(Color.black);
        d.drawRectangle(upperLeftX - 1, upperLeftY - 1, width + 1, height + 1);

        if (this.color == null) {
            d.setColor(Color.black);
        } else {
            d.setColor(color);
        }

        d.fillRectangle(upperLeftX, upperLeftY, width, height);
    }

    /**
     * Get method.
     * @return the rectangle represents the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The function gets a velocity represented by dx and dy and returns the speed.
     * @param velocity given velocity.
     * @return the speed of the velocity.
     */
    private double speed(Velocity velocity) {
        return Math.sqrt(Math.pow(velocity.getDx(), 2) + Math.pow(velocity.getDy(), 2));
    }

    /**
     * The function gets a velocity and return a new velocity if the ball has touched the first region of the paddle.
     * @param velocity given velocity.
     * @return the new velocity if the ball touched the first region of the paddle.
     */
    private Velocity region1(Velocity velocity) {
        return Velocity.fromAngleAndSpeed(300, speed(velocity));
    }

    /**
     * The function gets a velocity and return a new velocity if the ball has touched the second region of the paddle.
     * @param velocity given velocity.
     * @return the new velocity if the ball touched the second region of the paddle.
     */
    private Velocity region2(Velocity velocity) {
        return Velocity.fromAngleAndSpeed(330, speed(velocity));
    }

    /**
     * The function gets a velocity and return a new velocity if the ball has touched the third region of the paddle.
     * @param velocity given velocity.
     * @return the new velocity if the ball touched the third region of the paddle.
     */
    private Velocity region3(Velocity velocity) {
        return new Velocity(velocity.getDx(), -velocity.getDy());
    }

    /**
     * The function gets a velocity and return a new velocity if the ball has touched the fourth region of the paddle.
     * @param velocity given velocity.
     * @return the new velocity if the ball touched the fourth region of the paddle.
     */
    private Velocity region4(Velocity velocity) {
        return Velocity.fromAngleAndSpeed(30, speed(velocity));
    }

    /**
     * The function gets a velocity and return a new velocity if the ball has touched the fifth region of the paddle.
     * @param velocity given velocity.
     * @return the new velocity if the ball touched the fifth region of the paddle.
     */
    private Velocity region5(Velocity velocity) {
        return Velocity.fromAngleAndSpeed(60, speed(velocity));
    }

    private int regionCollidedWith(Point collisionPoint) {
        int width = (int) this.rectangle.getWidth();
        int regionWidth = width / 5;
        int regionCollidedWith = -1;
        if (collisionPoint == null) {
            return -1;
        }

        for (int i = 0; i < 5; i++) {
            if (regionWidth * i + this.rectangle.getUpperLeft().getX() <= collisionPoint.getX()
                    && collisionPoint.getX() <= regionWidth * (i + 1) + this.rectangle.getUpperLeft().getX()) {
                regionCollidedWith = i + 1;
            }
        }

        return regionCollidedWith;
    }

    /**
     * The function returns the new velocity after the ball hit the paddle.
     * @param collisionPoint the collision point of the object with velocity to the collidable object.
     * @param currentVelocity the velocity of the object.
     * @return the new velocity of the ball.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int regionCollidedWith = regionCollidedWith(collisionPoint);

        if (regionCollidedWith == -1) {
            return currentVelocity;
        }

        if (regionCollidedWith == 1) {
            return region1(currentVelocity);
        }
        if (regionCollidedWith == 2) {
            return region2(currentVelocity);
        }
        if (regionCollidedWith == 3) {
            return region3(currentVelocity);
        }
        if (regionCollidedWith == 4) {
            return region4(currentVelocity);
        }
        if (regionCollidedWith == 5) {
            return region5(currentVelocity);
        }

        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());

    }

    /**
     * The function returns the new velocity after the ball hit the paddle.
     * @param hitter the ball which hit the paddle.
     * @param collisionPoint the collision point of the object with velocity to the collidable object.
     * @param currentVelocity the velocity of the object.
     * @return the new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int regionCollidedWith = regionCollidedWith(collisionPoint);

        if (regionCollidedWith == -1) {
            return currentVelocity;
        }

        if (regionCollidedWith == 1) {
            return region1(currentVelocity);
        }
        if (regionCollidedWith == 2) {
            return region2(currentVelocity);
        }
        if (regionCollidedWith == 3) {
            return region3(currentVelocity);
        }
        if (regionCollidedWith == 4) {
            return region4(currentVelocity);
        }
        if (regionCollidedWith == 5) {
            return region5(currentVelocity);
        }

        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());

    }

    /**
     * The function gets a game object and adds this paddle to the game sprites and collidables collection.
     * @param g given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}