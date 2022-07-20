package GeometricPrimitives;
/**
 * Class GeometricPrimitives.Point. variable: double x, double y.
 * Methods: Constructor - gets x and y and construct it.
 *          Copy Constructor.
 *          distance - gets another point and calculate the distance between that one and the other point.
 *          equals - gets another point and checks if it is equal to that one, if equals return true, else false.
 *          Getters and Setters for x and y.
 */
public class Point {
    //variables.
    private double x;
    private double y;
    /**
     * The Constructor.
     * @param x x-Axis value.
     * @param y y-Axis value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Copy constructor.
     * @param p the point to copy.
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    /**
     * Returns the distance between this point to the point given in the name other.
     * @param other the another point which we have to calculate the distance between this one to it.
     * @return the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
    }
    /**
     * checks if that point equals to another point that was given.
     * @param other the another point.
     * @return true if equals, false else.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return ((Math.abs(this.x - other.getX()) < Math.pow(10, -10))
                && (Math.abs(this.y - other.getY()) < Math.pow(10, -10)));
    }
    /**
     * Get x-Axis value of this point.
     * @return the x-Axis value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * Get y-Axis value of this point.
     * @return the y-Axis value.
     */
    public double getY() {
        return this.y;
    }
    /**
     * Setter for x.
     * @param x new value for x.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Setter for y.
     * @param y new value for y.
     */
    public void setY(double y) {
        this.y = y;
    }
}
