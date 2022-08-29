package core;

// Ofir Sasoni
// 325690386

/**
 * the class velocity creates an object of velocity ( of the ball, in the current mission ), with two parameters:
 * velocity in the x axe, and velocity in the y axe.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx the change in the position on the x axe.
     * @param dy the change in the position on the y axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor without input.
     */
    public Velocity() {
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * Constructor which gets an angle and a speed values, and returns a velocity object with dx and dy values.
     * @param angle the angle of the velocity of the object.
     * @param speed the speed of the object in the given angle.
     * @return velocity object with dx and dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));

        return new Velocity(dx, dy);
    }

    /**
     * Get method.
     * @return value of dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get method.
     * @return value of dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set method.
     * @param dx new value of dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set method.
     * @param dy new value of dy.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * a function which takes a point with position (x,y) and return a new point with position (x + dx, y + dy).
     * @param p a point with position (x,y).
     * @return new point with position (x + dx, y + dy).
     */
    public geometry.Point applyToPoint(geometry.Point p) {
        double x = p.getX();
        double y = p.getY();

        return new geometry.Point(x + this.dx, y + this.dy);
    }
}
