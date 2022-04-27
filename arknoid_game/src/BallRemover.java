/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 2
 * @since 2020-06-5
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * This is an constructor for class "Ball remover".
     * <p>
     *  the constructor creates a new ballRemover object.
     * <p>
     * @param  game - the game.
     * @param  remainingBalls - the number of the remained blocks in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.game = game;
    }
    /**
     * This function removes the blocks from the game if they hit the death Block which is on the bottom of the screen.
     * @param beingHit - the block being hit.
     * @param hitter - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.deathBlock()) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
            this.game.setRemainingBalls(this.remainingBalls);
        }
    }
}
