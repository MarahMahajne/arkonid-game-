import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-06
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * This is an instructor for class "SpriteCollection".
     * <p>
     *  the instructor initializes a new list of sprites.
     * <p>
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * This function adds a sprite to the sprites list.
     * @param  s - a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * This function removes a sprite from the sprites list.
     * @param  s - a sprite.
     */
    public void removeSprite(Sprite s) {
        try {
            this.sprites.remove(s);
        } catch (Exception e) {
            System.out.println("this sprite is not in the collection!");
        }
    }
    /**
     * This function call timePassed() on all sprites in the list.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = (Sprite) sprites.get(i);
            sprite.timePassed();
        }
    }
    /**
     * This function call drawOn(d) on all sprites in the list.
     * @param d - the drawing surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
