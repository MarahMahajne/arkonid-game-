import java.awt.Color;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 4
 * @since 2021-06-17
 */
public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();
    /**
     * @return list of initial balls velocity.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();
    /**
     * @return level name.
     */
    String levelName();

    /**
     * @return level's background.
     */
    Sprite getBackground();
    /**
     * @return blocks in the level.
     */
    List<Block> blocks();
    /**
     * @return number of blocks must be destroyed yo pass the level.
     */
    int numberOfBlocksToRemove();
    /**
     * @return balls in the level.
     */
    List<Ball> getBalls();
    /**
     * @return the background color.
     * */
    Color getBackgroundColor();
    /**
     * this function creates the balls of the level.
     * */
    void createBalls();
}