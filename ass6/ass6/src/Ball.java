import biuoop.DrawSurface;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 2
 * @since 2021-06-06
 */
public class Ball implements Sprite {
    private int r;
    private java.awt.Color color;
    private Point center;
    private Velocity v;
    private GameEnvironment env;
    /**
     * This is an instructor for class "Ball".
     * <p>
     *  the instructor receives the x and y value of the center point of the ball, ans also the  radios and color of the
     *  ball and sets them to a specific ball.
     * <p>
     * @param  x - an integer - the x value for the center of the ball.
     * @param  y - an integer - the y value for the center of the ball.
     * @param  r - an integer - the radios of the ball(circle).
     * @param  color - the color of the ball(circle).
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.r = r;
        this.color = color;
        this.center = new Point(x, y);
    }
    /**
     * This is another instructor for class "Ball".
     * <p>
     *  the instructor receives the center point of the ball ,and also the radios and color of the ball and sets them to
     *  a specific ball.
     * <p>
     * @param  center - a Point - witch has the x and y value of the center of the ball.
     * @param  r - an integer - the radios of the ball(circle).
     * @param  color - the color of the ball(circle).
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.r = r;
        this.color = color;
        this.center = new Point(center.getX(), center.getY());
    }
    /**
     * This function returns the x value of the center point of the ball.
     * @return x - an integer - the x value of the center point of the ball.
     */
    public int getX() {
        int x = (int) this.center.getX();
        return (x);
    }
    /**
     * This function returns the y value of the center point of the ball.
     * @return y - an integer - the y value of the center point of the ball.
     */
    public int getY() {
        int y = (int) this.center.getY();
        return (y);
    }
    /**
     * This function returns the size of the ball(radios of the circle).
     * @return r - an integer -the size of the ball(radios of the circle).
     */
    public int getSize() {
        return (r);
    }
    /**
     * This function returns the color of the ball.
     * @return color -the color of the ball.
     */
    public java.awt.Color getColor() {
        return (color);
    }

    // draw the ball on the given DrawSurface
    /**
     * This function draw the ball on the given DrawSurface.
     * @param surface - the drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), this.r);
    }
    /**
     * timePassed.
     */
    public void timePassed() {
        move();
    }

    /**
     * This function sets the value of Velocity of the ball to a the new given value.
     * @param velocity - a Velocity - a new velocity for the ball.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }
    /**
     * This function sets the value of the Velocity of the ball to a new value constructed using the dx and dy values.
     * @param dx - the distance the ball moves on X-axis.
     * @param dy - the distance the ball moves on Y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * This function returns the Velocity of the current ball.
     * @return v - the Velocity of the current ball.
     */
    public Velocity getVelocity() {
        return (v);
    }
    /**
     * This function sets the center point to its new value after moving one step.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * This function is responsible for the ball movement and reaction after hitting something.
     */
    public void move() {
        CollisionInfo inf;
        Velocity newV;
        boolean flag = false;
        Line l = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        if (env.getClosestCollision(l) != null) {
            Point nextCollisionPoint = env.getClosestCollision(l).collisionPoint();
            if (nextCollisionPoint != null) {
                double x = nextCollisionPoint.getX();
                double y = nextCollisionPoint.getY();
                double x1 = l.start().getX();
                double x2 = l.end().getX();
                double y1 = l.start().getY();
                double y2 = l.end().getY();
                if ((x >= Math.min(x1, x2) && x <= Math.max(x1, x2))
                        && (y >= Math.min(y1, y2) && y <= Math.max(y1, y2))) {
                    inf = env.getClosestCollision(l);
                    this.center = nextCollisionPoint;
                    newV = inf.collisionObject().hit(this, nextCollisionPoint, this.v);
                    this.setVelocity(newV);
                    this.center = newV.applyToPoint(this.center);
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * This function returns the Velocity of the current ball.
     * @param startX - the x value of the right edge of the screen.
     * @param startY - the y value of the right edge of the screen.
     * @param endX - the x value of the left edge of the screen.
     * @param endY - the y value of the left edge of the screen
     */
    public void watchTheEdge(int startX, int startY, int endX, int endY) { //check
        Point p = new Point(this.center.getX(), this.center.getY());
        p = this.getVelocity().applyToPoint(p);
        int counter = 0;
        if ((p.getX() >= endX - r) && (counter < 1)) {
            counter++;
            v = Velocity.fromAngleAndSpeed(180 + v.getAngle(), v.getSpeed());
            this.setVelocity(v);
        }
        if ((p.getX() <= startX + r) && (counter < 1)) {
            counter++;
            v = Velocity.fromAngleAndSpeed(180 + v.getAngle(), v.getSpeed());
            this.setVelocity(v);
        }

        if ((p.getY() >= endY - r) && (counter < 1)) {
            counter++;
            v = Velocity.fromAngleAndSpeed(180 + v.getAngle(), v.getSpeed());
            this.setVelocity(v);
        }
        if ((p.getY() <= startY + r) && (counter < 1)) {
            counter++;
            v = Velocity.fromAngleAndSpeed(180 + v.getAngle(), v.getSpeed());
            this.setVelocity(v);
        }
        if (counter > 1) {
            v = Velocity.fromAngleAndSpeed(180 + v.getAngle(), v.getSpeed());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
  /*  public void watch(int startX, int startY, int endX, int endY) { //check
        Point p = new Point(this.center.getX(), this.center.getY());
        p = this.getVelocity().applyToPoint(p);
        int counter = 0;
        if ((p.getX() >= endX - r) && (counter < 1)) {
            counter++;
            this.setVelocity(-v.getDx(), v.getDy());

        }
        if ((p.getX() <= startX + r) && (counter < 1)) {
            counter++;
            this.setVelocity(-v.getDx(), v.getDy());
        }

        if ((p.getY() >= endY - r) && (counter < 1)) {
            counter++;
            this.setVelocity(v.getDx(), -1 * v.getDy());
        }
        if ((p.getY() <= startY + r) && (counter < 1)) {
            counter++;
            this.setVelocity(v.getDx(),-1* v.getDy());
        }
        if (counter > 1) {
            this.setVelocity(-v.getDx(), -v.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }*/
    /**
     * This function sets the environment of the game to the given environment.
     * @param e - environment of the game.
     */
    public void setEnv(GameEnvironment e) {
        this.env = e;
    }
    /**
     * This function adds the ball to the game.
     * <p>
     *  this function adds the ball to the  sprites list.
     * </p>
     * @param g - a game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * This function removes the ball from the game.
     * <p>
     *  this function removes the ball from the  sprites list.
     * </p>
     * @param game - a game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}