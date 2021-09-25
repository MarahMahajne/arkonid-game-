import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-21
 */
public class EndScreen implements Animation {
    private int score;
    /**
     * Constructor.
     * @param score - player score.
     */
    public EndScreen(int score) {
        this.score = score;
    }
    /**
     * draw EndScreen screen.
     * @param d -  given surface.
     */
    public void doOneFrame(DrawSurface d) {
        int screenWidth = 800;
        int screenHeight = 600;
        d.setColor(new Color(0.902f, 0.902f, 0.980f));
        d.fillRectangle(0, 0, screenWidth, screenHeight);
        d.setColor(new Color(0.345f, 0.000f, 0.000f));
        d.drawText((screenWidth / 5), screenHeight / 2, "Game Over", 100);
        d.drawText((int) (screenWidth / 7.3), (int) (screenHeight * 0.66), "Your score is "
                + this.score, 80);
        d.drawText((int) (screenWidth * 0.01), (int) (screenHeight * 0.98), "Press space to exit", 20);
    }
    /**
     * @return false.
     */
    public boolean shouldStop() {
        return false;
    }
}
