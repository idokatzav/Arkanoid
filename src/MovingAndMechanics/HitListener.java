package MovingAndMechanics;
import GeometricPrimitives.Ball;
import CollidableAndSpriteObjects.Block;
/**
 * HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that is being hit.
     * @param hitter the hitter ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

