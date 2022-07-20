package Levels;

import CollidableAndSpriteObjects.Block;
import CollidableAndSpriteObjects.Paddle;
import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import GeometricPrimitives.Circle;
import MovingAndMechanics.Velocity;
import GeometricPrimitives.Rectangle;
import GeometricPrimitives.Point;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Level 3.
 */
public class Level3 implements LevelInformation {
    private static final int BUILDING_START_Y = 450;
    private static final int BUILDING_START_X = 40 + GameLevel.SIDES_SMALL;
    private static final int BUILDING_WIDTH = 100;
    private static final int BUILDING_HEIGHT = 150;
    private static final int NUM_OF_WINDOWS_IN_ROW = 5;
    private static final int NUM_OF_WINDOWS_ROWS = 5;
    private static final int FIRST_WINDOW_Y = 460;
    private static final int FIRST_WINDOW_X = 50 + GameLevel.SIDES_SMALL;
    private static final int BETWEEN_WINDOWS_IN_ROW = 8;
    private static final int BETWEEN_WINDOWS_ROWS = 7;
    private static final int FIRST_PART_OF_THE_ANTENNA_X = BUILDING_START_X + 35;
    private static final int FIRST_PART_OF_THE_ANTENNA_Y = 400;
    private static final int FIRST_PART_OF_THE_ANTENNA_WIDTH = 30;
    private static final int FIRST_PART_OF_THE_ANTENNA_HEIGHT = 200;
    private static final int TOP_OF_THE_ANTENNA_Y = 200;
    private static final int TOP_OF_THE_ANTENNA_X = BUILDING_START_X + 45;
    private static final int TOP_OF_THE_ANTENNA_WIDTH = 10;
    private static final int TOP_OF_THE_ANTENNA_HEIGHT = 400;
    private static final int NUM_OF_CIRCLES_ON_THE_ANTENNA = 3;
    private static final int INNER_CIRCLE_RADIUS_ON_ANTENNA = 4;
    private static final int DIFFERENCES_BETWEEN_CIRCLES_ON_ANTENNA = 4;
    private static final Color[] ANTENNA_CIRCLES_COLORS = {new Color(255, 255, 255), new Color(246, 77, 54),
            new Color(216, 172, 102)};
    private static final Color[] BUILDING_COLORS = {new Color(46, 42, 41), new Color(62, 58, 57),
            new Color(78, 74, 73)};
    private static final int WINDOW_HEIGHT = 25;
    private static final int WINDOW_WIDTH = 10;
    private static final int CIRCLES_ON_TOP_ANTENNA_X = 115;
    private static final int CIRCLES_ON_TOP_ANTENNA_Y = 200;
    private static final int NUMBER_OF_BLOCKS_ROWS = 5;
    private static final int NUMBER_OF_BLOCKS_IN_THE_FIRST_ROW = 10;
    private static final Color[] BLOCKS_COLORS = {new Color(128, 128, 128), new Color(255, 0, 0),
            new Color(255, 255, 0), new Color(0, 0, 255), new Color(255, 255, 255)};
    private static final int START_OF_THE_FIRST_BLOCK_X = 250 + GameLevel.SIDES_SMALL;
    private static final int START_OF_THE_FIRST_BLOCK_Y = 150;
    private static final int BLOCK_HEIGHT_WITHOUT_DOWN = 25;
    private static final int BLOCK_HEIGHT = 26;
    private static final int BLOCK_WIDTH_WITHOUT_DOWN = 50;
    private static final int BLOCK_WIDTH = 51;
    private static final int[] ANGLES = {315, 45};
    private static final int NUMBER_OF_BALLS = 2;
    private static final int NUMBER_OF_BLOCKS = 40;
    private static final int PADDLE_WIDTH = 86;
    private static final int PADDLE_HEIGHT = 25;
    private static final String LEVEL_NAME = "Green 3";
    private static final int PADDLE_LEFT_UPPER_X_START = GameLevel.WIDTH / 2 - PADDLE_WIDTH / 2;
    private static final int PADDLE_LEFT_UPPER_Y_START = 565;
    private static final int PADDLE_SPEED = GameLevel.PADDLE_VELOCITY;
    private Paddle paddle;
    private Background background;
    private KeyboardSensor keyboardSensor;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    /**
     * Constructor.
     * @param keyboardSensor the keyboard sensor.
     */
    public Level3(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.background = new Background();
        this.blockList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
        Rectangle rect = new Rectangle(new Point(0, GameLevel.SIDES_SMALL), GameLevel.WIDTH, GameLevel.HEIGHT);
        rect.setIsFilled(true);
        rect.setColor(new Color(42, 130, 21));
        rect.setColor2(new Color(42, 130, 21));
        this.background.addBaseGeometry(rect);
        rect = new Rectangle(new Point(TOP_OF_THE_ANTENNA_X, TOP_OF_THE_ANTENNA_Y), TOP_OF_THE_ANTENNA_WIDTH,
                TOP_OF_THE_ANTENNA_HEIGHT);
        rect.setIsFilled(true);
        rect.setColor(BUILDING_COLORS[2]);
        rect.setColor2(BUILDING_COLORS[2]);
        this.background.addBaseGeometry(rect);
        rect = new Rectangle(new Point(FIRST_PART_OF_THE_ANTENNA_X, FIRST_PART_OF_THE_ANTENNA_Y),
                FIRST_PART_OF_THE_ANTENNA_WIDTH, FIRST_PART_OF_THE_ANTENNA_HEIGHT);
        rect.setIsFilled(true);
        rect.setColor(BUILDING_COLORS[1]);
        rect.setColor2(BUILDING_COLORS[1]);
        this.background.addBaseGeometry(rect);
        rect = new Rectangle(new Point(BUILDING_START_X, BUILDING_START_Y), BUILDING_WIDTH, BUILDING_HEIGHT);
        rect.setIsFilled(true);
        rect.setColor(BUILDING_COLORS[0]);
        rect.setColor2(BUILDING_COLORS[0]);
        this.background.addBaseGeometry(rect);
        for (int i = 0; i < NUM_OF_WINDOWS_ROWS; i++) {
            for (int j = 0; j < NUM_OF_WINDOWS_IN_ROW; j++) {
                rect = new Rectangle(new Point(FIRST_WINDOW_X + i * (WINDOW_WIDTH + BETWEEN_WINDOWS_ROWS),
                        FIRST_WINDOW_Y + j * (WINDOW_HEIGHT + BETWEEN_WINDOWS_IN_ROW)), WINDOW_WIDTH, WINDOW_HEIGHT);
                rect.setIsFilled(true);
                rect.setColor(Color.WHITE);
                rect.setColor2(Color.WHITE);
                this.background.addBaseGeometry(rect);
            }
        }
        for (int i = NUM_OF_CIRCLES_ON_THE_ANTENNA - 1; i >= 0; i--) {
            Circle c = new Circle(CIRCLES_ON_TOP_ANTENNA_X, CIRCLES_ON_TOP_ANTENNA_Y,
                    INNER_CIRCLE_RADIUS_ON_ANTENNA + i * DIFFERENCES_BETWEEN_CIRCLES_ON_ANTENNA,
                    ANTENNA_CIRCLES_COLORS[i], true);
            this.background.addBaseGeometry(c);
        }
        for (int i = 0; i < NUMBER_OF_BLOCKS_ROWS; i++) {
            for (int j = i; j < NUMBER_OF_BLOCKS_IN_THE_FIRST_ROW; j++) {
                rect = new Rectangle(new Point(START_OF_THE_FIRST_BLOCK_X + j * BLOCK_WIDTH_WITHOUT_DOWN,
                        START_OF_THE_FIRST_BLOCK_Y + i * BLOCK_HEIGHT_WITHOUT_DOWN), BLOCK_WIDTH_WITHOUT_DOWN,
                        BLOCK_HEIGHT_WITHOUT_DOWN);
                Block b = new Block(rect, BLOCKS_COLORS[i]);
                this.blockList.add(this.blockList.size(), b);
            }
        }
        for (int i = 0; i < ANGLES.length; i++) {
            this.velocityList.add(this.velocityList.size(), Velocity.fromAngleAndSpeed(ANGLES[i],
                    GameLevel.BALL_VELOCITY));
        }
        Rectangle rect3 = new Rectangle(new Point(PADDLE_LEFT_UPPER_X_START, PADDLE_LEFT_UPPER_Y_START), PADDLE_WIDTH,
                PADDLE_HEIGHT);
        this.paddle = new Paddle(this.keyboardSensor, new Color(255, 200, 0), rect3, GameLevel.SIDES_SMALL,
                GameLevel.WIDTH - GameLevel.SIDES_SMALL - PADDLE_WIDTH, PADDLE_SPEED);
    }
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return GameLevel.PADDLE_VELOCITY;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

    @Override
    public Paddle getPaddle() {
        return this.paddle;
    }

    @Override
    public int getLevelNum() {
        return 3;
    }
}
