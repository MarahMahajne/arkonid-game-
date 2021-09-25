import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-17
 */
public class AnimationRunner {
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private int framesPerSecond;
    /**
     * Constructor.
     *<p>
     *   creates a new Animation Runner object.
     *</p>
     * @param gui - gui.
     */
    public AnimationRunner(GUI gui) {
        this.sleeper = new biuoop.Sleeper();
        this.gui = gui;
        this.framesPerSecond = 60;
    }
    /**
     * loop that runs the animation .
     * @param animation - animation that should  run.
     */
    public void run(Animation animation) {
        do {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = (1000 / framesPerSecond) - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        } while (!animation.shouldStop());
    }
}

