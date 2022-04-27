/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * This is an instructor for class "Velocity".
     * <p>
     *  the instructor receives dx and dy values and sets them to the current Velocity.
     * </p>
     * @param  dx - a double - the change in position in x axes.
     * @param  dy - a double - the change in position in y axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Another instructor for class "Velocity".
     * <p>
     *  the instructor receives a new velocity, and sets the current dx and dy to their new value..
     * </p>
     * @param  v - a Velocity - the change in position in x and y axes.
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }
    /**
     * This function returns the new position of a point after moving one step.
     * <p>
     *  the function receives a point, and sets the current x and y to their new values. and returns the new position of
     *  a point after moving one step
     * </p>
     * @param  p - a point - the new point.
     * @return next - a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        Point next = new Point(x + dx, y + dy);
        return (next);
    }
    /**
     * This function return the change in position in x axes .
     * @return dx -the change in position in x axes .
     */
    public double getDx() {

        return (this.dx);
    }
    /**
     * This function return the change in position in y axes .
     * @return dy -the change in position in y axes .
     */
    public double getDy() {
        return (this.dy);
    }
    /**
     * This function works as another constrictor for this class.
     * <p>
     *  the function receives the value and angle of the wanted speed (Velocity), and calculates the new dx and dy , and
     *  constructs a new Velocity.
     * </p>
     * @param  angle - a degree(double) - the angle of the wanted speed.
     * @param  speed - a double - the value of the wanted speed.
     * @return velocity - a new Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //double dy = -(speed * Math.sin(angle));
        //double dx = speed * Math.cos(angle);
        //return new Velocity(dx, dy);
        double originalAngle;
        if (angle <= 90) {
            originalAngle = 90 - angle;
        } else {
            originalAngle = 450 - angle;
        }
        double dy = -(Math.sin(Math.toRadians(originalAngle)) * speed);
        double dx = Math.cos(Math.toRadians(originalAngle)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * This function return the angle of the speed.
     * @return angle - the angle of the current speed.
     */
    public double getAngle() {
        double angle = Math.atan(-1 * this.dy / this.dx);
        return angle;
    }
    /**
     * This function return the value of the speed.
     * @return speed - the value of the current speed.
     */
    public double getSpeed() {
        double speed = this.dx / Math.cos(this.getAngle());
        return speed;
    }
}
