import biuoop.DrawSurface;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public interface Sprite {
    /**
     *  draw the sprite to the screen.
     * @param d - the drawing surface.
     */
    void drawOn(DrawSurface d);
    /**
     *  notify the sprite that time has passed.
     */
    void timePassed();
}