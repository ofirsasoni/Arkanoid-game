package geometry;

import biuoop.DrawSurface;
import core.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The class represents a circle implements sprite.
 */
public class Circle implements Sprite {
    private int r;
    private int x;
    private int y;
    private Color color;
    private boolean isFilled;

    /**
     * Constructor.
     * @param x the x coordinate of the circle center.
     * @param y the y coordinate of the circle center.
     * @param r the radius.
     * @param color the color.
     * @param isFilled true if we want to fill it when drawing it ( making it a ball ), false otherwise.
     */
    public Circle(int x, int y, int r, Color color, boolean isFilled) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.isFilled = isFilled;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        if (isFilled) {
            d.fillCircle(x, y, r);
        } else {
            d.drawCircle(x, y, r);
        }
    }

    @Override
    public void timePassed() {
        return;
    }
}
