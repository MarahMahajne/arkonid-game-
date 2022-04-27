import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 2
 * @since 2021-06-06
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * This is an instructor for class "Game Environment".
     * <p>
     *  the instructor creates the new game environment.
     * </p>
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * This function adds the given collidable to the list of the collidables.
     * @param c - a collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * This function removes the given collidable from the list of the collidables.
     * @param c - a collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * This function finds information about the closest collision.
     * <p>
     * assume an object moves from the start of the giving line to the end of it , if this object will not collide with
     * any of the collidables the function returns null.Else, return the information about the closest collision that is
     * going to occur.
     * </p>
     * @param  trajectory - the line the ball moves on.
     * @return information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point p;
        Rectangle r;
        CollisionInfo inf;
        for (Collidable c : collidables) {
            r = c.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(r) != null) {
                p = trajectory.closestIntersectionToStartOfLine(r);
                inf = new CollisionInfo(p, c);
                return (inf);
            }
        }
        return null;
    }

    /**
     * This function sets the list of collidables to the given value.
     * @param list - a list of collidables.
     */
    public void setGameEnvironment(List<Collidable> list) {
        this.collidables = list;
    }
    /**
     * @return a list of collidabels.
     */
    public  List<Collidable> getCollidables() {
        return (this.collidables);
    }

}