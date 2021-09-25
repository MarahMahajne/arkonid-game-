/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-06
 */
public interface Collidable {
    /**
     * @return collisionRectangle - the collision rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * this function updates the velocity of the ball after hitting any of th collisions.
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the current velocity of the ball.
     * @param hitter - the ball that hit the block.
     * @return a new velocity of the ball.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}



