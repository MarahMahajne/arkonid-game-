import biuoop.DrawSurface;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-17
 */
public interface Animation {
    /**
     * do on frame .
     * @param d - the drawing surface.
     */
    void doOneFrame(DrawSurface d);
    /**
     *@return true if the loop should stop otherwise return false.
     */
    boolean shouldStop();
}