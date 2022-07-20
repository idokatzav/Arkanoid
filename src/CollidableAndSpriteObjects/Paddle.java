package CollidableAndSpriteObjects;

import GameMain.GameLevel;
import GeometricPrimitives.Ball;
import GeometricPrimitives.Line;
import GeometricPrimitives.Point;
import GeometricPrimitives.Rectangle;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * CollidableAndSpriteObjects.Paddle Class. Let us create paddle object, that is needed for the game, with its own
 * logic that is different from other objects.
 * Methods: Constructor by gui, color, rectangle, maxX, minX, and speed
 *          moveLeft - moves the paddle to left.
 *          moveRight - moves the paddle to right.
 *          timePassed - checks if left arrow or right arrow were pressed, if pressed, update the paddle place.
 *          drawOn - draw the paddle.
 *          getCollisionRectangle - returns the rectangle of the paddle.
 *          hit - update the speed of a ball after hit with that object.
 *          addToGame - add the paddle to the given game.
 *          Getters And Setters for the class variables.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle rectangle;
    private int maxX;
    private int minX;
    private int speed;
    /**
     * Constructor for CollidableAndSpriteObjects.Paddle.
     * @param keyboardSensor the keyborad sensor.
     * @param color the color.
     * @param rect the rectangle.
     * @param maxX the maximum x for the upperLeft point of the paddle.
     * @param minX the minimum x for the upperLeft point of the paddle.
     * @param speed the speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboardSensor, Color color, Rectangle rect, int minX, int maxX, int speed) {
        this.keyboard = keyboardSensor;
        this.color = color;
        this.rectangle = rect;
        this.maxX = maxX;
        this.minX = minX;
        this.speed = speed;
    }
    /**
     * Moving the ball this.speed pixels to left.
     */
    public void moveLeft() {
        if (((int) (Math.round(this.rectangle.getUpperLeft().getX())) - this.speed) < this.minX) {
            Point p = this.rectangle.getUpperLeft();
            this.rectangle.setUpperLeft(new Point(minX, p.getY()));
        } else {
            Point p = this.rectangle.getUpperLeft();
            this.rectangle.setUpperLeft(new Point(p.getX() - this.speed, p.getY()));
        }
    }
    /**
     * Moving the ball this.speed pixels to right.
     */
    public void moveRight() {
        if (((int) (Math.round(this.rectangle.getUpperLeft().getX())) + this.speed) > this.maxX) {
            Point p = this.rectangle.getUpperLeft();
            this.rectangle.setUpperLeft(new Point(maxX, p.getY()));
        } else {
            Point p = this.rectangle.getUpperLeft();
            this.rectangle.setUpperLeft(new Point(p.getX() + this.speed, p.getY()));
        }
    }
    // CollidableAndSpriteObjects.Sprite
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Point p = this.rectangle.getUpperLeft();
        double height = this.rectangle.getHeight();
        double width = this.rectangle.getWidth();
        d.fillRectangle((int) (Math.round(p.getX())), (int) (Math.round(p.getY())), (int) (Math.round(width)),
                (int) (Math.round(height)));
        d.setColor(Color.BLACK);
        d.drawRectangle((int) (Math.round(p.getX())), (int) (Math.round(p.getY())), (int) (Math.round(width)),
                (int) (Math.round(height)));
    }
    // CollidableAndSpriteObjects.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Get the sides of the rectangle of the paddle.
        Line[] sides = this.rectangle.getSides();
        //check were was the colliosion.
        if (sides[0].isOnLine(collisionPoint)) {
            //if on the upper side, we'll update the speed to be according angles 300,330,0,30,60 degrees.
            Point[] p = new Point[6];
            p[0] = this.rectangle.getUpperLeft();
            for (int i = 1; i < p.length; i++) {
                p[i] = new Point(p[i - 1].getX() + (this.rectangle.getWidth() / 5), p[i - 1].getY());
            }
            Line[] parts = new Line[5];
            for (int i = 0; i < parts.length; i++) {
                parts[i] = new Line(p[i], p[i + 1]);
            }
            int[] angles = new int[5];
            for (int i = 0; i < angles.length; i++) {
                angles[i] = (300 + 30 * i) % 360;
            }
            for (int i = 0; i < angles.length; i++) {
                if (parts[i].isOnLine(collisionPoint) || (i == angles.length - 1)) {
                    if (i != (angles.length / 2)) {
                        double size = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                + Math.pow(currentVelocity.getDy(), 2));
                        return Velocity.fromAngleAndSpeed(angles[i], size);
                    } else {
                        return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                    }
                }
            }
        }
        //if it's on the downer we'll update the y-Axis direction by negate it.
        if (sides[2].isOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        //if we didn't reach anything else, we'll return the new velocity.
        if (sides[1].isOnLine(collisionPoint)) {
            return new Velocity(Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
        }
        return new Velocity(-1 * Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Setter for the color.
     * @param color new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Setter for maxX.
     * @param maxX the new maxX.
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }
    /**
     * Setter for minX.
     * @param minX
     */
    public void setMinX(int minX) {
        this.minX = minX;
    }
    /**
     * Setter for rectangle.
     * @param rectangle the new rectangle.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    /**
     * Setter for the speed.
     * @param speed the speed.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * Getter for this.maxX.
     * @return this.maxX.
     */
    public int getMaxX() {
        return this.maxX;
    }
    /**
     * Getter for this.minX.
     * @return this.minX.
     */
    public int getMinX() {
        return this.minX;
    }
    /**
     * Getter for this.speed.
     * @return this.speed.
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * Returns the width of the paddle.
     * @return the width of the paddle.
     */
    public int getWidth() {
        return (int) (Math.round(this.rectangle.getWidth()));
    }
}