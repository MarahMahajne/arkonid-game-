import biuoop.DrawSurface;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-21
 */
public class Background implements Sprite {
    private List<Line> lines;
    private List<Circle> circles;
    private List<Rectangle> rectangles;
    /**
     * This is an constructor for class "background".
     * @param  lines -  a list of lines
     * @param  circles - a list of circles
     * @param  rectangles - a list of rectangles
     */
    public Background(List<Line> lines, List<Circle> circles, List<Rectangle> rectangles) {
        this.lines = lines;
        this.circles = circles;
        this.rectangles = rectangles;
    }
    /**
     * This function draw the Background on the given DrawSurface.
     * @param d - the drawing surface.
     */
    public void drawOn(DrawSurface d) {
        for (Line l : lines) {
            l.paintLine(d);
        }
        for (Circle c : circles) {
            c.drawCircale(d);
        }
        for (Rectangle r : rectangles) {
            r.paintRectangle(d);
        }
    }
    /**
     * timePassed.
     */
    public void timePassed() {
    }
    /**
     * add object to game.
     * @param g : game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}