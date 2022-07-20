package GameUpdating;
import CollidableAndSpriteObjects.Block;
import GameMain.GameLevel;
import GeometricPrimitives.Ball;
import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
/**
 * BallRemover Class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * Constructor.
     * @param game the game.
     * @param removedBalls the counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeBallFromGame(hitter);
        this.remainingBalls.decrease(1);
    }
}
