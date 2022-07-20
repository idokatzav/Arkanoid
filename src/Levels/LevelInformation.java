package Levels;

import CollidableAndSpriteObjects.Block;
import CollidableAndSpriteObjects.Paddle;
import CollidableAndSpriteObjects.Sprite;
import MovingAndMechanics.Velocity;

import java.util.List;
/**
 * Level information interface.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls.
     * @return the number
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return the list of the velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * Returns the paddle speed.
     * @return the paddle speed.
     */
    int paddleSpeed();
    /**
     * Returns the number of the level.
     * @return the number of the level.
     */
    int getLevelNum();
    /**
     * Returns the paddle width.
     * @return the paddle width.
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return the name of the level.
     */
    String levelName();
    /**
     * Returns a sprite with the background of the level.
     * @return the sprite background of the level.
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the blocks that make up this level.
     */
    List<Block> blocks();
    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
    /**
     * Getter for the paddle.
     * @return the paddle.
     */
    Paddle getPaddle();
}
