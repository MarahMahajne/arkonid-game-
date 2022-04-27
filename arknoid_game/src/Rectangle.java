import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;
    private boolean flag;
    /**
     * This is an instructor for class "Rectangle".
     * <p>
     *  Create a new rectangle with location and width/height.
     * <p>
     * @param height - the height of the rectangle.
     * @param width  -  the width of the rectangle.
     * @param upperLeft - the upper left edge of the triangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * This is an instructor for class "Rectangle".
     * <p>
     *  Create a new rectangle with location and width/height.
     * <p>
     * @param height - the height of the rectangle.
     * @param width  -  the width of the rectangle.
     * @param upperLeft - the upper left edge of the triangle.
     * @param color - the color of the rectangle.
     * @param flag - if to fill the rectangle or not.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color, boolean flag) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.flag = flag;
    }
    /**
     * this function finds the intersection points of the rectangle with the given line.
     * <p>
     *  this function divides the rectangle to four lines , each line is one side of the rectangle , and then  finds the
     *  intersection points of line with each side of the rectangle.and adds them to the list of intersection point.
     * </p>
     * @param line - a line.
     * @return intersectionPoints - a list of all the intersection points between the given line and the rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        Line up = this.getLup();
        Line right = this.getLright();
        Line down = this.getLdown();
        Line left = this.getLleft();

        if ((line.isIntersecting(up))) {
            list.add(line.intersectionWith(up));
        }
        if ((line.isIntersecting(right))) {
            list.add(line.intersectionWith(right));
        }
        if ((line.isIntersecting(down))) {
            list.add(line.intersectionWith(down));
        }
        if (line.isIntersecting(left)) {
            list.add(line.intersectionWith(left));
        }
        return (list);
    }
    /**
     * @return width - the width of th rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return height - the height of th rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return upperLeft - the upper Left point of th rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * @return upperRight - the upper right point of th rectangle.
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.upperLeft.getX() + getWidth(), this.upperLeft.getY());
        return upperRight;
    }
    /**
     * @return downRight - the down right point of th rectangle.
     */
    public Point getDownRight() {
        Point downRight = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY() + this.getHeight());
        return downRight;
    }
    /**
     * @return DownLeft - the down Left point of th rectangle.
     */
    public Point getDownLeft() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.getHeight());
        return downLeft;
    }
    /**
     * @return the upper side of the rectangle.
     */
    public Line getLup() {
        return (new Line(getUpperLeft(), getUpperRight()));
    }
    /**
     * @return the right side of the rectangle.
     */
    public Line getLright() {
        return (new Line(getDownRight(), getUpperRight()));
    }
    /**
     * @return the down side of the rectangle.
     */
    public Line getLdown() {
        return (new Line(getDownRight(), getDownLeft()));
    }
    /**
     * @return the left side of the rectangle.
     */
    public Line getLleft() {
        return (new Line(getUpperLeft(), getDownLeft()));
    }
    /**
     * this function sets the rectangle to its new position after applying th velocity on it.
     * @param v - the velocity of the rectangle.
     */
    public void setUpperLeft(Velocity v) {
        this.upperLeft = v.applyToPoint(this.upperLeft);
    }
    /**
     * sets the upper point of the rectangle to the given point.
     * @param p - a point .
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
    /**
     * draws the rectangle.
     * @param d - the drawSurface.
     * */
    public void paintRectangle(DrawSurface d) {
        d.setColor(this.color);
        if (this.flag) {
            d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.upperLeft.getY(), (int) this.width,
                    (int) this.height);
        } else {
            d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.upperLeft.getY(), (int) this.width,
                    (int) this.height);
        }
    }
}