import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-21-06
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean finished;
    private boolean pressed;
    /**
     * Constructor.
     * @param sensor - key board sensor.
     * @param key       : key to stop the animation.
     * @param animation : animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard  = sensor;
        this.key = key;
        this.animation = animation;
        this.pressed = true;
    }

    /**
     * draw the animation.
     * @param d - given surface.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!this.pressed) {
                this.finished = true;
            }
        }
        if (!this.keyboard.isPressed(this.key)) {
            this.pressed = false;
        }
    }
    /**
     * @return true if the key is pressed false other wise.
     */
    public boolean shouldStop() {
        if (this.finished) {
            this.finished = false;
            return true;
        }
        return false;
    }
}