package GeometricPrimitives;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Circle class - a type of BaseGeometry.
 */
public class Circle implements BaseGeometry {
    private int xCenter;
    private int yCenter;
    private int radius;
    private Color color;
    private Color color2;
    private boolean filled;
    /**
     * Constructor.
     * @param x the center x value.
     * @param y the center y value.
     * @param radius the radius.
     * @param color the color. It is also the default color of the area of the circle if filled.
     * @param filled is it filled (is it a filled circle).
     */
    public Circle(int x, int y, int radius, Color color, boolean filled) {
        this.xCenter = x;
        this.yCenter = y;
        this.radius = radius;
        this.color = color;
        this.color2 = color;
        this.filled = filled;
    }
    /**
     * Setter for the area color of the rectangle.
     * @param color the new color.
     */
    public void setInnerColor(Color color) {
        this.color2 = color;
    }
    /**
     * Setter for the fill area status.
     * @param filled the new status.
     */
    public void updateFillStatus(boolean filled) {
        this.filled = filled;
    }
    @Override
    public void drawOn(DrawSurface d) {
        if (this.filled) {
            d.setColor(this.color2);
            d.fillCircle(this.xCenter, this.yCenter, this.radius);
        }
        d.setColor(this.color);
        d.drawCircle(this.xCenter, this.yCenter, this.radius);
    }
}
