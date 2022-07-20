package CollidableAndSpriteObjects;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.Velocity;
import GeometricPrimitives.Ball;
/**
 * Interface CollidableAndSpriteObjects.Collidable. Interface that is the base of CollidableAndSpriteObjects.
 * Collidable objects.
 */
public interface Collidable {
    /**
     * Return the "collistion shape" of the object.
     * @return the rectangle collision.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter the hitter ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
