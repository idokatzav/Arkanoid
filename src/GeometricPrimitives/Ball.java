package GeometricPrimitives;

import CollidableAndSpriteObjects.Collidable;
import CollidableAndSpriteObjects.GameEnvironment;
import CollidableAndSpriteObjects.Paddle;
import CollidableAndSpriteObjects.Sprite;
import GameMain.GameLevel;
import MovingAndMechanics.CollisionInfo;
import MovingAndMechanics.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * GeometricPrimitives.Ball class. The base of ball object - one of the bases of the game.
 * Methods: Constructors and Copy Constructor.
 *          addToGame - add the ball to a given game.
 *          timePassed - calls moveOneStep method to move the ball.
 *          drawOn - draws the ball on a given drawSurface.
 *          moveOneStep - moves the ball one step and update it to change direction after collisions with some other
 *                        Collidables.
 *          Getters and Setters.
 */
public class Ball implements Sprite {
    //fields
    private int xSize;
    private int ySize;
    private int radius;
    private Point point;
    private java.awt.Color color;
    private Velocity v;
    private int xMin;
    private int yMin;
    private GameEnvironment environment;
    private Paddle paddle;
    private int sidesSmall;
    /**
     * Constructor.
     * @param center the center of the circle.
     * @param r the radius of the circle.
     * @param color the color of the circle.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xSize = 200;
        this.ySize = 200;
        this.xMin = 0;
        this.yMin = 0;
        this.environment = new GameEnvironment();
        this.paddle = null;
        this.sidesSmall = 0;
    }
    /**
     * Another constructor.
     * @param x x-Axis value.
     * @param y y-Axis value.
     * @param r the radius.
     * @param color the color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.xSize = 200;
        this.ySize = 200;
        this.v = new Velocity(0, 0);
        this.xMin = 0;
        this.yMin = 0;
        this.environment = new GameEnvironment();
        this.paddle = null;
        this.sidesSmall = 0;
    }
    /**
     * Copy Constructor.
     * @param b another ball to copy.
     */
    public Ball(Ball b) {
        this.xSize = b.xSize;
        this.ySize = b.ySize;
        this.radius = b.radius;
        this.point = new Point(b.point);
        this.color = b.color;
        this.v = b.v;
        this.xMin = b.xMin;
        this.yMin = b.yMin;
        this.environment = b.environment;
        this.paddle = b.paddle;
        this.sidesSmall = b.sidesSmall;
    }
    /**
     * The sides of the board.
     * @return this.sidesSmall.
     */
    public int getSidesSmall() {
        return sidesSmall;
    }
    /**
     * Setter for this.sidesSmall.
     * @param sidesSmall new value for this.sidesSmall.
     */
    public void setSidesSmall(int sidesSmall) {
        this.sidesSmall = sidesSmall;
    }
    /**
     * Setter for this.paddle.
     * @param paddle the paddle.
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
    /**
     * Getter for paddle.
     * @return this.paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
    @Override
    public void addToGame(GameLevel g) {
       g.addSprite(this);
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    //accessors.
    /**
     * Getter for the environment.
     * @return the environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
    /**
     * Add collidable to the environment.
     * @param c the new collidable to add.
     */
    public void addCollidableToEnvironment(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Setter for game environment.
     * @param gameEnvironment a new environment.
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }
    /**
     * Returns the x-Axis value fixed to the start of the board (minX).
     * @return the fixed size.
     */
    public int getXAfterFixedToMin() {
        return this.xMin + this.getX();
    }
    /**
     * Returns the y-Axis value fixed to the start of the board (minY).
     * @return the fixed size.
     */
    public int getYAfterFixedToMin() {
        return this.yMin + this.getY();
    }
    /**
     * Getter for x.
     * @return x-axis value of the center of the circle.
     */
    public int getX() {
        return (int) (Math.round(this.point.getX()));
    }
    /**
     * Getter for y.
     * @return y-axis value of the center of the circle.
     */
    public int getY() {
        return (int) (Math.round(this.point.getY()));
    }
    /**
     * Getters for the radius of the circle.
     * @return the radius.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * Setter for this.radius.
     * @param size the new size.
     */
    public void setSize(int size) {
        if (size < 0) {
            return;
        }
        this.radius = size;
    }
    /**
     * Getter for this.color.
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Setter for this.color.
     * @param color the new color.
     */
    public void setColor(java.awt.Color color) {
        if (color != null) {
            this.color = color;
        }
    }
    /**
     * Returns this xSize.
     * @return xSize.
     */
    public int getXSize() {
        return this.xSize;
    }
    /**
     * Returns this ySize.
     * @return ySize.
     */
    public int getYSize() {
        return this.ySize;
    }
    /**
     * Getter for yMin.
     * @return this.yMin.
     */
    public int getYMin() {
        return this.yMin;
    }
    /**
     * Getter for xMin.
     * @return this.xMin.
     */
    public int getXMin() {
        return this.xMin;
    }
    /**
     * Setter for xMin.
     * @param xMin new value for xMin.
     */
    public void setXMin(int xMin) {
        if (xMin < 0) {
            return;
        }
        this.xMin = xMin;
    }
    /**
     * Setter for this.point.x.
     * @param x the new x.
     */
    public void setX(int x) {
        this.point.setX(x);
    }
    /**
     * Setter for this.point.y.
     * @param y the new y.
     */
    public void setY(int y) {
        this.point.setY(y);
    }
    /**
     * Setter for yMin.
     * @param yMin new value for yMin.
     */
    public void setYMin(int yMin) {
        if (yMin < 0) {
            return;
        }
        this.yMin = yMin;
    }
    /**
     * Sets this xSize.
     * @param xSize new xSize value.
     */
    public void setXSize(int xSize) {
        this.xSize = xSize;
    }
    /**
     * Sets this ySize.
     * @param ySize new ySize value.
     */
    public void setYSize(int ySize) {
        this.ySize = ySize;
    }
    /**
     * Draw the ball on the given DrawSurface.
     * @param surface the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getXAfterFixedToMin(), this.getYAfterFixedToMin(), this.getSize());
        surface.setColor(this.color);
        surface.fillCircle(this.getXAfterFixedToMin(), this.getYAfterFixedToMin(), this.getSize());
    }
    /**
     * Set the velocity.
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = new Velocity(v);
    }
    /**
     * Set the velocity.
     * @param dx the new dx.
     * @param dy the new dy.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * Returns a copy of this object.
     * @return a copy of the velocity object.
     */
    public Velocity getVelocity() {
        return new Velocity(this.v);
    }
    /**
     * Moves the ball one step.
     */
    public void moveOneStep() {
        //Check if the ball is inside the paddle
        if (this.paddle != null) {
            //if it's near one of the sides of the board - to fix bugs of stacking between a border and the paddle.
            int r = 0;
            if ((this.getX() > this.xSize - this.sidesSmall - 10 * this.radius) || (this.getX() < this.sidesSmall
                    + 10 * this.radius)) {
                //if it can make it, we'll think like being inside the paddle is that a part of the ball inside it.
                r = this.radius;
            }
            //we'll check if part of the ball or the ball (according to the relevant) inside the paddle.
            if (this.paddle.getCollisionRectangle().isInside(this.point, r)) {
                //if it is, we'll update its point to be a few pixels above to paddle.
                int add = 0;
                if (this.point.getY() > paddle.getCollisionRectangle().getUpperLeft().getY()
                        + paddle.getCollisionRectangle().getHeight() / 2 || r == 0) {
                    this.point = new Point(this.point.getX(),
                            this.paddle.getCollisionRectangle().getUpperLeft().getY() + (int) (Math.round(this.
                                    paddle.getCollisionRectangle().getHeight())) + 2 * this.radius);
                } else {
                    this.point = new Point(this.point.getX(),
                            this.paddle.getCollisionRectangle().getUpperLeft().getY() - 2 * this.radius);
                }
                //if the radius of checking isn't zero we wanted to check if parts of the ball i inside.
                if (r == this.radius) {
                    //if it is we'll update the directions according to the place of the hit.
                    if (this.getX() > this.xSize / 2) {
                        this.v.setDx(-1 * Math.abs(this.v.getDx()));
                        this.v.setDy(-1 * Math.abs(this.v.getDy()));
                    } else {
                        this.v.setDx(Math.abs(this.v.getDx()));
                        this.v.setDy(-1 * Math.abs(this.v.getDy()));
                    }
                    return;
                }
                //else we'll update the direction to be above.
                this.v.setDy(-1 * Math.abs(this.v.getDy()));
                return;
            }
        }
        //if it isn't inside the paddle, we'll check for collisions with collidables.
        Velocity v = this.getVelocity();
        Line l = new Line(new Point(this.point), new Point(this.point.getX() + v.getDx(), this.point.getY()
                + v.getDy()));
        CollisionInfo c = this.environment.getClosestCollision(l);
        //if it has a collision with an object, it means c != null so we'll do what's inside the block.
        if (c != null) {
            //we'll get the collision object and from it the collision rectangle and by that.
            Collidable c1 = c.collisionObject();
            this.v = c1.hit(this, c.collisionPoint(), this.v);
            Rectangle rect = c1.getCollisionRectangle();
            //now, we'll get the lines of the sides of the rectangle to check where was the collision.
            Line[] sides = rect.getSides();
            /*
             * sides[0] means the upper, sides[1] the righter, sides[2] the downer, and sides[3] the lefter,
             * by that update the velocity and the center of the ball. We also have to check if it's on intersection
             * of two sides that have a similar point between them.
            */
            if (sides[0].isOnLine(c.collisionPoint()) && sides[1].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX() + this.radius, c.collisionPoint().getY()
                        - this.radius);
            } else if (sides[1].isOnLine(c.collisionPoint()) && sides[2].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX() + this.radius, c.collisionPoint().getY()
                        + this.radius);
            } else if (sides[2].isOnLine(c.collisionPoint()) && sides[3].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX() - this.radius, c.collisionPoint().getY()
                        + this.radius);
            } else if (sides[0].isOnLine(c.collisionPoint()) && sides[3].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX() - this.radius, c.collisionPoint().getY()
                        - this.radius);
            } else if (sides[0].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX(), c.collisionPoint().getY() - this.radius);
            } else if (sides[1].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX() + this.radius + this.v.getDx(),
                        c.collisionPoint().getY());
            } else if (sides[2].isOnLine(c.collisionPoint())) {
                this.point = new Point(c.collisionPoint().getX(), c.collisionPoint().getY() + this.radius);
            } else { //it means it's on the sides[3].
                this.point = new Point(c.collisionPoint().getX() - this.radius + this.v.getDx(),
                        c.collisionPoint().getY());
            }
            return;
        }
        this.point = this.getVelocity().applyToPoint(this.point);
//        //if the circle passed the left border, we'll fix its place.
//        if ((this.point.getX() + this.v.getDx() - this.radius) < 0) {
//            this.point.setX(Math.abs(this.point.getX() + this.v.getDx() - this.radius) + this.radius);
//            //we'll switch the velocity in x to the negated of it.
//            this.v.setDx(-1 * this.v.getDx());
//            /*
//             * if also the y is negative we'll fix it, also if more than the y max we'll fix it,
//             * else we'll add the dy to y.
//             */
//            if ((this.point.getY() + this.v.getDy() - this.radius) < 0) {
//                this.point.setY(Math.abs(this.point.getY() + this.v.getDy() - this.radius) + this.radius);
//                this.v.setDy(-1 * this.v.getDy());
//            } else if ((this.point.getY() + this.v.getDy()  + this.radius) > this.ySize) {
//                this.point.setY(2 * this.ySize - (this.point.getY() + this.v.getDy() + this.radius) - this.radius);
//                this.v.setDy(-1 * v.getDy());
//            } else {
//                this.point.setY(this.getY() + this.v.getDy());
//            }
//        } else if ((this.point.getX() + this.v.getDx() + this.radius) > this.xSize) {
//            //if we reached there - we passed the right border, we'll fix the x value.
//            this.point.setX(2 * this.xSize - (this.point.getX() + this.v.getDx() + this.radius) - this.radius);
//            //we'll switch the velocity in x to the negated of it.
//            this.v.setDx(-1 * v.getDx());
//            /*
//             * if the y is negative we'll fix it, or if also the y is more than the y max we'll fix it,
//             * else we'll add the dy to y.
//             */
//            if ((this.point.getY() + this.v.getDy() - this.radius) < 0) {
//                this.point.setY(Math.abs(this.point.getY() + this.v.getDy() - this.radius) + this.radius);
//                this.v.setDy(-1 * this.v.getDy());
//            } else if ((this.point.getY() + this.v.getDy() + this.radius) > this.ySize) {
//                this.point.setY(2 * this.ySize - (this.point.getY() + this.v.getDy() + this.radius) - this.radius);
//                this.v.setDy(-1 * v.getDy());
//            } else {
//                this.point.setY(this.getY() + this.v.getDy());
//            }
//        } else if ((this.point.getY() + this.v.getDy() - this.radius) < 0) {
//            //if only the y-Axis value is out of the borders and negative, we'll fix it and update x.
//            this.point.setY(Math.abs(this.point.getY() + this.v.getDy() - this.radius) + this.radius);
//            this.v.setDy(-1 * this.v.getDy());
//            this.point.setX(this.point.getX() + this.v.getDx());
//        } else if ((this.point.getY() + this.v.getDy() + this.radius) > this.ySize) {
//            //if only the y-Axis value is out of the border and more than the max value, we'll fix it and update x.
//            this.point.setY(2 * this.ySize - (this.point.getY() + this.v.getDy() + this.radius) - this.radius);
//            this.v.setDy(-1 * v.getDy());
//            this.point.setX(this.point.getX() + this.v.getDx());
//        } else {
//            //this case is when both are ok.
//            this.point = this.getVelocity().applyToPoint(this.point);
//        }
    }
}