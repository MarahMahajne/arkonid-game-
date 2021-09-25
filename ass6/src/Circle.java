import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 4
 * @since 2021-06-17
 */
public class Circle {
    private int x;
    private int y;
    private int r;
    private Color color;
    private boolean flag;
    /**
     * Constructor for circle.
     * @param x - x value of circle center.
     * @param y - y value of circle center.
     * @param r - radius.
     * @param color - circle color.
     */
    public Circle(double x, double y, int r, Color color) {
        this.x = (int) x;
        this.y = (int) y;
        this.color = color;
        this.r = r;
    }

    /**
     *  another Constructor.
     * @param center - center point .
     * @param r - radius.
     * @param color  - circle color.
     * @param flag - whether or not to fill the circle.
     */
    public Circle(Point center, int r, Color color, Boolean flag) {
        this.x = (int) center.getX();
        this.y = (int) center.getY();
        this.r = r;
        this.color = color;
        this.flag = flag;
    }
    /**
     * draw a circle in given surface.
     * @param d - given surface
     */
    public void drawCircale(DrawSurface d) {
        d.setColor(this.color);
        if (this.flag) {
            d.fillCircle(this.x, this.y, this.r);
        } else {
            d.drawCircle(this.x, this.y, this.r);
        }
    }
}
