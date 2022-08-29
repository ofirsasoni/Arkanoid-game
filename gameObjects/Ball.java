package gameObjects;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listenersRemovers.HitListener;

import java.awt.Color;
import java.util.ArrayList;


/**
 * The class "Ball" represent a ball with 3 characteristics: center point, radius and color.
 */
public class Ball implements Sprite {
    private static final Rectangle LEFT_BOARD = new Rectangle(new Point(0, 0), 30, 600);
    private static final Rectangle RIGHT_BOARD = new Rectangle(new Point(770, 0), 30, 600);
    private static final Rectangle UPPER_BOARD = new Rectangle(new Point(0, 0), 800, 30);
    private static final Rectangle LOWER_BOARD = new Rectangle(new Point(0, 570), 800, 30);
    private java.util.ArrayList<HitListener> hitListeners;

    private static final int MIN_X_LIMIT = 30;
    private static final int MAX_X_LIMIT = 770;
    private static final int MIN_Y_LIMIT = 30;
    private static final int MAX_Y_LIMIT = 570;

    private geometry.Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private int minLimit;
    private int maxLimit;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor.
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(geometry.Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param x     the x coordinate of the center point of the ball.
     * @param y     the y coordinate of the center point of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new geometry.Point(x, y);
        this.r = r;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param x     the x coordinate of the center point of the ball.
     * @param y     the y coordinate of the center point of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param velocity the velocity of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Velocity velocity) {
        this.center = new geometry.Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = velocity;
        hitListeners = new ArrayList<>();
    }

    /**
     * Constructor.
     * @param x     the x coordinate of the center point of the ball.
     * @param y     the y coordinate of the center point of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param velocity the velocity of the ball.
     * @param gameEnvironment the game environment of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Velocity velocity, GameEnvironment gameEnvironment) {
        this.center = new geometry.Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = gameEnvironment;
        hitListeners = new ArrayList<>();
    }

    /**
     * Get method.
     * @return value of the x coordinate of the center point of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get method.
     * @return value of the y coordinate of the center point of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get method.
     * @return the center point of the ball.
     */
    public geometry.Point getCenter() {
        return this.center;
    }

    /**
     * Get method.
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Get method.
     * @return new velocity object with the same values of this velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Get method.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Set method.
     * @param r new radius of the ball.
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Set method.
     * @param color new color of the ball.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Set method given a point with the new values of the center.
     * @param center new center of the ball.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Set method given the new values of the center.
     * @param x x value of the new center point of the ball.
     * @param y y value of the new center point of the ball.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * Set method to set the dx and the dy values of this velocity by the values of given velocity.
     * @param v velocity with the values we want to change this velocity to.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set function to set the dx and the dy values of this velocity to given values dx and dy.
     * @param dx the new value of this.dx.
     * @param dy the new value of this.dy.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Set method.
     * @param gameEnvironment new game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * function which draw this ball on the given surface.
     * @param d the surface we will draw the ball on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawCircle(this.getX(), this.getY(), this.r);
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * The function checks if the ball entered a block for some reason, if it did, the function pulls the ball
     * outside the block.
     * @param collidable a block the ball entered into.
     */
    public void enteredTheBlock(Collidable collidable) {
        Rectangle rectangle = collidable.getCollisionRectangle();
        double leftX = rectangle.getUpperLeft().getX();
        double rightX = rectangle.getUpperRight().getX();
        double upperY = rectangle.getUpperLeft().getY();
        double lowerY = rectangle.getLowerLeft().getY();

        double x = this.center.getX();
        double y = this.center.getY();

        if ((leftX < x && x < rightX) && (upperY < y && y < lowerY)) {
            if (x < MIN_X_LIMIT) {
                x = MIN_X_LIMIT + 1;
            }
            if (x > MAX_X_LIMIT) {
                x = MAX_X_LIMIT - 1;
            }
            if (y < MIN_Y_LIMIT) {
                y = MIN_Y_LIMIT + 1;
            }
            if (y > MAX_Y_LIMIT) {
                y = MAX_Y_LIMIT - 1;
            }
            if (leftX < x && x < rightX && upperY < y && y < lowerY) {
                this.setCenter(x, upperY - this.r * 2);
            }
            this.setCenter(x, y);
        }
    }

    /**
     * The function moveOneStep changes the current place of the center of the ball according to his velocity.
     * If the new place of the ball has crossed the board of one of the blocks, the function would place the ball
     * beside the board, and not across it.
     */

    public void moveOneStep() {
        Point trajectoryStart = new Point(this.getX(), this.getY());
        Point trajectoryEnd = new Point(this.getX() + this.velocity.getDx(), this.getY() + this.velocity.getDy());
        Line trajectory = new Line(trajectoryStart, trajectoryEnd);
        boolean isChecked = false;

        if (this.gameEnvironment == null || this.gameEnvironment.getClosestCollision(trajectory) == null) {
            this.setCenter(trajectoryEnd);
        } else {
            Point hitPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            Collidable collisionObject = this.gameEnvironment.getClosestCollision(trajectory).collisionObject();
            Rectangle rectangle = collisionObject.getCollisionRectangle();

            this.center = this.velocity.applyToPoint(this.center);
            this.velocity = collisionObject.hit(this, hitPoint, this.velocity);

            if (rectangle.getLeftLine().onSegment(hitPoint)) {
                this.center.setX(hitPoint.getX() - this.r);
                isChecked = true;
            }
            if (rectangle.getRightLine().onSegment(hitPoint)) {
                this.center.setX(hitPoint.getX() + this.r);
                isChecked = true;
            }
            if (rectangle.getUpperLine().onSegment(hitPoint)) {
                this.center.setY(hitPoint.getY() - this.r);
                isChecked = true;
            }
            if (rectangle.getLowerLine().onSegment(hitPoint)) {
                this.center.setY(hitPoint.getY() + this.r);
                isChecked = true;
            }

            if (!isChecked) {
                this.center = this.velocity.applyToPoint(this.center);
            }

            enteredTheBlock(collisionObject);
            enteredTheBlock(new Block(LOWER_BOARD));
            enteredTheBlock(new Block(UPPER_BOARD));
            enteredTheBlock(new Block(LEFT_BOARD));
            enteredTheBlock(new Block(RIGHT_BOARD));
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * The function adds the ball to given game sprite collection.
     * @param g given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function removes the ball from the given game.
     * @param g given game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}