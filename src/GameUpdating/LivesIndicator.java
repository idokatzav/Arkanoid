package GameUpdating;

import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import MovingAndMechanics.Counter;
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Lives indicator.
 */
public class LivesIndicator implements Sprite {
    public static final int SCORE_BOX_X = 100;
    public static final int SCORE_BOX_Y = GameLevel.TEXT_BOX - 4;
    private Counter countLives;
    /**
     * Constructor.
     * @param countLives a live counter.
     */
    public LivesIndicator(Counter countLives) {
        this.countLives = countLives;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(SCORE_BOX_X, SCORE_BOX_Y, "Lives: " + countLives.getValue(), GameLevel.TEXT_SIZE);
    }

    @Override
    public void timePassed() {
        //nothing.
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
