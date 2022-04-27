/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * This is an instructor for class "Rectangle".
     * <p>
     *  Create a new score counter object.
     * <p>
     * @param scoreCounter - counter for the current score in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This function adds points to the score when you hit a block in the game.
     * <p>
     *     the function adds 5 points if you hit a single block and zero points if you hit the death block.
     * </p>
     * @param beingHit - the block being hit.
     * @param hitter - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.deathBlock()) {
            return;
        }
        currentScore.increase(5);
    }
}