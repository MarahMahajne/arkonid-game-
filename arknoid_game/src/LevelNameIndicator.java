import biuoop.DrawSurface;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 4
 * @since 2021-06-17
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;
    private int blockHeight;
    private int blockWidth;
    /**
     * Constructor.
     * @param levelName - level name.
     * @param blockWidth  - the width of the block.
     * @param blockHeight - the height of the block.
     */
    public LevelNameIndicator(String levelName, double blockWidth, double blockHeight) {
        this.blockWidth = (int) blockWidth;
        this.blockHeight = (int) blockHeight;
        this.levelName = levelName;
    }
    /**
     * draw the level name.
     * @param d - drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.drawText((int) (this.blockWidth * (0.65)), (int) (this.blockHeight * (0.78)),
                "Level Name: " + this.levelName, 22);
    }
    /**
     * ignored.
     */
    public void timePassed() {
    }
    /**
     * add level name as sprite in the game.
     *
     * @param g - game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
