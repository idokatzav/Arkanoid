package GeometricPrimitives;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * GeometricPrimitives.Line class, the creating of line and checks it's interaction with other points and lines.
 * Methods: isZero - gets double a and checks if it equals to zero (|a| < 10^-10)
 *          Constructors and Copy Constructor.
 *          length - returns the length of the line.
 *          isIntersecting - checks if intersects with another given line.
 *          lineIntoMatrix - returns a matrix of the this line and another as y = mx + b.
 *          isOnLine - check if a given point is on that line.
 *          intersectionWith - returns the intersection of this line with another if there is one intersection.
 *          equals - checks if it equals to another line.
 *          closestIntersectionToStartOfLine - returns the closeset intersection of that line with another rectangle
 *                                             to the start of that line.
 */
public class Line implements BaseGeometry {
    //variables.
    private Point start;
    private Point end;
    private Color color;
    /**
     * Checks if a double is 0.
     * @param a the double.
     * @return true if equals, false otherwise.
     */
    public boolean isZero(double a) {
        return Math.abs(a) <= Math.pow(10, -10);
    }
    //constructors
    /**
     * Constructor for GeometricPrimitives.Line - gets start point and end point and create segment by them.
     * @param start the start point.
     * @param end the end point.
     */
    public Line(Point start, Point end) {
        //we'll set the this.start as a new point with the values of start that was given.
        this.start = new Point(start.getX(), start.getY());
        //we'll set the this.end as a new point with the values of end that was given.
        this.end = new Point(end.getX(), end.getY());
        this.color = Color.WHITE;
    }
    /**
     * Constructor for GeometricPrimitives.Line - gets values of start and end points of a line and creates
     * line like this.
     * @param x1 the start point x-axis value.
     * @param y1 the start point y-axis value.
     * @param x2 the end point x-axis value.
     * @param y2 the end point y-axis value.
     */
    public Line(double x1, double y1, double x2, double y2) {
        //we'll create this.start and this.end by the given values.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = Color.WHITE;
    }
    // Return the length of the line
    /**
     * Calculates the length of this line.
     * @return the length.
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * Setter for the color.
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Returns the middle point of the line.
     * @return the middle point.
     */
    public Point middle() {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }
    /**
     * Returns the start point of the line.
     * @return the point.
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }
    /**
     * Returns the end point of the line.
     * @return the point.
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }
    /**
     * Checks if this and another line given are intersecting.
     * @param other the another line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //we want to convert
        double[][] lines = this.linesIntoMatrix(other);
        //if both of them are a point, we'll check if these are the same points, if it is return true, false otherwise.
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.start.equals(other.start)) {
                return true;
            }
            return false;
        }
        //if only one line is a point we'll check if it is on the other line, if it is return true, false otherwise.
        if (this.start.equals(this.end)) {
            if (other.isOnLine(this.start)) {
                return true;
            }
            return false;
        }
        if (other.start.equals(other.end)) {
            if (this.isOnLine(other.start)) {
                return true;
            }
            return false;
        }
        //if the first line is in the order of 0 = x + a, it can't be 0 = 0 because it means that it's a point.
        if (this.isZero(lines[0][0])) {
            //if also the other like this so: 1: 0 = x + a ,2: 0 = x + b and if a - b = 0 return true, else false.
            if (this.isZero(lines[1][0])) {
                if (this.isZero(lines[0][2] - lines[1][2])) {
                    /*
                     * if there is any intersect of the lines (one of the points of start or end of the first is on the
                     * second or one of the points of the second is on the first so they are intersecting), otherwise
                     * it means the start end the end of both are not between the start or the end of the other
                     * so these can't have any intersect between them.
                     */
                    return this.isOnLine(other.start) || this.isOnLine(other.end)
                            || other.isOnLine(this.start) || other.isOnLine(this.end);
                }
                //if it's a != b return false.
                return false;
            }
            //now we'll find x and and y by 0 = x + lines[0][2] and y = lines[1][1] * x + lines[1][2]
            double x = -lines[0][2];
            double y = lines[1][1] * x  + lines[1][2];
            Point p = new Point(x, y);
            //if the point on both of them, we'll return true, false otherwise.
            if (this.isOnLine(p) && other.isOnLine(p)) {
                return true;
            }
            return false;
        } else if (this.isZero(lines[1][0])) { //if the second one is in order of 0 = x + lines[1][2]
            //we'll find the intersection point.
            double x = -lines[1][2];
            double y = lines[0][1] * x  + lines[0][2];
            Point p = new Point(x, y);
            //if it's on both line, return true, else false.
            if (this.isOnLine(p) && other.isOnLine(p)) {
                return true;
            }
            return false;
        }
        if (this.isZero(lines[0][1])) { //if the first in order of y = lines[0][2]
            if (this.isZero(lines[1][1])) { //if the second is in order of y = lines[1][2]
                /*
                 * if the start of one of them or its end on the other it means that there is an intersection
                 * point, else it means that there is not any intersections between them.
                 * */
                return this.isOnLine(other.start) || this.isOnLine(other.end)
                        || other.isOnLine(this.start) || other.isOnLine(this.end);
            }
            //else, it's 1: y = lines[0][2], 2: y = lines[1][1] + lines[1][2], we'll solve it.
            double y = lines[0][2];
            double x = (y - lines[1][2]) / (lines[1][1]);
            Point p = new Point(x, y);
            //if this point is on both of them we'll return true, false otherwise.
            return this.isOnLine(p) && other.isOnLine(p);
        } else if (this.isZero(lines[1][1])) {
            //if the second line in order of y = a and the first not we'll solve.
            double y = lines[1][2];
            double x = (y - lines[0][2]) / (lines[0][1]);
            Point p = new Point(x, y);
            //if it's on both line we'll return true, false otherwise.
            return this.isOnLine(p) && other.isOnLine(p);
        } //if m1 = m2 so we have to check if these segments on the same line.
        if (this.isZero(lines[0][1] - lines[1][1])) {
            //if alse b1 = b2 it means that these are the same lines becaues it's not in order of 0 = x + a.
            if (this.isZero(lines[0][2] - lines[1][2])) {
                return this.isOnLine(other.start) || this.isOnLine(other.end) || other.isOnLine(this.start)
                        || other.isOnLine(this.end);
            }
            //if b1 != b2 so these are not the same lines, so we'll return false.
            return false;
        } else {
            //else both are in order of y = mx + b and m1 != m2, we'll find the intersection point.
            double x = (lines[1][2] - lines[0][2]) / (lines[0][1] - lines[1][1]);
            double y = x * lines[0][1] + lines[0][2];
            Point p = new Point(x, y);
            //if it's on both lines we'll return true, false otherwise.
            return this.isOnLine(p) && other.isOnLine(p);
        }
    }
    /**
     * Makes a 2d array of the lines, as y = mx + b in that order, if it's x = a so: 0 * y = 1 * x - a, if
     * y = a so y = 0 * x + a.
     * @param other the another line.
     * @return the system.
     */
    private double[][] linesIntoMatrix(Line other) {
        //we'll show these lines as y = mx - mx1 + y1.
        double[][] lines = new double[2][3];
        double x1, x2, y1, y2;
        x1 = this.start.getX();
        x2 = this.end.getX();
        y1 = this.start.getY();
        y2 = this.end.getY();
        if (this.isZero(x2 - x1)) {
            lines[0][0] = 0;
            lines[0][1] = 1;
            lines[0][2] = -1 * this.start.getX();
        } else if (this.isZero(y2 - y1)) {
            lines[0][0] = 1;
            lines[0][1] = 0;
            lines[0][2] = this.start.getY();
        } else {
            lines[0][0] = 1;
            lines[0][1] = (y2 - y1) / (x2 - x1);
            lines[0][2] = y1 - x1 * ((y2 - y1) / (x2 - x1));
        }
        //we'll do the same for other.
        x1 = other.start.getX();
        x2 = other.end.getX();
        y1 = other.start.getY();
        y2 = other.end.getY();
        if (this.isZero(x2 - x1)) {
            lines[1][0] = 0;
            lines[1][1] = 1;
            lines[1][2] = -1 * other.start.getX();
        } else if (this.isZero(y2 - y1)) {
            lines[1][0] = 1;
            lines[1][1] = 0;
            lines[1][2] = other.start.getY();
        } else {
            lines[1][0] = 1;
            lines[1][1] = (y2 - y1) / (x2 - x1);
            lines[1][2] = y1 - x1 * ((y2 - y1) / (x2 - x1));
        }
        return lines;
    }
    /**
     * Gets a line as this and point p and return true if p is on the segment by triangle inequality.
     * @param p the point.
     * @return if it's on the segment.
     */
    public boolean isOnLine(Point p) {
        //check triangle inequality.
        return this.isZero(p.distance(this.start) + p.distance(this.end) - this.length());
    }
    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other the another line.
     * @return the intersection point if there is one like this, else - null.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other) && !this.start.equals(this.end)) {
            return null;
        }
        double[][] lines = this.linesIntoMatrix(other);
        if (this.isIntersecting(other)) {
            if (this.start.equals(this.end) && other.start.equals(other.end)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            //if detA equals 0 it means or one of the lines is a point, or infinity intersection points
            if (this.isZero(lines[0][0] - lines[1][0]) && this.isZero(lines[0][1] - lines[1][1])) {
                //if one starts in the end of the other, we'll start with the case of x = a:
                if (this.isZero(this.start.getX() - this.end.getX())) {
                    double minThis = Math.min(this.start.getY(), this.end.getY());
                    double maxThis = Math.max(this.start.getY(), this.end.getY());
                    double minOther = Math.min(other.start.getY(), other.end.getY());
                    double maxOther = Math.max(other.start.getY(), other.end.getY());
                    if (this.isZero(maxThis - minOther)) {
                        return new Point(this.start.getX(), maxThis);
                    } else if (this.isZero(minThis - maxOther)) {
                        return new Point(this.start.getX(), maxOther);
                    }
                }
                //now the other cases.
                double minThisX = Math.min(this.start.getX(), this.end.getX());
                double maxThisX = Math.max(this.start.getX(), this.end.getX());
                double minOtherX = Math.min(other.start.getX(), other.end.getX());
                double maxOtherX = Math.max(other.start.getX(), other.end.getX());
                if (this.isZero(maxThisX - minOtherX)) {
                    if (this.start.getX() > this.end.getX()) {
                        return new Point(maxThisX, this.start.getY());
                    }
                    return new Point(maxThisX, this.end.getY());
                } else if (this.isZero(minThisX - maxOtherX)) {
                    if (this.start.getX() < this.end.getX()) {
                        return new Point(minThisX, this.start.getY());
                    }
                    return new Point(minThisX, this.end.getY());
                }
                //else it means it's infinity intersection points, so we'll retrun null.
                return null;
            }
            //if one of the segments is a point we'll return that point.
            if (this.start.equals(this.end)) {
                return new Point(this.start.getX(), this.start.getY());
            } else if (other.start.equals(other.end)) {
                return new Point(other.start.getX(), other.start.getY());
            }
            if (this.isZero(lines[0][1] - lines[1][1])) {
                if (this.isZero(lines[0][0])) {
                    double y = lines[1][2] - lines[0][2] * lines[1][1];
                    //we created lines[0][1] to be one in that case.
                    double x = -lines[0][2];
                    return new Point(x, y);
                } else if (this.isZero(lines[1][0])) {
                    double y = lines[0][2] - lines[1][2] * lines[0][1];
                    //we created lines[0][1] to be one in that case.
                    double x = -lines[1][2];
                    return new Point(x, y);
                }
                //we'll not reach that part cause enter this means that none of them is a point so lines([0]/[1])[0]!=0.
            }
            if (this.isZero(lines[0][0])) {
                if (this.isZero(lines[1][0])) {
                    double minThis = Math.min(this.start.getY(), this.end.getY());
                    double maxThis = Math.max(this.start.getY(), this.end.getY());
                    double minOther = Math.min(other.start.getY(), other.end.getY());
                    double maxOther = Math.max(other.start.getY(), other.end.getY());
                    if (maxOther == minThis) {
                        return new Point(-lines[0][2], maxOther);
                    } else if (minOther == maxThis) {
                        return new Point(-lines[0][2], maxThis);
                    }
                    return null;
                }
                //we created lines[0][1] to be one in that case.
                double x = -lines[0][2];
                double y = lines[1][1] * x + lines[1][2];
                return new Point(x, y);
            } else if (this.isZero(lines[1][0])) {
                double y = lines[0][2] - lines[1][2] * lines[0][1];
                //we created lines[0][1] to be one in that case.
                double x = -lines[1][2];
                return new Point(x, y);
            }
            //now, it cant be at y = 0 and x = 0 cause no one of them with start == end so lines[0][0]=1=lines[1][0].
            double x = (lines[1][2] - lines[0][2]) / (lines[0][1] - lines[1][1]);
            double y;
            if (this.isZero(lines[0][0])) {
                y = lines[1][1] * x + lines[1][2];
            } else {
                y = lines[0][1] * x + lines[0][2];
            }
            return new Point(x, y);
        }
        //else there is not any intersection point, so we'll return null.
        return null;
    }
    /**
     * Checks if two lines are same.
     * @param other the another line to check.
     * @return  true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }
    /**
     * If this line does not intersect with the rectangle, retrn null. Otherwise, return the closest intersection
     * point to the start of the line.
     * @param rect the rectangle.
     * @return the closest point if exists, otherwise null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //we'll get the list of the intersection points.
        java.util.List<Point> lst = rect.intersectionPoints(this);
        //if the list is empty, we'll return null.
        if (lst.isEmpty()) {
            return null;
        }
        //else we'll set for now the closest as the first point in the list.
        Point closest = lst.remove(0);
        /*
         * while the list is not empty, we'll try to find if the first point in it is closer to the start than the
         * current closest.
         */
        while (!lst.isEmpty()) {
            //if the current closest is less close than the currently first of the list, we'll set the first as closest.
            if (this.start.distance(closest) > this.start.distance(lst.get(0))) {
                //we'll update closest by remove the first point of the list from the list into closest.
                closest = lst.remove(0);
            } else { //else, we'll only remove the first point and not set it as the currently closest.
                lst.remove(0);
            }
        }
        //we'll return the closest point we found.
        return closest;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) (Math.round(this.start.getX())), (int) (Math.round(this.start.getY())),
                (int) (Math.round(this.end.getX())), (int) (Math.round(this.end.getY())));
    }
}