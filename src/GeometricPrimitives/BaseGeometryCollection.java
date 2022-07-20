package GeometricPrimitives;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of baseGeometries.
 */
public class BaseGeometryCollection {
    private List<BaseGeometry> lst;
    /**
     * Constructor.
     */
    public BaseGeometryCollection() {
        this.lst = new ArrayList<BaseGeometry>();
    }
    /**
     * Add Base Geometry to the collection.
     * @param b the base geometry to add.
     */
    public void addBaseGeometry(BaseGeometry b) {
        this.lst.add(lst.size(), b);
    }
    /**
     * Draw all the base geometries.
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (BaseGeometry b: this.lst) {
            b.drawOn(d);
        }
    }
}
