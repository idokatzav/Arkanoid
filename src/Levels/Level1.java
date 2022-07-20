package Levels;

import CollidableAndSpriteObjects.Block;
import CollidableAndSpriteObjects.Paddle;
import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import GeometricPrimitives.Circle;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.Velocity;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level 1 Class.
 */
public class Level1 implements LevelInformation {
    private static final int PADDLE_WIDTH = 86;
    private static final int PADDLE_HEIGHT = 17;
    private static final int PADDLE_LEFT_UPPER_X_START = (GameLevel.WIDTH - PADDLE_WIDTH) / 2;
    private static final int PADDLE_LEFT_UPPER_Y_START = 565;
    private static final int BLOCK_SIZE = 29;
    private static final int MIDDLE_Y = 163;
    private static final int MIDDLE_X = 400;
    private static final int BASIC_CIRCLE_RADIUS = 60;
    private static final int CHANGE_BETWEEN_CIRCLES_IN_RADIUS = 30;
    private static final int DISTANCE_FROM_CENTER_OF_BASE = 6 + (BLOCK_SIZE + 1) / 2;
    private static final int LINE_LENGTH = 120;
    private static final int X_START = 387;
    private static final int Y_START = 152;
    private static final int PADDLE_SPEED = GameLevel.PADDLE_VELOCITY;
    private static final String NAME = "Direct Hit";
    private static final int NUMBER_OF_BALLS = 1;
    private static final int NUMBER_OF_BLOCKS = 1;
    private Paddle paddle;
    private Background background;
    private KeyboardSensor keyboardSensor;
    private List<Block> lstBlock;
    private List<Velocity> lstVelocities;
    /**
     * Constructor.
     * @param keyboardSensor the keyboard.
     */
    public Level1(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.background = new Background();
        Rectangle rect1 = new Rectangle(new Point(GameLevel.SIDES_SMALL, GameLevel.SIDES_SMALL + GameLevel.TEXT_BOX),
                GameLevel.WIDTH - GameLevel.SIDES_SMALL, GameLevel.HEIGHT - GameLevel.SIDES_SMALL - GameLevel.TEXT_BOX);
        rect1.setIsFilled(true);
        rect1.setColor(Color.BLACK);
        rect1.setColor2(Color.BLACK);
        this.background.addBaseGeometry(rect1);
        Line l = new Line(MIDDLE_X - DISTANCE_FROM_CENTER_OF_BASE, MIDDLE_Y,
                MIDDLE_X - DISTANCE_FROM_CENTER_OF_BASE - LINE_LENGTH, MIDDLE_Y);
        l.setColor(Color.BLUE);
        this.background.addBaseGeometry(l);
        l = new Line(MIDDLE_X + DISTANCE_FROM_CENTER_OF_BASE, MIDDLE_Y,
                MIDDLE_X + DISTANCE_FROM_CENTER_OF_BASE + LINE_LENGTH, MIDDLE_Y);
        l.setColor(Color.BLUE);
        this.background.addBaseGeometry(l);
        l = new Line(MIDDLE_X, MIDDLE_Y - DISTANCE_FROM_CENTER_OF_BASE,
                MIDDLE_X, MIDDLE_Y - DISTANCE_FROM_CENTER_OF_BASE - LINE_LENGTH);
        l.setColor(Color.BLUE);
        this.background.addBaseGeometry(l);
        l = new Line(MIDDLE_X, MIDDLE_Y + DISTANCE_FROM_CENTER_OF_BASE,
                MIDDLE_X, MIDDLE_Y + DISTANCE_FROM_CENTER_OF_BASE + LINE_LENGTH);
        l.setColor(Color.BLUE);
        this.background.addBaseGeometry(l);
        for (int i = 0; i < 3; i++) {
            Circle c = new Circle(MIDDLE_X, MIDDLE_Y, BASIC_CIRCLE_RADIUS + i * (CHANGE_BETWEEN_CIRCLES_IN_RADIUS),
                    Color.BLUE, false);
            this.background.addBaseGeometry(c);
        }
        Rectangle rect3 = new Rectangle(new Point(PADDLE_LEFT_UPPER_X_START, PADDLE_LEFT_UPPER_Y_START), PADDLE_WIDTH,
                PADDLE_HEIGHT);
        this.paddle = new Paddle(this.keyboardSensor, new Color(255, 200, 0), rect3, GameLevel.SIDES_SMALL,
                GameLevel.WIDTH - GameLevel.SIDES_SMALL - PADDLE_WIDTH, PADDLE_SPEED);
        Rectangle rect = new Rectangle(new Point(MIDDLE_X - BLOCK_SIZE / 2, MIDDLE_Y - BLOCK_SIZE / 2),
                BLOCK_SIZE, BLOCK_SIZE);
        Block b = new Block(rect, Color.RED);
        this.lstBlock = new ArrayList<Block>();
        this.lstBlock.add(b);
        this.lstVelocities = new ArrayList<Velocity>();
        Velocity v = new Velocity(0, -GameLevel.BALL_VELOCITY);
        this.lstVelocities.add(v);
    }
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

    @Override
    public int paddleSpeed() {
        return GameLevel.PADDLE_VELOCITY;
    }

    @Override
    public int paddleWidth() {
        return this.paddle.getWidth();
    }

    @Override
    public List<Block> blocks() {
        return this.lstBlock;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.lstVelocities;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public String levelName() {
        return NAME;
    }

    @Override
    public Paddle getPaddle() {
        return this.paddle;
    }

    @Override
    public int getLevelNum() {
        return 1;
    }
}
