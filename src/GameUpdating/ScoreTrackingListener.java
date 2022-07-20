package GameUpdating;
import CollidableAndSpriteObjects.Block;
import GeometricPrimitives.Ball;
import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
/**
 * ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor.
     * @param currentScore the Score counter.
     */
    public ScoreTrackingListener(Counter currentScore) {
        this.currentScore = currentScore;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
