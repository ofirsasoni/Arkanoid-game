package geometry;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;
import core.Sprite;
import game.GameLevel;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The class represents a rectangle object.
 */
public class Rectangle implements Sprite {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructor.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color the color of the rectangle.
     */
    public Rectangle(Point upperLeft, int width, int height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    /**
     * Get method.
     * @return the width of this rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get method.
     * @return the height of this rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get method.
     * @return the upper left point of this rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * Get method.
     * @return the upper right point of this rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Get method.
     * @return the lower left point of this rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Get method.
     * @return the lower right point of this rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Get method.
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return new Line(getUpperLeft(), getUpperRight());
    }

    /**
     * Get method.
     * @return the lower line of the rectangle.
     */
    public Line getLowerLine() {
        return new Line(getLowerLeft(), getLowerRight());
    }

    /**
     * Get method.
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(getUpperLeft(), getLowerLeft());
    }

    /**
     * Get method.
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(getUpperRight(), getLowerRight());
    }

    /**
     * The function returns an array contains all the lines of the rectangle.
     * @return array contains this rectangle's lines.
     */
    public Line[] getLines() {
        Line[] lines = new Line[4];
        lines[0] = getUpperLine();
        lines[1] = getLowerLine();
        lines[2] = getLeftLine();
        lines[3] = getRightLine();

        return lines;
    }

    /**
     * Set method.
     * @param x new value of the x coordinate of the upper left point of the rectangle.
     */
    public void setUpperLeftX(double x) {
        this.upperLeft.setX(x);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line we want to check its intersection points with the rectangle.
     * @return List (possibly empty) of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.ArrayList<Point> points = new ArrayList<Point>();
        Line[] rectangleLines = this.getLines();

        for (Line rectangleLine : rectangleLines) {
            if (line.isIntersecting(rectangleLine) && line.intersectionWith(rectangleLine) != null) {
                points.add(line.intersectionWith(rectangleLine));
            }
        }

        return points;
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) upperLeft.getX() - 1, (int) upperLeft.getY() - 1, (int) width + 1, (int) height + 1);
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
