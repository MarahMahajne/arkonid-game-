import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-17
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * constructor.
     * @param keyboard - the KeyboardSensor.
     */
    public PauseScreen(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.stop = false;
    }
    /**
     * constructor.
     * @param d - the drawingSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**
     * @return true if the loop should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}