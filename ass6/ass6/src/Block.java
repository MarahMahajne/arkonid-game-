import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-06
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private  boolean flag;
    private  boolean newBall = false;
    private  boolean kill = false;
    private List<HitListener> hitListeners;

    /**
     * This is an instructor for class " Block".
     * <p>
     *  the instructor creates anew block.
     * </p>
     * @param  rectangle - the shape of the block.
     * @param color - the color of the paddle.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        int num = new Random().nextInt(10) + 1;
        if (num == 5) { // remove the ball that hit this block
            this.kill = true;
        } else {
            if (num == 9) { // add anew ball if you hit this block
                this.newBall = true;
            }
        }
        if ((this.rectangle.getUpperLeft().getY() == 600) && (this.rectangle.getUpperLeft().getX() == 30)) {
            this.flag = true;
        }

    }
    /**
     * @return the triangle of this paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * this function updates the velocity of the ball after hitting the block.
     * @param collisionPoint - the point of collision with the block.
     * @param currentVelocity - the current velocity of the ball.
     * @param hitter - the ball that hit the object.
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
            if ((num == 1) || (num == 3)) {
                newVelocity = new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            } else if ((num == 2) || (num == 4)) {
                newVelocity = new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            }
        } else {
            newVelocity = new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return (newVelocity);
    }
    /**
     * This function draws the block on the drawing surface.
     * @param d - the draw surface.
     */
    public void drawOn(DrawSurface d) {
        int height = (int) rectangle.getHeight();
        int width = (int) rectangle.getWidth();
        double x = rectangle.getUpperLeft().getX();
        double y = rectangle.getUpperLeft().getY();
        if (!this.deathBlock()) {
            d.setColor(Color.BLACK);
            d.drawRectangle((int) x, (int) y, width, height);
            d.setColor(this.color);
            d.fillRectangle((int) x, (int) y, width, height);
        }
    }
    /**
     * ignored.
     */
    public void timePassed() {
    }
    /**
     * This function adds the block to the game.
     * <p>
     *  this function adds the block to the sprites list and also to collides list.
     * </p>
     * @param g - a game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * This function removes the block from the game.
     * <p>
     *  this function removes the block from the sprites list and theo colliders list.
     * </p>
     * @param game - a game.
     */
    public void  removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Notify all listeners about a hit event.
     * @param hitter - the ball that hit the block.
     */
    private void  notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        System.out.println(listeners.size());

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * add a hit listener to the list of hitListeners.
     * @param hl - a hitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * remove a hit listener from the list of hitListeners.
     * @param hl - a hitListener.
     */
    public void removeHitListener(HitListener hl) {
        try {
            this.hitListeners.remove(hl);
        } catch (Exception e) { //not found in the collection of the listeners.
            System.out.println("this listener is not in the block's hit listeners");
        }
    }
    /**
     * @return true if this is the death block.else returns false.
     */
    public boolean deathBlock() {
        if (this.flag) {
            return true;
        }
        return false;
    }
    /**
     * this function sets the flag to a new value.
     * @param value - boolean value;
     */
    public void setFlag(boolean value) {
        this.flag = value;
    }
    /**
     * @return the flag field value.
     */
    public boolean getFlag() {
       return this.flag;
    }
    /**
     * @return the rectangle field value.
     * */
    public Rectangle getRectangle() {
       return this.rectangle;
    }
   /* public boolean getKll() {
        if (this.kill) {
            return true;
        }
        return false;
    }
    public boolean getNewBall() {
        if (this.newBall) {
            return true;
        }
        return false;
    }*/
}
