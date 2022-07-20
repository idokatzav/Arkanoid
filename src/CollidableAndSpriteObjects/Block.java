package CollidableAndSpriteObjects;
import GameMain.GameLevel;
import GeometricPrimitives.Ball;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.HitListener;
import MovingAndMechanics.HitNotifier;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * class CollidableAndSpriteObjects.Block. Let us save information of blocks - one of the bases of the game.
 * Methods: Constructors.
 *          addToGame - add the block to a given game.
 *          drawOn - draws the block on a given drawSurface.
 *          timePassed - update the information when time passed.
 *          hit - updates a given velocity after Collision with that object.
 *          Getters and Setters.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private boolean toDrawPrimeter;
    private Rectangle rect;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    /**
     * Constructor for block.
     * @param rect the block's rectangle.
     * @param color the color.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = new Rectangle(rect);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.toDrawPrimeter = true;
    }
    /**
     * Constructor for block.
     * @param rect the block's rectangle.
     */
    public Block(Rectangle rect) {
        this.rect = new Rectangle(rect);
        this.color = new Color(0, 0, 0);
        this.hitListeners = new ArrayList<HitListener>();
        this.toDrawPrimeter = true;
    }
    /**
     * Constructor for CollidableAndSpriteObjects.Block without giving the rectangle.
     */
    public Block() {
        this.rect = new Rectangle(new Point(0, 0), 0, 0);
        this.color = new Color(0, 0, 0);
        this.hitListeners = new ArrayList<HitListener>();
        this.toDrawPrimeter = true;
    }
    /**
     * Constructor for CollidableAndSpriteObjects.Block.
     * @param color the new color.
     */
    public Block(java.awt.Color color) {
        this.rect = new Rectangle(new Point(0, 0), 0, 0);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.toDrawPrimeter = true;
    }

    /**
     * Setter for the drawing of the primeter.
     * @param toDrawPrimeter the new status.
     */
    public void setToDrawPrimeter(boolean toDrawPrimeter) {
        this.toDrawPrimeter = toDrawPrimeter;
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * Draw the CollidableAndSpriteObjects.Block to the screen.
     * @param d the draw surface.
     */
    public void drawOn(DrawSurface d) {
        Point p = this.rect.getUpperLeft();
        double height = this.rect.getHeight();
        double width = this.rect.getWidth();
        d.setColor(this.color);
        d.fillRectangle((int) (Math.round(p.getX())), (int) (Math.round(p.getY())), (int) (Math.round(width)),
                (int) (Math.round(height)));
        if (this.toDrawPrimeter) {
            d.setColor(Color.BLACK);
            d.drawRectangle((int) (Math.round(p.getX())), (int) (Math.round(p.getY())), (int) (Math.round(width)),
                    (int) (Math.round(height)));
        }
    }
    /**
     *  Notify the sprite that time has passed.
     */
    public void timePassed() {
        //to do.
    }
    //accessors.
    /**
     * Setter for this.rect.
     * @param rect the new rectangle.
     */
    public void setRect(Rectangle rect) {
        this.rect = new Rectangle(rect);
    }
    /**
     * Setter for this.rect.width.
     * @param width the new width.
     */
    public void setRectWidth(double width) {
        this.rect.setWidth(width);
    }
    /**
     * Setter for this.rect.height.
     * @param height the new height.
     */
    public void setRectHeight(double height) {
        this.rect.setHeight(height);
    }
    /**
     * Returns copy of this collision rectangle.
     * @return copy of this.rect.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rect);
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] sides = this.rect.getSides();
        Velocity v = new Velocity(currentVelocity);
        if (sides[0].isOnLine(collisionPoint) || sides[2].isOnLine(collisionPoint)) {
            v.setDy(-1 * v.getDy());
        }
        if (sides[1].isOnLine(collisionPoint) || sides[3].isOnLine(collisionPoint)) {
            v.setDx(-1 * v.getDx());
        }
        this.notifyHit(hitter);
        return v;
    }

    /**
     * Removes this object from the game.
     * @param game the game object.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * notify that the given ball hit this block.
     * @param hitter the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }
    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }
}
