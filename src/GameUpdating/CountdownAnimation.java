package GameUpdating;

import CollidableAndSpriteObjects.SpriteCollection;
import GameMain.GameLevel;
import MovingAndMechanics.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int currentCounterValue;
    private int framesPassedThatPart;
    private SpriteCollection gameScreen;
    private Color textColor;
    public static final int END = -1;
    public static final int HEIGHT_OF_COUNTER = 2 * GameLevel.HEIGHT / 3;
    public static final int WIDTH_OF_COUNTER = GameLevel.SIDES_SMALL * 4;
    public static final String STOP_MSG = "...";
    public static final String GO_MSG = "GO";
    public static final int STOP_MSG_SIZE = 100;
    /**
     * Constructor.
     * @param numOfSeconds the number of seconds the animation will work.
     * @param countFrom the point to start count from.
     * @param gameScreen the list of sprites.
     * @param textColor the color of the text.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color textColor) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.currentCounterValue = countFrom;
        this.framesPassedThatPart = 0;
        this.gameScreen = gameScreen;
        this.textColor = textColor;
    }
    /**
     * Setter for text color.
     * @param color the new Color.
     */
    public void setTextColor(Color color) {
        this.textColor = color;
    }
    @Override
    public boolean shouldStop() {
        return this.currentCounterValue == END;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, GameLevel.TEXT_BOX, GameLevel.WIDTH, GameLevel.HEIGHT - GameLevel.TEXT_BOX);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, GameLevel.TEXT_BOX, GameLevel.WIDTH, GameLevel.HEIGHT - GameLevel.TEXT_BOX);
        this.gameScreen.drawAllOn(d);
        //draw the currently number of seconds.
        String msgToPrint;
        if (currentCounterValue > 0) {
            msgToPrint = currentCounterValue + STOP_MSG;
        } else {
            msgToPrint = GO_MSG;
        }
        d.setColor(this.textColor);
        d.drawText(WIDTH_OF_COUNTER, HEIGHT_OF_COUNTER, msgToPrint, STOP_MSG_SIZE);
        //calc the num of frames we reached until now and update it.
        double framesToReach = GameLevel.FPS * numOfSeconds;
        if (((int) ((framesToReach) / countFrom)) == framesPassedThatPart) {
            this.currentCounterValue--;
            this.framesPassedThatPart = 1;
        } else {
            this.framesPassedThatPart++;
        }
    }
}
