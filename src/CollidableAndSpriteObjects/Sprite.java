package CollidableAndSpriteObjects;
import GameMain.GameLevel;
import biuoop.DrawSurface;
/**
 * CollidableAndSpriteObjects.Sprite Interface. Interface that is the base of CollidableAndSpriteObjects.Sprite objects.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d the draw surface.
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * Add this to game.
     * @param g the game
     */
    void addToGame(GameLevel g);
}
