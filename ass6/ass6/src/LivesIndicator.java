import biuoop.DrawSurface;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 4
 * @since 2021-06-21
 */
public class LivesIndicator implements Sprite {
    private Counter c;
    private int blockWidth;
    private int blockHeight;
    /**
     * Constructor.
     * @param c -  counter for lives.
     * @param blockWidth - block width.
     * @param blockHeight - block height.
     */
    public LivesIndicator(Counter c, double blockWidth, double blockHeight) {
        this.c = c;
        this.blockWidth = (int) blockWidth;
        this.blockHeight = (int) blockHeight;
    }

    /**
     * draw the word lives and the remained lives.
     * @param d - drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.drawText((int) (this.blockWidth * (0.1)), (int) (this.blockHeight * (0.78)),
                "Lives: " + Integer.toString(this.c.getValue()), 22);
    }
    /**
     * ignored.
     */
    public void timePassed() {
    }
    /**
     * add this indicator to the game as sprite.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
