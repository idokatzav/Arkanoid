package GameUpdating;
import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import MovingAndMechanics.Counter;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    public static final int SCORE_BOX_X = 300;
    public static final int SCORE_BOX_Y = GameLevel.TEXT_BOX - 4;
    private Counter scoreCount;
    /**
     * Constructor.
     * @param scoreCount counter.
     */
    public ScoreIndicator(Counter scoreCount) {
        this.scoreCount = scoreCount;
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void timePassed() {
        //nothing.
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(SCORE_BOX_X, SCORE_BOX_Y, "Score: " + scoreCount.getValue(),
                GameLevel.TEXT_SIZE);
    }
}
