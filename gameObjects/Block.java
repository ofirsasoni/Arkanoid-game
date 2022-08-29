package gameObjects;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listenersRemovers.HitListener;
import listenersRemovers.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represent a block object implements Collidable interface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private java.awt.Color color;
    private java.util.ArrayList<HitListener> hitListeners;

    /**
     * Constructor.
     * @param rectangle the rectangle object of the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * Constructor.
     * @param rectangle the rectangle object of the block.
     * @param color the color of the block.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new java.util.ArrayList<HitListener>();
    }

    /**
     * Constructor.
     * @param rectangle the rectangle object of the block.
     * @param color the color of the block.
     * @param hitListeners all the hit listeners.
     */
    public Block(Rectangle rectangle, java.awt.Color color, java.util.ArrayList<HitListener> hitListeners) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * The function creates the collision shape of the object.
     *
     * @return the collision shape of the object.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Get method.
     * @return the hit listeners.
     */
    public ArrayList<HitListener> getHitListeners() {
        if (hitListeners == null) {
            return new java.util.ArrayList<HitListener>();
        }
        return hitListeners;
    }

    /**
     * The function removes the block from a given game.
     * @param gameLevel a given game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * The function adds hit listener to the block hit listeners list.
     * @param hl new hit listener to be added to the list.
     */
    public void addHitListener(HitListener hl) {
        if (hitListeners == null) {
            hitListeners = new java.util.ArrayList<HitListener>();
            hitListeners.add(hl);
        }
        this.hitListeners.add(hl);
    }

    /**
     * Set method.
     * @param hitListeners new hit listeners.
     */
    public void setHitListeners(ArrayList<HitListener> hitListeners) {
        this.hitListeners = hitListeners;
    }

    /**
     * The function removes a given hit listener from the block hit listeners list.
     * @param hl the hit listener we want to remove.
     */
    public void removeHitListener(HitListener hl) {
        if (hitListeners == null) {
            hitListeners = new java.util.ArrayList<>();
        } else {
            this.hitListeners.remove(hl);
        }
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).hitEvent(this, hitter);
        }
    }

    /**
     * The function get collision point and velocity and return the new velocity expected after the hit.
     *
     * @param collisionPoint  the collision point of the object with velocity to the collidable object.
     * @param currentVelocity the velocity of the object.
     * @return new velocity of the object after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Rectangle rectangle = getCollisionRectangle();
        Line[] rectangleLines = rectangle.getLines(); // [0] - upperLine, [1] - lowerLine, [2] leftLine, [3] rightLine.
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        if (rectangleLines[0].onSegment(collisionPoint) || rectangleLines[1].onSegment(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }

        if (rectangleLines[2].onSegment(collisionPoint) || rectangleLines[3].onSegment(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        return newVelocity;
    }

    /**
     * The function get collision point and velocity and return the new velocity expected after the hit.
     * @param hitter the ball which hit the block.
     * @param collisionPoint  the collision point of the object with velocity to the collidable object.
     * @param currentVelocity the velocity of the object.
     * @return new velocity of the object after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle rectangle = getCollisionRectangle();
        Line[] rectangleLines = rectangle.getLines(); // [0] - upperLine, [1] - lowerLine, [2] leftLine, [3] rightLine.
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        if (rectangleLines[0].onSegment(collisionPoint) || rectangleLines[1].onSegment(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }

        if (rectangleLines[2].onSegment(collisionPoint) || rectangleLines[3].onSegment(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }

        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * The function gets a draw surface and draws this block on it.
     * @param d draw surface.
     */
    public void drawOn(DrawSurface d) {
        Point upperLeft = this.rectangle.getUpperLeft();

        int upperLeftX = (int) upperLeft.getX();
        int upperLeftY = (int) upperLeft.getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        d.setColor(Color.black);
        d.drawRectangle(upperLeftX - 1, upperLeftY - 1, width + 1, height + 1);

        d.setColor(color);
        d.fillRectangle(upperLeftX, upperLeftY, width, height);
    }

    /**
     * The function adds the sprite to both given game sprite and collidable collection.
     * @param g given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }
}
