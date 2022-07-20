package GameUpdating;
import CollidableAndSpriteObjects.Block;
import GameMain.GameLevel;
import GeometricPrimitives.Ball;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
import MovingAndMechanics.Velocity;
import java.awt.Color;
import java.util.Random;
/**
 * Ball Creator Class. Makes sum blocks to be special blocks that will create balls in hit.
 */
public class BallCreator implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private int numberOfBlocksToCreate;
    /**
     * Constructor.
     * @param game the game.
     * @param removedBalls the counter.
     * @param numberOfBallsToCreate the number of balls that will be created in hit
     */
    public BallCreator(GameLevel game, Counter removedBalls, int numberOfBallsToCreate) {
        this.numberOfBlocksToCreate = numberOfBallsToCreate;
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Point p = beingHit.getCollisionRectangle().getUpperLeft();
        Point p2 = new Point(p.getX() + beingHit.getCollisionRectangle().getWidth(),
                p.getY() + beingHit.getCollisionRectangle().getHeight());
        Line l = new Line(p, p2);
        Random random = new Random();
        for (int i = 0; i < numberOfBlocksToCreate; i++) {
            Ball b = new Ball(l.middle(), GameLevel.BALL_RADIUS, Color.WHITE);
            b.setPaddle(hitter.getPaddle());
            b.setEnvironment(hitter.getEnvironment());
            b.setXSize(GameLevel.WIDTH);
            b.setYSize(GameLevel.HEIGHT);
            b.setXMin(0);
            b.setYMin(0);
            b.setVelocity(Velocity.fromAngleAndSpeed(random.nextInt(360), GameLevel.BALL_VELOCITY));
            this.remainingBalls.increase(1);
            b.addToGame(this.game);
        }
    }
}
