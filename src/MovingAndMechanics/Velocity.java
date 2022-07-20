package MovingAndMechanics;
import GeometricPrimitives.Point;

/**
 * MovingAndMechanics.Velocity specifies the change in position on the 'x' and the 'y' axes.
 * Methods: Constructor by dx and dy.
 *          Copy Constructor.
 *          static Constructor by angle (in degrees) and spped.
 *          applyToPoint - applies a given point to the place it may be after the effect of the velocity.
 *          equals.
 *          Getters and Setters for dx and dy.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * another constructor for velocity by angle and speed.
     * @param angle the angle in degrees.
     * @param speed the speed: sqrt(dx^2 + dy^2).
     * @return a new MovingAndMechanics.Velocity of that speed and angle.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
    /**
     * Constructor.
     * @param dx will be entered into this.dx.
     * @param dy will be entered into this.dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Copy constructor.
     * @param v the velocity to insert.
     */
    public Velocity(Velocity v) {
        if (v == null) {
            this.dx = 0;
            this.dy = 0;
            return;
        }
        this.dx = v.dx;
        this.dy = v.dy;
    }
    /**
     * Take a point with position x,y, and return a new point with position (x + dx, y + dy).
     * @param p the point that was given.
     * @return the new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * Checks for this velocity and another velocity if these velocities are equal.
     * @param v the another velocity.
     * @return true if equals, false otherwise.
     */
    public boolean equals(Velocity v) {
        return (Math.abs(this.dx - v.dx) <= Math.pow(10, -10)) && (Math.abs(this.dy - v.dy) <= Math.pow(10, -10));
    }
    /**
     * Getter for dx.
     * @return this.dx.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * Getter for dy.
     * @return dy.
     */
    public double getDy() {
        return dy;
    }
    /**
     * Setter for dx.
     * @param dx dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Setter for dy.
     * @param dy dy.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}
