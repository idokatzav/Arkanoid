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
 * Level 4.
 */
public class Level4 implements LevelInformation {
    private static final Point[] CLOUDS_CENTERS = {new Point(100, 400), new Point(120, 420),
            new Point(140, 390), new Point(160, 420), new Point(180, 400), new Point(600, 500),
            new Point(620, 540), new Point(640, 510), new Point(680, 520), new Point(660, 530)};
    private static final int[] RADUISES = {24, 27, 30, 23, 33, 24, 28, 30, 32, 22};
    private static final Color[] CLOUDS_COLOR = {new Color(204, 204, 204), new Color(204, 204, 204),
            new Color(187, 187, 187), new Color(170, 170, 170), new Color(170, 170, 170),
            new Color(204, 204, 204), new Color(204, 204, 204), new Color(187, 187, 187),
            new Color(170, 170, 170), new Color(170, 170, 170)};
    private static final Line[] RAIN = {new Line(100, 439, 80, 600), new Line(109, 445, 90, 600),
            new Line(119, 447, 100, 600), new Line(129, 446, 110, 600), new Line(140, 439, 120, 600),
            new Line(150, 440, 130, 600), new Line(160, 442, 140, 600), new Line(170, 440, 151, 600),
            new Line(181, 432, 160, 600), new Line(191, 431, 170, 600),
            new Line(595, 551, 580, 600), new Line(602, 561, 590, 600),
            new Line(611, 564, 600, 600), new Line(620, 567, 610, 600), new Line(631, 565, 620, 600),
            new Line(646, 548, 630, 600), new Line(655, 552, 640, 600), new Line(665, 552, 650, 600),
            new Line(675, 552, 660, 600), new Line(685, 552, 670, 600)};
    private static final int X_START_BLOCKS = 25;
    private static final int Y_START_BLOCKS = 100;
    private static final int WIDTH_BLOCK = 50;
    private static final int HEIGTH_BLOCK = 25;
    private static final int NUMBER_OF_ROW_BLOCK = 7;
    private static final int NUMBER_OF_BLOCKS_IN_ROW = 15;
    private static final int NUM_OF_BALLS = 3;
    private static final int PADDLE_SPEED = GameLevel.PADDLE_VELOCITY;
    private static final int NUMBER_OF_BLOCKS = NUMBER_OF_BLOCKS_IN_ROW * NUMBER_OF_ROW_BLOCK;
    private static final Color[] BLOCKS_COLOR = {new Color(128, 128, 128), new Color(255, 0, 0), new Color(255, 255, 0),
            new Color(0, 255, 0), new Color(255, 255, 255), new Color(255, 175, 175), new Color(0, 255, 255)};
    private static final int[] ANGLES = {315, 0, 45};
    private static final int PADDLE_WIDTH = 86;
    private static final int PADDLE_HEIGHT = 25;
    private static final String LEVEL_NAME = "Final Four";
    private static final int PADDLE_LEFT_UPPER_X_START = GameLevel.WIDTH / 2 - PADDLE_WIDTH / 2;
    private static final int PADDLE_LEFT_UPPER_Y_START = 565;
    private Paddle paddle;
    private Background background;
    private KeyboardSensor keyboardSensor;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    /**
     * Constructor.
     * @param keyboardSensor the keyboard sensor.
     */
    public Level4(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.blockList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
        this.background = new Background();
        Rectangle rectangle1 = new Rectangle(new Point(0, GameLevel.SIDES_SMALL),
                GameLevel.WIDTH, GameLevel.HEIGHT);
        rectangle1.setIsFilled(true);
        rectangle1.setColor(new Color(23, 136, 208));
        rectangle1.setColor2(new Color(23, 136, 208));
        this.background.addBaseGeometry(rectangle1);
        for (int i = 0; i < RAIN.length; i++) {
            Line l = new Line((int) (Math.round(RAIN[i].start().getX())), (int) (Math.round(RAIN[i].start().getY())),
                    (int) (Math.round(RAIN[i].end().getX())), (int) (Math.round(RAIN[i].end().getY())));
            l.setColor(Color.WHITE);
            this.background.addBaseGeometry(l);
        }
        for (int i = 0; i < CLOUDS_CENTERS.length; i++) {
            Circle c = new Circle((int) (Math.round(CLOUDS_CENTERS[i].getX())),
                    (int) (Math.round(CLOUDS_CENTERS[i].getY())), RADUISES[i], CLOUDS_COLOR[i], true);
            this.background.addBaseGeometry(c);
        }
        for (int i = 0; i < NUMBER_OF_ROW_BLOCK; i++) {
            for (int j = 0; j < NUMBER_OF_BLOCKS_IN_ROW; j++) {
                Rectangle rect1 = new Rectangle(new Point(X_START_BLOCKS + j * WIDTH_BLOCK,
                        Y_START_BLOCKS + i * HEIGTH_BLOCK), WIDTH_BLOCK, HEIGTH_BLOCK);
                Block block = new Block(rect1, BLOCKS_COLOR[i]);
                this.blockList.add(this.blockList.size(), block);
            }
        }
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(ANGLES[i], GameLevel.BALL_VELOCITY);
            this.velocityList.add(this.velocityList.size(), v);
        }
        Rectangle rect3 = new Rectangle(new Point(PADDLE_LEFT_UPPER_X_START, PADDLE_LEFT_UPPER_Y_START), PADDLE_WIDTH,
                PADDLE_HEIGHT);
        this.paddle = new Paddle(this.keyboardSensor, new Color(255, 200, 0), rect3, GameLevel.SIDES_SMALL,
                GameLevel.WIDTH - GameLevel.SIDES_SMALL - PADDLE_WIDTH, PADDLE_SPEED);
    }
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
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
        return 4;
    }
}
