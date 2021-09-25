import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 1
 * @since 2021-06-17
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int screenWidth;
    private int screenHeight;
    private long time;
    private long timeForEachNum;
    private long end;
    private int countDown;
    private boolean flag = false;
    /**
     * Constructor.
     *
     * @param numOfSeconds - the count down time.
     * @param countFrom - the start of the countdown;
     * @param gameScreen - game screen.
     * @param screenWidth - screen width.
     * @param screenHeight - screen height.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, int screenWidth,
                              int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.countDown = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }
    /**
     * @param d - given surface.
     */
    public void doOneFrame(DrawSurface d) {
        if (!flag) {
            this.time = System.currentTimeMillis();
            this.timeForEachNum = (long) (this.numOfSeconds * 1000) / (this.countFrom + 1);
            this.end = time + (long) (this.numOfSeconds * 1000);
            this.flag = true;
        }
        if (System.currentTimeMillis() >= time + (timeForEachNum * (countFrom - countDown + 1))) {
            if (this.countDown != 0) {
                this.countDown--;
            }
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.orange);
        String s = Integer.toString(countDown);
        d.drawText((screenWidth - 60) / 2,  (screenHeight) - 200, s, 60);
    }
    /**
     * @return true if we should stop the loop
     */
    public boolean shouldStop() {
        if (System.currentTimeMillis() >= end) {
            return true;
        }
        return false;
    }
}