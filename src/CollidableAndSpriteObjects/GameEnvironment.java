package CollidableAndSpriteObjects;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.CollisionInfo;
import java.util.ArrayList;
import java.util.List;
/**
 * GameMain.Game environment class. Contians the information about the object in the environment.
 * Methods: Constructor.
 *          addCollidable - add collidable to the list of the collidable.
 *          getCollidableList - returns the list of the collidable.
 *          getClosestCollision - returns a ColliosionInfo object contains information about the collision.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidablesList;
    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<Collidable>();
    }
    /**
     * add the given collidable to the environment.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidablesList.add(this.collidablesList.size(), c);
    }
    /**
     * Getter for this.collidablesList.
     * @return the list.
     */
    public List<Collidable> getCollidablesList() {
        return this.collidablesList;
    }
    /**
     * Removes a given collidable from game objects.
     * @param c the object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesList.remove(c);
    }
    /**
     * Remove all the Collidables.
     */
    public void removeAllCollidables() {
        List<Collidable> lst = new ArrayList<Collidable>(this.collidablesList);
        for (Collidable c: lst) {
            this.collidablesList.remove(c);
        }
    }
    /**
     * Assume an object moving from line.start() to line.end(). If this object will not collide with any of the
     * collidables in this collection, return null. Else, return the information about the closest collision
     * that is going to occur.
     * @param trajectory the line.
     * @return the Collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //create a point and CollidableAndSpriteObjects.Collidable to save the collidable and the point.
        Point closest = null;
        Collidable c1 = null;
        //foreach CollidableAndSpriteObjects.Collidable of these collidable
        for (Collidable c: this.collidablesList) {
            //we'll get that rectangle.
            Rectangle rect = c.getCollisionRectangle();
            //we'll get the list of intersections.
            java.util.List<Point> lst1 = rect.intersectionPoints(trajectory);
            //while that list isn't empty we'll check if its currently first point is the closer.
            while (!lst1.isEmpty()) {
                if (closest == null) {
                    /*
                     * if it isn't empty, we'll set that CollidableAndSpriteObjects.Collidable as the current
                     * collidable and the point as current closest.
                     */
                    closest = new Point(lst1.remove(0));
                    c1 = c;
                } else { //wels, we'll check for the current first point from the intersection is the closest.
                    Point p = lst1.remove(0);
                    //if the current first point is closer than closest we'll update the closer.
                    if (p.distance(trajectory.start()) < closest.distance(trajectory.start())) {
                        c1 = c;
                        closest = p;
                    }
                }
            }
        }
        //if there is not any point like this we'll return null.
        if ((closest == null) && (c1 == null)) {
            return null;
        }
        //we'll return the new Collision info.
        return new CollisionInfo(closest, c1);
    }
}
