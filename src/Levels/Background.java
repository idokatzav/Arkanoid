package Levels;

import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import GeometricPrimitives.BaseGeometry;
import GeometricPrimitives.BaseGeometryCollection;
import biuoop.DrawSurface;

/**
 * A Background class. Contains the objects make the background.
 */
public class Background implements Sprite {
    private BaseGeometryCollection baseGeometriesInThisBackground;
    /**
     * Constructor.
     */
    public Background() {
        this.baseGeometriesInThisBackground = new BaseGeometryCollection();
    }
    /**
     * Add base geometry to the background.
     * @param b the base geometry to add.
     */
    public void addBaseGeometry(BaseGeometry b) {
        this.baseGeometriesInThisBackground.addBaseGeometry(b);
    }
    @Override
    public void timePassed() {
        //nothing.
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.baseGeometriesInThisBackground.drawAllOn(d);
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
