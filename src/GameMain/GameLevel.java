package GameMain;

import CollidableAndSpriteObjects.Block;
import CollidableAndSpriteObjects.Collidable;
import CollidableAndSpriteObjects.GameEnvironment;
import CollidableAndSpriteObjects.Paddle;
import CollidableAndSpriteObjects.Sprite;
import CollidableAndSpriteObjects.SpriteCollection;
import GameUpdating.BallRemover;
import GameUpdating.BlockRemover;
import GameUpdating.CountdownAnimation;
import GameUpdating.LivesIndicator;
import GameUpdating.PauseScreen;
import GameUpdating.ScoreIndicator;
import GameUpdating.ScoreTrackingListener;
import GeometricPrimitives.Ball;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import Levels.LevelInformation;
import Levels.LevelName;
import MovingAndMechanics.Animation;
import MovingAndMechanics.AnimationRunner;
import MovingAndMechanics.Counter;
import MovingAndMechanics.HitListener;
import MovingAndMechanics.KeyPressStoppableAnimation;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.List;

/**
 * GameLevel Main.GameLevel class. Let us create and run a game.
 * Methods: Constructor.
 *          addCollidable - add CollidableAndSpriteObjects.Collidable to the game.
 *          addSprite - add CollidableAndSpriteObjects.Sprite to the game.
 *          initialize - creates the board, blocks, paddle and balls.
 *          run - runs the game.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private boolean win;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter countScore;
    private Paddle p;
    private KeyboardSensor keyboard;
    private LevelName levelName;
    private BlockRemover listeners;
    private HitListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private BallRemover bR;
    private int currentLevel;
    private LevelInformation lvl;
    private int index;
    private Counter countLives;
    private LivesIndicator livesIndicator;
    private boolean ballsCreatedFirst;
    public static final String NAME = "Arkanoid";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int TEXT_BOX = 18;
    public static final int SIDES_SMALL = 25;
    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 10;
    public static final int BALL_RADIUS = 4;
    public static final int BALL_VELOCITY = 7;
    public static final int PADDLE_VELOCITY = 10;
    public static final int ROUND_OF_SPECIAL_BLOCKS = 20;
    public static final int PARTS_OF_ROUND_OF_SPECIAL_BLOCKS = 4;
    public static final int ROUND_OF_SPECIAL_KILLING_BLOCKS = 10;
    public static final int TEXT_BLOCK_SIZE = 0;
    public static final int TEXT_SIZE = 14;
    public static final int WIN_OR_LOSE_SCREEN_IN_SECONDS = 16;
    public static final int FPS = 60;
    public static final int NUMBER_OF_SECONDS = 2;
    public static final int COUNT_FROM = 3;
    public static final int WIDTH_START_POINT_OF_PAUSE = WIDTH / 2 - 140;
    public static final int WIDTH_START_POINT_OF_PAUSE_DESCRIPTION = WIDTH / 2 - 240;
    public static final int HEIGHT_START_POINT_OF_PAUSE = HEIGHT / 2 + 80;
    public static final int PAUSE_DESCRIPTION_SIZE = 45;
    public static final int HEIGHT_OF_POINT_OF_PAUSE = HEIGHT / 2 - 80 + PAUSE_DESCRIPTION_SIZE;
    public static final int PAUSE_SIZE = 80;
    public static final Color[] TXT_COLORS = {Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK};
    public static final int NUM_OF_LIVES = 7;
    /**
     * Main.
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        Counter scoreCount = new Counter();
        GUI gui = new GUI(NAME, WIDTH, HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        LevelInformation[] levels = {new Level1(keyboardSensor), new Level2(keyboardSensor), new Level3(keyboardSensor),
                new Level4(keyboardSensor)};
        AnimationRunner animationRunner = new AnimationRunner(gui, FPS);
        Counter countLive = new Counter();
        countLive.increase(NUM_OF_LIVES);
        for (int i = 0; i < levels.length; i++) {
            GameLevel gameLevel = new GameLevel(levels[i], scoreCount, gui, keyboardSensor, animationRunner, countLive);
            gameLevel.initialize();
            gameLevel.run();
        }
        gui.close();
    }
    /**
     * Constructor.
     * @param lvl the level.
     * @param countScore ScoreCounter.
     * @param gui the gui.
     * @param keyboardSensor the keyboard.
     * @param animationRunner the animationRunner.
     * @param countLives LiveCounter.
     */
    public GameLevel(LevelInformation lvl, Counter countScore, GUI gui, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter countLives) {
        this.index = lvl.getLevelNum();
        this.gui = gui;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.running = true;
        this.levelName = null;
        this.currentLevel = lvl.getLevelNum() - 1;
        this.lvl = lvl;
        this.keyboard = keyboardSensor;
        this.countScore = countScore;
        this.countLives = countLives;
        this.scoreIndicator = new ScoreIndicator(countScore);
        this.livesIndicator = new LivesIndicator(this.countLives);
        this.scoreTrackingListener = new ScoreTrackingListener(countScore);
        this.runner = animationRunner;
        this.win = false;
        this.ballsCreatedFirst = false;
    }
    /**
     * Checks if the level was won.
     * @return true if won, false otherwise.
     */
    private boolean isWin() {
        return this.isWin() && !this.running;
    }
    /**
     * Getter for the counter text color.
     * @return the color.
     */
    public Color getTextColor() {
        return TXT_COLORS[this.currentLevel];
    }
    /**
     * Returns a counter of the number of balls.
     * @return the counter.
     */
    public Counter getCountOfBalls() {
        return this.remainingBalls;
    }
    /**
     * Returns a counter of the number of blocks.
     * @return the counter.
     */
    public Counter getCountOfBlocks() {
        return this.remainingBlocks;
    }
    /**
     * Add CollidableAndSpriteObjects.Collidable.
     * @param c the new collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Add CollidableAndSpriteObjects.Sprite.
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and GeometricPrimitives.Ball (and CollidableAndSpriteObjects.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //create the gray sides of the board.
        this.remainingBlocks = new Counter();
        this.listeners = new BlockRemover(this, this.remainingBlocks);
        this.remainingBalls = new Counter();
        this.bR = new BallRemover(this, this.remainingBalls);
        this.runner = new AnimationRunner(this.gui, FPS);
        this.running = true;
        this.p = this.lvl.getPaddle();
        this.levelName = new LevelName(lvl.levelName());
        (lvl.getBackground()).addToGame(this);
        Block b1 = new Block(new GeometricPrimitives.Rectangle(new Point(0, HEIGHT + SIDES_SMALL),
                WIDTH, SIDES_SMALL), Color.GRAY);
        b1.setToDrawPrimeter(false);
        b1.addHitListener(this.bR);
        b1.addToGame(this);
        b1 = new Block(new GeometricPrimitives.Rectangle(new Point(WIDTH - SIDES_SMALL, TEXT_BOX),
                SIDES_SMALL, HEIGHT + SIDES_SMALL), Color.GRAY);
        b1.setToDrawPrimeter(false);
        b1.addToGame(this);
        b1 = new Block(new GeometricPrimitives.Rectangle(new Point(0, TEXT_BOX), SIDES_SMALL,
                HEIGHT + SIDES_SMALL), Color.GRAY);
        b1.setToDrawPrimeter(false);
        b1.addToGame(this);
        b1 = new Block(new Rectangle(new Point(0, TEXT_BOX), WIDTH, SIDES_SMALL), Color.GRAY);
        b1.setToDrawPrimeter(false);
        b1.addToGame(this);
        List<Block> lstBlock = lvl.blocks();
        for (Block b: lstBlock) {
            b.addHitListener(this.listeners);
            b.addHitListener(this.scoreTrackingListener);
            b.addToGame(this);
        }
        this.p.addToGame(this);
        this.remainingBlocks.decrease(this.remainingBlocks.getValue());
        this.remainingBlocks.increase(lvl.numberOfBlocksToRemove());
        (this.levelName).addToGame(this);
        (this.livesIndicator).addToGame(this);
        (this.scoreIndicator).addToGame(this);
    }
    /**
     * creates balls on the top of the paddle.
     * @param numberOfBalls the number of balls to create.
     * @param ballVelocities the velocities of the balls.
     */
    public void createBallsOnTopOfPaddle(int numberOfBalls, List<Velocity> ballVelocities) {
        //create the balls.
        this.running = true;
        this.remainingBalls.decrease(this.remainingBalls.getValue());
        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball;
            if (this.ballsCreatedFirst) {
                ball = new Ball(new Point(this.p.getCollisionRectangle().getUpperLeft().getX()
                        + this.p.getWidth() / 2, (int) (Math.round(this.p.getCollisionRectangle().
                        getUpperLeft().getY())) - SIDES_SMALL), BALL_RADIUS, Color.WHITE);
            } else {
                ball = new Ball(new Point(GameLevel.WIDTH / 2,
                        (int) (Math.round(this.p.getCollisionRectangle().getUpperLeft().getY()))
                        - SIDES_SMALL), BALL_RADIUS, Color.WHITE);
            }
            ball.setPaddle(this.p);
            ball.setEnvironment(this.environment);
            ball.setXSize(WIDTH);
            ball.setYSize(HEIGHT);
            ball.setXMin(0);
            ball.setYMin(0);
            ball.setVelocity(ballVelocities.get(i));
            this.remainingBalls.increase(1);
            ball.addToGame(this);
        }
        this.ballsCreatedFirst = true;
    }
    /**
     * Returns the sprite collection.
     * @return this.spriteCollection.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(NUMBER_OF_SECONDS, COUNT_FROM, this.sprites,
                TXT_COLORS[this.index - 1]));
        this.runner.run(this);
        if (this.remainingBlocks.getValue() == 0) {
            this.countScore.increase(100);
        }
    }
    /**
     * Removes the given ball from the game.
     * @param b a ball.
     */
    public void removeBallFromGame(Ball b) {
        if (b != null) {
            this.removeSprite(b);
        }
    }
    /**
     * Removes a given collidable from game objects.
     * @param c the object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * Removes a given sprite from game objects.
     * @param s the object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
            this.win = true;
        }
        if (this.countLives.getValue() == 0 && this.remainingBalls.getValue() == 0) {
            this.running = false;
            this.win = false;
        } else if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen();
            KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard,
                    "space", pause);
            this.runner.run(keyPressStoppableAnimation);
            this.runner.run(new CountdownAnimation(NUMBER_OF_SECONDS, COUNT_FROM, this.sprites,
                    TXT_COLORS[this.currentLevel]));
        }
    }
}