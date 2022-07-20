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
 * Level 2.
 */
public class Level2 implements LevelInformation {
    private static final int SUN_CENTER_X = 151;
    private static final int SUN_CENTER_Y = 150;
    private static final int SUN_BASE_RADIUS = 40;
    private static final int PADDLE_WIDTH = GameLevel.WIDTH * 3 / 4;
    private static final int PADDLE_LEFT_UPPER_X_START = GameLevel.WIDTH / 2 - PADDLE_WIDTH / 2;
    private static final int PADDLE_LEFT_UPPER_Y_START = 565;
    private static final int PADDLE_HEIGHT = 18;
    private static final int HEIGHT_OF_BLOCK = 26;
    private static final int X_FIRST_BLOCK = 24;
    private static final int DIFFERENCES_BETWEEN_SUN_PARTS_RADIUSES = 10;
    private static final int X_START = 387;
    private static final int Y_START = 152;
    private static final int PADDLE_SPEED = GameLevel.PADDLE_VELOCITY;
    private static final String NAME = "Wide Easy";
    private static final int NUMBER_OF_BALLS = 10;
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final int NUMBER_OF_SUN_RAYS = 100;
    private static final int DIFFERENCE_BETWEEN_ENDS_OF_SUN_RAYS = 7;
    private static final int X_END_OF_LEFTEST_SUN_RAYS = 9;
    private static final int Y_END_OF_SUN_RAYS = 250;
    private static final int DIFFERENCE_BETWEEN_BLOCKS = (GameLevel.WIDTH
            - 2 * GameLevel.SIDES_SMALL + 2) / NUMBER_OF_BLOCKS;
    private static final int WIDTH_OF_BLOCK = DIFFERENCE_BETWEEN_BLOCKS + 1;
    private static final int ANGLE_OF_START = 310;
    private static final int ANGLES_STEPS = 10;
    private Paddle paddle;
    private Background background;
    private KeyboardSensor keyboardSensor;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    /**
     * Constructor.
     * @param keyboardSensor the keyboard.
     */
    public Level2(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.background = new Background();
        Rectangle rect1 = new Rectangle(new Point(GameLevel.SIDES_SMALL, GameLevel.SIDES_SMALL + GameLevel.TEXT_BOX),
                GameLevel.WIDTH - GameLevel.SIDES_SMALL, GameLevel.HEIGHT - GameLevel.SIDES_SMALL - GameLevel.TEXT_BOX);
        rect1.setIsFilled(true);
        rect1.setColor(Color.WHITE);
        rect1.setColor2(Color.WHITE);
        this.background.addBaseGeometry(rect1);
        Color[] colors = new Color[3];
        colors[0] = new Color(255, 225, 24);
        colors[1] = new Color(236, 215, 73);
        colors[2] = new Color(239, 231, 176);
        for (int i = 0; i < NUMBER_OF_SUN_RAYS; i++) {
            Line l = new Line(new Point(SUN_CENTER_X, SUN_CENTER_Y),
                    new Point(X_END_OF_LEFTEST_SUN_RAYS + i * DIFFERENCE_BETWEEN_ENDS_OF_SUN_RAYS, Y_END_OF_SUN_RAYS));
            l.setColor(colors[2]);
            this.background.addBaseGeometry(l);
        }
        for (int i = 2; i >= 0; i--) {
            Circle c = new Circle(SUN_CENTER_X, SUN_CENTER_Y,
                    SUN_BASE_RADIUS + i * DIFFERENCES_BETWEEN_SUN_PARTS_RADIUSES, colors[i], true);
            this.background.addBaseGeometry(c);
        }
        Rectangle rect3 = new Rectangle(new Point(PADDLE_LEFT_UPPER_X_START, PADDLE_LEFT_UPPER_Y_START), PADDLE_WIDTH,
                PADDLE_HEIGHT);
        this.paddle = new Paddle(this.keyboardSensor, new Color(255, 200, 0), rect3, GameLevel.SIDES_SMALL,
                GameLevel.WIDTH - GameLevel.SIDES_SMALL - PADDLE_WIDTH, PADDLE_SPEED);
        this.blockList = new ArrayList<Block>();
        Color[] colors1 = new Color[15];
        colors1[0] = new Color(255, 0, 0);
        colors1[1] = colors1[0];
        colors1[2] = new Color(255, 200, 0);
        colors1[3] = colors1[2];
        colors1[4] = new Color(255, 255, 0);
        colors1[5] = colors1[4];
        colors1[6] = new Color(0, 255, 0);
        colors1[7] = colors1[6];
        colors1[8] = colors1[6];
        colors1[9] = new Color(0, 0, 255);
        colors1[10] = colors1[9];
        colors1[11] = new Color(255, 175, 175);
        colors1[12] = colors1[11];
        colors1[13] = new Color(0, 255, 255);
        colors1[14] = colors1[13];
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(X_FIRST_BLOCK + i * DIFFERENCE_BETWEEN_BLOCKS,
                    Y_END_OF_SUN_RAYS), WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK);
            Block b = new Block(rect, colors1[i]);
            b.setToDrawPrimeter(true);
            this.blockList.add(this.blockList.size(), b);
        }
        this.velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                this.velocityList.add(this.velocityList.size(), Velocity.fromAngleAndSpeed(ANGLE_OF_START + i
                                * ANGLES_STEPS, GameLevel.BALL_VELOCITY));
            } else {
                this.velocityList.add(this.velocityList.size(), Velocity.fromAngleAndSpeed(ANGLE_OF_START
                                + (i + 1) * ANGLES_STEPS, GameLevel.BALL_VELOCITY));
            }
        }
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
    public java.util.List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {

        return this.velocityList;
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
        return 2;
    }
}
