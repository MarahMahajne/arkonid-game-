/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 2
 * @since 2021-06-6
 */
public interface HitListener {
    /**
     * This function is called whenever the beingHit object is hit.
     * @param beingHit - the block being hit.
     * @param hitter - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}