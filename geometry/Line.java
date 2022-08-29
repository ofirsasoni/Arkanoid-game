package geometry;

// ofir sasoni
// 325690386

import biuoop.DrawSurface;
import core.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The class "Line" creates an object of line-segment with a start point and an end point.
 */
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color color;

    /**
     * Constructor.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     * @param x1 the x coordinate of the starting point.
     * @param y1 the y coordinate of the starting point.
     * @param x2 the x coordinate of the ending point.
     * @param y2 the y coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Constructor.
     * @param x1 the x coordinate of the starting point.
     * @param y1 the y coordinate of the starting point.
     * @param x2 the x coordinate of the ending point.
     * @param y2 the y coordinate of the ending point.
     * @param color the color of the line.
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }

    /**
     * a function which return the value of the x coordinate of the starting point.
     * @return value of the x coordinate of the starting point.
     */
    public double getX1() {
        return start.getX();
    }

    /**
     * a function which return the value of the y coordinate of the starting point.
     * @return value of the y coordinate of the starting point.
     */
    public double getY1() {
        return start.getY();
    }

    /**
     * a function which return the value of the x coordinate of the ending point.
     * @return value of the x coordinate of the ending point.
     */
    public double getX2() {
        return end.getX();
    }

    /**
     * a function which return the value of the y coordinate of the ending point.
     * @return value of the y coordinate of the ending point.
     */
    public double getY2() {
        return end.getY();
    }

    /**
     * a function which returns the starting point of the line.
     * @return the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * a function which returns the ending point of the line.
     * @return the ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * a function which returns the length of the line.
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * a function which calculates and returns the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;

        return new Point(middleX, middleY);
    }

    /**
     * a function which checks if two line are equal.
     * @param other a line objects
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()
                && this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY())
                || (this.start.getX() == other.end.getX() && this.start.getY() == other.end.getY()
                && this.end.getX() == other.start.getX() && this.end.getY() == other.start.getY()));
    }

    /**
     * The function gets a point and checks if the point is on this line segment.
     * @param point the point we want to check if is on the line.
     * @return true if the point is on this line segment, false otherwise.
     */
    public boolean onSegment(Point point) {
        double x = point.getX();
        double y = point.getY();

        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());

        return (minX <= x && x <= maxX) && (minY <= y && y <= maxY);
    }

    /**
     * a function which calculates and returns the incline of the line.
     * @return the incline of the line.
     */
    private double incline() {
        double numerator = this.start.getY() - this.end.getY();
        double denominator = this.start.getX() - this.end.getX();
        if (numerator == 0 || denominator == 0) {
            return 0;
        }
        return numerator / denominator;
    }

    /**
     * a function which calculates and returns the value of the y coordinate of the intersection of the line
     * with the y axe.
     * @return the value of the y coordinate of the intersection of the line with the y axe.
     */
    private double intersectionWithY() {
        if (this.start.getX() == this.end.getX()) {
            return 0;
        }
        return -this.start.getX() * this.incline() + this.start.getY();
    }

    /**
     * The function gets a line as a parameter, and checks if this line intersecting with the given line, using the
     * orientation way of checking, as described in:
     * https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/.
     * @param other given line.
     * @return true if this line intersecting with the given line, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point p1 = this.start;
        Point q1 = this.end;
        Point p2 = other.start;
        Point q2 = other.end;
        // Find the four orientations needed for general and
        // special cases
        int o1 = p2.orientation(p1, q1);
        int o2 = q2.orientation(p1, q1);
        int o3 = p1.orientation(p2, q2);
        int o4 = q1.orientation(p2, q2);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == 0 && this.onSegment(p2)) {
            return true;
        }

        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == 0 && this.onSegment(q2)) {
            return true;
        }

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == 0 && other.onSegment(p1)) {
            return true;
        }

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == 0 && other.onSegment(q1)) {
            return true;
        }

        return false;
    }

    /**
     * The function calculates the intersection point of two line when both line parallel to one of the axes.
     * @param other given line.
     * @return intersection point if the two line have intersection point, null otherwise.
     */
    private Point intersectionOfTwoLinesParallelToAxes(Line other) {
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()
                && this.start.getX() == other.start.getX()) { // both lines are x = a for the same a value.
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            double thisMinY = Math.min(this.start.getY(), this.end.getY());
            double otherMaxY = Math.max(other.start.getY(), other.end.getY());
            double otherMinY = Math.min(other.start.getY(), other.end.getY());

            if (thisMaxY == otherMinY) {
                return new Point(this.start.getX(), thisMaxY);
            }
            if (thisMinY == otherMaxY) {
                return new Point(this.start.getX(), thisMinY);
            }
        }
        if (this.start.getY() == this.end.getY() && other.start.getY() == other.end.getY()
                && this.start.getY() == other.start.getY()) { // both lines are y = a for the same a value.
            double thisMaxX = Math.max(this.start.getX(), this.end.getX());
            double thisMinX = Math.min(this.start.getX(), this.end.getX());
            double otherMaxX = Math.max(other.start.getX(), other.end.getX());
            double otherMinX = Math.min(other.start.getX(), other.end.getX());

            if (thisMaxX == otherMinX) {
                return new Point(thisMaxX, this.start.getY());
            }
            if (thisMinX == otherMaxX) {
                return new Point(thisMinX, this.start.getY());
            }
        }
        if (this.start.getY() == this.end.getY() && other.start.getX() == other.end.getX()) { // first line is
            // x = a and the second line is y = b.
            if (this.onSegment(new Point(other.start.getX(), this.start.getY()))
                    && other.onSegment(new Point(other.start.getX(), this.start.getY()))) {
                return new Point(other.start.getX(), this.start.getY());
            }
        }
        if (this.start.getX() == this.end.getX() && other.start.getY() == other.end.getY()) { // first line is
            // y = a and the second line is x = b.
            if (this.onSegment(new Point(this.start.getX(), other.start.getY()))
                    && other.onSegment(new Point(this.start.getX(), other.start.getY()))) {
                return new Point(this.start.getX(), other.start.getY());
            }
        }
        return null;
    }

    /**
     * The function calculates the intersection point of two line when one of them parallel to one of the axes.
     * @param other given line.
     * @return intersection point if the two line have intersection point, null otherwise.
     */
    private Point intersectionWhenOneLineParallelToAxe(Line other) {
        if (this.start.getX() == this.end.getX()) { // if the first line is parallel to the y axe:
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            double thisMinY = Math.min(this.start.getY(), this.end.getY());
            double possibleIntersectionY = other.incline() * this.start.getX() + other.intersectionWithY();

            if (thisMinY <= possibleIntersectionY && possibleIntersectionY <= thisMaxY) {
                return new Point(this.start.getX(), possibleIntersectionY);
            }
        }
        if (this.start.getY() == this.end.getY()) { // if the first line is parallel to the x axe:
            double thisMaxX = Math.max(this.start.getX(), this.end.getX());
            double thisMinX = Math.min(this.start.getX(), this.end.getX());
            double possibleIntersectionX = (this.start.getY() - other.intersectionWithY()) / other.incline();

            if (thisMinX <= possibleIntersectionX && possibleIntersectionX <= thisMaxX) {
                return new Point(possibleIntersectionX, this.start.getY());
            }
        }
        return null;
    }

    /**
     * The function calculates the intersection point of two parallel lines, when none of them parallel to an axe.
     * @param other given line.
     * @return intersection point if the two line have intersection point, null otherwise.
     */
    private Point intersectionOfParallelLines(Line other) {
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double otherMaxY = Math.max(other.start.getY(), other.end.getY());
        double otherMinY = Math.min(other.start.getY(), other.end.getY());

        // Checking if the ending of one of the lines is a starting of the second line.
        // returning null if not, because the remaining options are that there are zero or infinity
        // intersection points.
        if (thisMaxY == otherMinY) {
            return new Point(this.incline() * thisMaxY + this.intersectionWithY(), thisMaxY);
        }
        if (thisMinY == otherMaxY) {
            return new Point(this.incline() * thisMinY + this.intersectionWithY(), thisMinY);
        }
        return null;
    }

    /**
     * a function which calculates the intersection point with this line and other line given as a parameter, if exists.
     * @param other a line object.
     * @return null if the intersection point not exists ( or not single ), the intersection point otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }

        if (this.incline() == 0 && other.incline() == 0) { // both lines are x = a for the same a value.
            return intersectionOfTwoLinesParallelToAxes(other);
        }

        if (this.incline() == 0) { // checking if only the first line is parallel to one of the axes.
            return this.intersectionWhenOneLineParallelToAxe(other);
        }

        if (other.incline() == 0) { // checking if only the second line is parallel to one of the axes.
            return other.intersectionWhenOneLineParallelToAxe(this);
        }

        if (this.incline() == other.incline()) {
            return this.intersectionOfParallelLines(other);
        }

        // we need to find the intersection point between the following two lines: y = m1x + b1, y = m2x + b2
        // if we develop the equation m1x + b1 = m2x + b2, we will get the following result (m1 - m2)x = b2 - b1
        // which leads us to the value x of the intersection point between the lines: x = (b2 - b1) / (m1 - m2)
        // placing the value of x in the first equation will lead us to the value of y:
        // y = m1 * (b2 - b1) / (m1 - m2) + b1. therefore, the intersection point is:
        // ((b2 - b1) / (m1 - m2), m1 * (b2 - b1) / (m1 - m2) + b1)
        // its important to say that (m1 - m2) would never be equal to 0 because of the checks we do in the
        // "isIntersecting" function.

        double x = (other.intersectionWithY() - this.intersectionWithY()) / (this.incline() - other.incline());
        double y = this.incline() * x + this.intersectionWithY();

        return new Point(x, y);
    }

    /**
     * The function gets a rectangle and returns the closest intersection of this line with the rectangle, or null if
     * no intersection is about to happen.
     * @param rect given rectangle.
     * @return If this line does not intersect with the rectangle, return null. Otherwise, return the closest
     * intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        double minDistance = -1;
        Point closestPoint = null;

        for (Point point : points) {
            if (point == null) {
                continue;
            }
            if (minDistance == -1 || point.distance(this.start) < minDistance) {
                minDistance = point.distance(this.start);
                closestPoint = point;
            }
        }

        return closestPoint;
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.drawLine((int) this.getX1(), (int) this.getY1(), (int) this.getX2(), (int) this.getY2());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
