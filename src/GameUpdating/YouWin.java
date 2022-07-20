package GameUpdating;

import MovingAndMechanics.Animation;
import MovingAndMechanics.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * End Screen Class.
 */
public class YouWin implements Animation {
    private static final String END_SCREEN_DESC = "press space to close the game";
    private static final String WIN_MSG = "You Win! Your score is ";
    private Counter score;
    private KeyboardSensor keyboard;
    /**
     * Constructor.
     * @param counter the score counter.
     */
    public YouWin(Counter counter) {
        this.score = counter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String msg = WIN_MSG + this.score.getValue();
        d.setColor(Color.BLACK);
        d.drawText(60, 265, msg, 50);
        d.setColor(Color.BLACK);
        d.drawText(120, 380, END_SCREEN_DESC, 30);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
