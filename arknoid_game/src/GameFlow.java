import biuoop.GUI;
import java.util.List;
import biuoop.KeyboardSensor;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version
 * @since 2021-06-17
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private Counter score;
    private Counter livesNum;
    /**
     * Constructor.
     * @param runner - animation runner.
     * @param keyboard - the keyboard.
     * @param gui - the gui.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard, GUI gui) {
        this.score = new Counter();
        this.livesNum = new Counter();
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
        this.livesNum = new Counter();
        this.livesNum.increase(7);
        this.score.increase(0);
    }

    /**
     * runs the levels.
     * @param levels - list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int levelNum = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner, this.keyboard, this.score, this.livesNum, this.gui);
            level.initialize();
            while (level.getRemainingBlocks().getValue() > 0 && level.getLivesNum().getValue() >= 0) {
                level.run();
                levelNum++;
            }
            if (level.getLivesNum().getValue() == -1) {
                 EndScreen lose = new EndScreen(level.getScore().getValue());
                 this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", lose));
            } else {
                if (levelNum == levels.size()) {
                    VictoryScreen win = new VictoryScreen(level.getScore().getValue());
                    this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", win));
                }
            }
        }
        while (true) {
            if (this.keyboard.isPressed(keyboard.SPACE_KEY)) {
                this.gui.close();
            }
        }
    }
}

