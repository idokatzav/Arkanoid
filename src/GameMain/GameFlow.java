package GameMain;

import GameUpdating.GameOver;
import GameUpdating.YouWin;
import Levels.LevelInformation;
import MovingAndMechanics.Animation;
import MovingAndMechanics.AnimationRunner;
import MovingAndMechanics.Counter;
import MovingAndMechanics.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter countScore;
    private GUI gui;
    private boolean win;
    private Counter lives;
    /**
     * Constructor.
     * @param animationRunner the animation runner.
     * @param keyboardSensor the keyboard sensor.
     * @param gui the gui.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
        this.countScore = new Counter();
        this.win = win;
        this.lives = new Counter();
        this.lives.increase(GameLevel.NUM_OF_LIVES);
    }
    /**
     * runs a level list.
     * @param levels the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            this.win = false;
            GameLevel level = new GameLevel(levelInfo, this.countScore, this.gui,
                    this.keyboardSensor, this.animationRunner, this.lives);
            level.initialize();
            while (this.lives.getValue() > 0 && !this.win) {
                this.win = true;
                level.createBallsOnTopOfPaddle(levelInfo.numberOfBalls(), levelInfo.initialBallVelocities());
                Counter countBalls = level.getCountOfBalls();
                Counter countBlocks = level.getCountOfBlocks();
                while (countBalls.getValue() > 0 && countBlocks.getValue() > 0) {
                    level.run();
                }
                if (countBalls.getValue() == 0 && countBlocks.getValue() > 0) {
                    this.lives.decrease(1);
                    if (this.lives.getValue() == 0) {
                        this.win = false;
                        Animation gameOver = new GameOver(this.countScore);
                        KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(
                                this.keyboardSensor, KeyboardSensor.SPACE_KEY, gameOver);
                        this.animationRunner.run(keyPressStoppableAnimation);
                        break;
                    } else {
                        this.win = false;
                    }
                }
            }
        }
        if (this.win) {
            Animation win = new YouWin(this.countScore);
            KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, win);
            this.animationRunner.run(keyPressStoppableAnimation);
        }
        this.gui.close();
    }
}

