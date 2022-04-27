/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-06
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * This is an constructor for class "Ball remover".
     * <p>
     *  the constructor creates a new ballRemover object.
     * <p>
     * @param  game - the game.
     * @param  remainingBlocks - the number of the remained blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
        this.game = game;
    }
    /**
     * This function removes the blocks from the game if they hit th death Block.
     * @param beingHit - the block being hit.
     * @param hitter - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.game.setRemainingBlocks(this.remainingBlocks);
    }
}
