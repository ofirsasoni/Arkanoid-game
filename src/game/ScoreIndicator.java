package game;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;
import core.Sprite;
import geometry.Point;
import geometry.Rectangle;
import listenersRemovers.ScoreTrackingListener;

import java.awt.Color;

/**
 * The class represents the block which shows the current score of the game.
 */
public class ScoreIndicator implements Sprite {
    private ScoreTrackingListener score;
    private DrawSurface d;
    private static final Rectangle RECTANGLE = new Rectangle(new Point(0, 0), 600, 20);
    private static final Color COLOR = Color.white;

    /**
     * Constructor.
     * @param score the current score.
     * @param d the draw surface we want to draw the score indicator on.
     */
    public ScoreIndicator(ScoreTrackingListener score, DrawSurface d) {
        this.score = score;
        this.d = d;
    }

    /**
     * The function gets a draws the Sprite object on the given draw surface.
     * @param d given draw surface.
     */
    public void drawOn(DrawSurface d) {
        Point upperLeft = RECTANGLE.getUpperLeft();
        d.setColor(Color.black);

        int upperLeftX = 0;
        int upperLeftY = 0;
        int width = 800;
        int height = 30;

        d.drawRectangle(upperLeftX, upperLeftY, width, height);
        d.setColor(COLOR);
        d.fillRectangle(upperLeftX, upperLeftY, width, height);
        d.setColor(Color.black);
        d.drawText(350, 25, "Score:" + score.getScore(), 15);
    }


    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }

    /**
     * The function adds the sprite to both given game sprite and collidable collection.
     * @param g given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
