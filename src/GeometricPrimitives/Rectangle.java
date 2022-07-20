package GeometricPrimitives;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
/**
 * GeometricPrimitives.Rectangle class. Let us save information about the rectangles that will be shown on the screen.
 * Methods: Copy constructor.
 *          Constructor - by upperLeft point, width and height.
 *          areEquals - check if to double a and b are equals (|a-b| <= 10^-10).
 *          getSides - gives an array of lines that are the sides.
 *          intersectionPoints - return a list of the intersection points of a given line and the rectangle.
 *          isInside - check is point is inside the rectangle or inside the given radius from the rectangle.
 *          Getters and Setters for upperLeft point, width and height.
 */
public class Rectangle implements BaseGeometry {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;
    private Color color2;
    private boolean isFilled;
    /**
     * Copy constructor for rectangle.
     * @param rect the new rectangle.
     */
    public Rectangle(Rectangle rect) {
        this.upperLeft = new Point(rect.upperLeft);
        this.width = rect.width;
        this.height = rect.height;
        this.color = rect.color;
        this.color2 = rect.color2;
        this.isFilled = rect.isFilled;
    }
    /**
     * Checks if two doubles equal.
     * @param a the first double.
     * @param b the second double.
     * @return if they are equal |a-b|<10^-10: we'll return true, otherwise false.
     */
    private boolean areEquals(double a, double b) {
        return (Math.abs(a - b) <= Math.pow(10, -10));
    }
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upperLeft point.
     * @param width the width.
     * @param height the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft);
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }
        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
        this.color = Color.WHITE;
        this.color2 = Color.WHITE;
        this.isFilled = false;
    }
    /**
     * Returns the sides of the rectangle. 0 upperLeft-upperRight, 1 upperRight-downerRight, 2 downerRight-downerLeft,
     * 3 downerLeft-upperLeft.
     * @return an array of the sides.
     */
    public Line[] getSides() {
        Line[] sides = new Line[4];
        Point p1, p2, p3, p4;
        p1 = new Point(this.upperLeft);
        p2 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        p3 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        p4 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        sides[0] = new Line(p1, p2);
        sides[1] = new Line(p2, p3);
        sides[2] = new Line(p3, p4);
        sides[3] = new Line(p4, p1);
        return sides;
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line to find intersections with that rectangle.
     * @return list of points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //Create a new Linked List.
        java.util.List<Point> lst = new ArrayList<Point>();
        //Create a line array with the sides of the rectangle.
        Line[] lines = new Line[4];
        //we'll create p1 the upper right point of the rectangle.
        Point p1 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        //we'll create p2 the downer left point of the rectangle.
        Point p2 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        //we'll create p3 the downer right point of the rectangle.
        Point p3 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        //side upperLeft p1.
        lines[0] = new Line(this.upperLeft, p1);
        //side upperLeft p2.
        lines[1] = new Line(this.upperLeft, p2);
        //side p1p3.
        lines[2] = new Line(p1, p3);
        //side p2p3.
        lines[3] = new Line(p2, p3);
        //we'll find all of the intersection point of the rectangle sides with the given line and put them in an array.
        Point[] points = new Point[4];
        for (int i = 0; i < points.length; i++) {
            points[i] = line.intersectionWith(lines[i]);
        }
        /*
         * for each point in the array, we'll check if it's null or it contained in the array before, if not both,
         * we'll add it into the list, otherwise not.
         */
        for (int i = 0; i < points.length; i++) {
            //if it's not null we'll check if it wasn't in the array with a lower index.
            if (points[i] != null) {
                //we'll save a boolean to check if we found point equals to this.
                boolean boolA = true;
                //if one of the points equals, the boolean will change to false.
                for (int j = 0; j < i && boolA; j++) {
                    if (points[i].equals(points[j])) {
                        boolA = false;
                    }
                }
                //if the boolean still true, we'll add that point.
                if (boolA) {
                    lst.add(lst.size(), points[i]);
                }
            }
        }
        return lst;
    }
    /**
     * Getter for width.
     * @return the width.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Getter for height.
     * @return the height.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left point of the rectangle.
     * @return copy of the upper left point.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft);
    }
    /**
     * Setter for width.
     * @param width the new width.
     */
    public void setWidth(double width) {
        if (width < 0) {
            this.width = 0;
            return;
        }
        this.width = width;
    }
    /**
     * Checks if point p inside this rectangle.
     * @param p the point.
     * @return true if inside, otherwise false.
     * @param r the radius of thing of part of the ball inside the paddle.
     */
    public boolean isInside(Point p, int r) {
        return (p.getX() > (this.upperLeft.getX() - r))
                && (p.getX() < (this.upperLeft.getX() + this.width + r))
                && (p.getY() > (this.upperLeft.getY() - r))
                && (p.getY() < (this.upperLeft.getY() + this.height));
    }
    /**
     * Setter for height.
     * @param height the new height.
     */
    public void setHeight(double height) {
        if (height < 0) {
            this.height = 0;
            return;
        }
        this.height = height;
    }
    /**
     * Setter for upperLeft point.
     * @param upperLeft the new GeometricPrimitives.Point.
     */
    public void setUpperLeft(Point upperLeft) {
        if (upperLeft.getX() < 0 || upperLeft.getY() < 0) {
            this.upperLeft = new Point(0, 0);
            return;
        }
        this.upperLeft = new Point(upperLeft);
    }
    /**
     * Setter for the color of the perimeter.
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Setter for the area color.
     * @param color2 the new color.
     */
    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    /**
     * Update isFilled status.
     * @param isFilled the new status.
     */
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int xUpperLeft = (int) (Math.round(this.upperLeft.getX()));
        int yUpperLeft = (int) (Math.round(this.upperLeft.getY()));
        int w = (int) (Math.round(this.width));
        int h = (int) (Math.round(this.height));
        if (this.isFilled) {
            d.setColor(this.color2);
            d.fillRectangle(xUpperLeft, yUpperLeft, w, h);
        }
        d.setColor(this.color);
        d.drawRectangle(xUpperLeft, yUpperLeft, w, h);
    }
}
