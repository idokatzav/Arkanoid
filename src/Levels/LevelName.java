package Levels;

import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import GameUpdating.ScoreIndicator;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Level Name Class: the object of the text of the level name.
 */
public class LevelName implements Sprite {
    public static final int LEVEL_NAME_START = 500;
    public static final String BASE_STR = "Level Name: ";
    private String levelName;
    /**
     * Constructor.
     * @param levelName the name of the level.
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    /**
     * Setter for level name.
     * @param levelName the name of the level.
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(LEVEL_NAME_START, ScoreIndicator.SCORE_BOX_Y, BASE_STR + levelName,
                GameLevel.TEXT_SIZE);
    }

    @Override
    public void timePassed() {
        //nothing
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
