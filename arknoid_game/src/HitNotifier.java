/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 2
 * @since 2021-06-06
 */
public interface HitNotifier {
    /**
     * adds hl as a listener to the list of hitListeners.
     * @param hl - a hitListener.
     */
    void addHitListener(HitListener hl);
    /**
     * removes hl as a listener to the list of hitListeners.
     * @param hl - a hitListener.
     */
    void removeHitListener(HitListener hl);
}
