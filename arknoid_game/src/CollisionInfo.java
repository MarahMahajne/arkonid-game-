/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 2
 * @since 2021-06-06
 */
public class CollisionInfo {
    private Point collisionPoint;
    private  Collidable collisionObject;
    /**
     * This is an instructor for class "CollisionInfo".
     * <p>
     *  the instructor creates a new  CollisionInfo
     * </p>
     * @param collisionPoint - the collision point.
     * @param collisionObject - the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * @return collisionPoint - the collision point.
     */
    public Point collisionPoint() {
        return (this.collisionPoint);
    }
    /**
     * @return collisionObject - the collision object.
     */
    public Collidable collisionObject() {
        return (this.collisionObject);
    }
}
