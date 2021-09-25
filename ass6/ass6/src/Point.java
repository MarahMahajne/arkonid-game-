/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-06
 */
public class Point {
    private double x;
    private double y;
    /**
     * This is an instructor for class "Point".
     * <p>
     *  the instructor receives x and y values and sets them to the current point.
     * </p>
     * @param  x - a double - the x value of the point.
     * @param  y - a double - the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * This function return the distance between tow points.
     * @param other - another point.
     * @return distance - a double - the distance between tow points.
     */
    public double distance(Point other) {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * This function checks if  the points are equal
     * <p>
     *  The function  returns true is the points are equal, false otherwise.
     * </p>
     * @param other - another point.
     * @return true - if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX()) {
            if (this.getY() == other.getY()) {
                return true;
            }
        }
        return false;
    }
    /**
     * This function return the x value of the point .
     * @return x - the x value of the point.
     */
    public double getX() {
        return (this.x);
    }
    /**
     * This function return the y value of the point .
     * @return y - the y value of the point.
     */
    public double getY() {
        return (this.y);
    }
    /**
     * changes the x value of the point.
     * @param newX - x value.
     */
    public void setX(double newX) {
        this.x = newX;
    }
    /**
     * changes the y value of the point.
     * @param newY - y value.
     */
    public void setY(double newY) {
        this.y = newY;
    }
}