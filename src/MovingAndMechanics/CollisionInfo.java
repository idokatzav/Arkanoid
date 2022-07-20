package MovingAndMechanics;
import CollidableAndSpriteObjects.Collidable;
import GeometricPrimitives.Point;

/**
 * MovingAndMechanics.CollisionInfo class. Contains the collision GeometricPrimitives.Point and the collisionObject -
 * what let us use them to calculate
 * the new place after the hit and the new MovingAndMechanics.Velocity.
 * Methods: Constructor.
 *          Getters for collisionPoint and collisionObject.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * Constructor for MovingAndMechanics.CollisionInfo.
     * @param p the collision point.
     * @param c the collidable to add.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = new Point(p);
        this.collisionObject = c;
    }
    /**
     * The point at which the collision occurs.
     * @return copy of the collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint);
    }
    /**
     * The collidable object involved in the collision.
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
