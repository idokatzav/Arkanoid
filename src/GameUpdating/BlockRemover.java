package GameUpdating;
import CollidableAndSpriteObjects.Block;
import GameMain.GameLevel;
import GeometricPrimitives.Ball;
import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Constructor.
     * @param game the game.
     * @param removedBlocks the counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}