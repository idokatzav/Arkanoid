package CollidableAndSpriteObjects;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * CollidableAndSpriteObjects.SpriteCollection class. Let us to manage a sprite collection that will be
 * used to run the game.
 * Methods: Constructor.
 *          addSprite - add sprite to the collection.
 *          notifyAllTimePassed - notify all the sprites that time passed and run timePassed method on them.
 *          drawAllOn - draws all of the sprites.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spriteList;
    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }
    /**
     * Add CollidableAndSpriteObjects.Sprite to the list.
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(this.spriteList.size(), s);
    }
    /**
     * Removes a given sprite from game objects.
     * @param s the object to remove.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * Removes all the sprites.
     */
    public void removeAllSprites() {
        List<Sprite> lst = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s: lst) {
            this.spriteList.remove(s);
        }
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList1 = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s: spriteList1) {
            s.timePassed();
        }
    }
    /**
     *  call drawOn(d) on all sprites.
     * @param d the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: this.spriteList) {
            s.drawOn(d);
        }
    }
}