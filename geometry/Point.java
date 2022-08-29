package geometry;

// Ofir Sasoni
// 325690386

/**
 * The class "Point" is a class with two parameters: x and y. Its aim is to create a two-dimensional point, with
 * options of finding the distance between two points, check if two points are equal and get a point's x and y values.
 */
public class Point {
    public static final double ALLOWED_ERROR = Math.pow(10, -10);
    private double x;
    private double y;

    /**
     * constructor. a method which creates a point with given values x and y.
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * a method which returns the x value of this point.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * a method which returns the y value of this point.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set Method.
     * @param x new value of this.x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set Method.
     * @param y new value of this.y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * a method which calculating and returning the distance between two points.
     * @param other a point object.
     * @return distance between this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y -  other.y) * (this.y - other.y)));
    }

    /**
     * a method which checks if two points are equal.
     * @param other a point object.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) <= ALLOWED_ERROR && Math.abs(this.y - other.y) <= ALLOWED_ERROR;
    }

    /**
     * The function returns the orientation of the 3 given point: collinear/clockwise/counterclockwise.
     * @param q second point.
     * @param r third point.
     * @return 0 if collinear, 1 if clockwise, 2 if counterclockwise.
     */
    public int orientation(Point q, Point r) {
        double val = (q.y - this.y) * (r.x - q.x) - (q.x - this.x) * (r.y - q.y);

        if (val == 0) {
            return 0; // collinear
        }

        return (val > 0) ? 1 : 2; // clockwise or counterclockwise
    }
}
