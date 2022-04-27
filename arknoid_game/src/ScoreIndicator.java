import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public class ScoreIndicator implements Sprite {
    private Counter scoresCounter;
    private int scoreBlockWidth;
    private int scoreBlockHeight;
    /**
     * This is an instructor for class "ScoreIndicator".
     * <p>
     *  Create a new ScoreIndicator object.
     * <p>
     * @param scoresCounter - counter for the current score in the game.
     * @param scoreBlockWidth - the width of the window that presents the score.
     * @param scoreBlockHeight - the height of the window that presents the score.
     */
    public ScoreIndicator(Counter scoresCounter, double scoreBlockWidth, double scoreBlockHeight) {
        this.scoresCounter = scoresCounter;
        this.scoreBlockWidth = (int) scoreBlockWidth;
        this.scoreBlockHeight = (int) scoreBlockHeight;

    }

    /**
     * displaying the score on given surface.
     *
     * @param d : given surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText((int) (this.scoreBlockWidth * (0.4)), (int) (this.scoreBlockHeight * (0.78)),
                "Score: " + Integer.toString(this.scoresCounter.getValue()), 22);
    }
    /**
     * time passed - ignored.
     */
    public void timePassed() {
    }
    /**
     * add the score indicator to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
