import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 4
 * @since 2020-06-17
 */
public class VictoryScreen implements Animation {
    private int score;
    /**
     * Constructor.
     * @param score - the score.
     */
    public VictoryScreen(int score) {
        this.score = score;
    }

    /**
     * draw victory screen.
     * @param d - drawing given surface.
     */
    public void doOneFrame(DrawSurface d) {
        int screenWidth = 800;
        int screenHeight = 600;
        d.setColor(new Color(0.902f, 0.902f, 0.980f));
        d.fillRectangle(0, 0, screenWidth, screenHeight);
        d.setColor(new Color(0.196f, 0.804f, 0.196f));
        d.drawText((int) (screenWidth / 4.2), screenHeight / 2, "You Win !", 100);
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
