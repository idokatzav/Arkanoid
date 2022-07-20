package GameUpdating;

import GameMain.GameLevel;
import MovingAndMechanics.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Pause Screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;
    public static final String PAUSE = "Paused";
    public static final String PAUSE_MSG = "press space to continue";
    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH_START_POINT_OF_PAUSE, GameLevel.HEIGHT_OF_POINT_OF_PAUSE, PAUSE,
                GameLevel.PAUSE_SIZE);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH_START_POINT_OF_PAUSE_DESCRIPTION, GameLevel.HEIGHT_START_POINT_OF_PAUSE, PAUSE_MSG,
                GameLevel.PAUSE_DESCRIPTION_SIZE);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
