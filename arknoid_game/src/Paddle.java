import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-21
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private GUI gui;
    private Velocity v;
    private Color color;
    private int edgesWidth = 30;
    /**
     * This is an instructor for class "Paddle".
     * <p>
     *  the instructor creates anew paddle.
     * </p>
     * @param gui - a double - the x value of the point.
     * @param  rectangle - the shape of the paddle.
     * @param color - the color of the paddle.
     */
    public Paddle(GUI gui, Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.color = color;
    }
    /**
     * move the paddle to th left.
     * <p>
     *  this function moves the paddle to the left.
     * </p>
     */
    public void moveLeft() {
        Velocity newV = new Velocity(-5, 0);
        Point leftEdge = this.rectangle.getUpperLeft();
        leftEdge = newV.applyToPoint(leftEdge);
        if (leftEdge.getX() < 0 + edgesWidth) {
            newV = new Velocity(5, 0);
        }
        this.v = newV;
        this.rectangle.setUpperLeft(this.v);
    }
    /**
     * move the paddle to th right.
     * <p>
     *  this function moves the paddle to the right.
     * </p>
     */
    public void moveRight() {
        Velocity newV = new Velocity(5, 0);
        Point rightEdge = this.rectangle.getUpperRight();
        rightEdge = newV.applyToPoint(rightEdge);
        if (rightEdge.getX() > this.gui.getDrawSurface().getWidth() - edgesWidth) {
            newV = new Velocity(-5, 0);
        }
        this.v = newV;
        this.rectangle.setUpperLeft(newV);
    }
    /**
     * this function is responsible for the movement of the paddle
     * <p>
     * this function calls the right function every time a key is pressed.
     * </p>
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * this function draws the paddle on the giving draw surface.
     * @param d - the drawing surface.
     */
    public void drawOn(DrawSurface d) {
        int height = (int) rectangle.getHeight();
        int width = (int) rectangle.getWidth();
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        d.setColor(color);
        d.fillRectangle(x, y, width, height);
    }
    /**
     * @return the triangle of this paddle.
     */
    public Rectangle getCollisionRectangle() {
        return (this.rectangle);
    }
    /**
     * this function updates the velocity of the ball after hitting the paddle.
     * @param collisionPoint - the point of collision with the paddle.
     * @param currentVelocity - the current velocity of the ball.
     * @param hitter - the ball that hit the paddle.
     * @return a new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        Line up = this.rectangle.getLup();
        Line right = this.rectangle.getLright();
        Line down = this.rectangle.getLdown();
        Line left = this.rectangle.getLleft();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        int num = 0;
        int c = 0;
        if (y == up.start().getY()) {
            c++;
            num = 1;
        }
        if (x == right.start().getX()) {
            c++;
            num = 2;
        }
        if (y == down.start().getY()) {
            c++;
            num = 3;
        }
        if (x == left.start().getX()) {
            c++;
            num = 4;
        }
        if (c == 1) {
            if (num == 1) {
                newVelocity = hitTheTop(collisionPoint, currentVelocity);
            } else if (num == 3) {
                newVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            } else if ((num == 2) || (num == 4)) {
                newVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            }
        } else {
            newVelocity = new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        return (newVelocity);
    }
    /**
     * This function adds the paddle to the game.
     * <p>
     *  this function adds the paddle to the sprites list and also to collides list.
     * </p>
     * @param g - a game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * this function sets the velocity of th paddle to a new value.
     * @param dx - the change of the x axis.
     * @param dy - the change of the y axis .
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * This function updates the velocity of the ball after hitting the top of the paddle.
     * <p>
     *  this function divides the top of th paddle into five part,and makes th ball bounce back after hitting the top of
     *  the paddle, but each time the ball bounces  in a different angel depending on th part it hit.
     * </p>
     * @param collisionPoint - the point of collision with the paddle.
     * @param currentVelocity - the current velocity of the ball.
     * @return a new velocity of the ball.
     */
    public Velocity hitTheTop(Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = currentVelocity;
        boolean flag = false;
        double width = this.rectangle.getWidth();
        double section = width / 5;
        double x = collisionPoint.getX();
        double x1 = rectangle.getUpperLeft().getX();
        double x2 = x1 + section;
        double x3 = x2 + section;
        double x4 = x3 + section;
        double x5 = x4 + section;
        double x6 = x5 + section;
        if ((x >= x1 && x < x2)) {
            newV = Velocity.fromAngleAndSpeed(300, 5);
            flag = true;
        }
        if ((x >= x2 && x < x3) && (!flag)) {
            flag = true;
            newV = Velocity.fromAngleAndSpeed(330, 5);
        }
        if ((x >= x3 && x < x4) && (!flag)) {
            flag = true;
           // newV = Velocity.fromAngleAndSpeed(360, 5);
            newV = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if ((x >= x4 && x < x5) && (!flag)) {
            flag = true;
            newV = Velocity.fromAngleAndSpeed(30, 5);
        }
        if ((x >= x5 && x <= x6) && (!flag)) {
            newV = Velocity.fromAngleAndSpeed(60, 5);
        }
        return (newV);
    }
}
