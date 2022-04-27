import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 1
 * @since 2021-06-21
 */
public class Ass6Game {
     /**
     * The main function is responsible of controlling the game.
     * <p>
     *  the main function calls all the other function in the right order.
     * <p>
     * @param args - ignored.
     */
  public static void main(String[] args) {
      int screenWidth = 800;
      int screenHeight = 600;
      GUI gui = new GUI("Arkanoid Game", screenWidth, screenHeight);
      biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
      AnimationRunner runner = new AnimationRunner(gui);
      GameFlow flow = new GameFlow(runner, keyboard, gui);
      List<LevelInformation> levels = new ArrayList<>();
      if (args.length > 1) {
          for (int i = 0; i < args.length; i++) {
              if (args[i].equals("1")) {
                  levels.add(new Level1(screenWidth, screenHeight, gui));
              } else if (args[i].equals("2")) {
                  levels.add(new Level2(screenWidth, screenHeight, gui));
              } else if (args[i].equals("3")) {
                  levels.add(new Level3(screenWidth, screenHeight, gui));
              } else if (args[i].equals("4")) {
                 levels.add(new Level4(screenWidth, screenHeight, gui));
              }
          }
      } else {
          levels.add(new Level1(screenWidth, screenHeight, gui));
          levels.add(new Level2(screenWidth, screenHeight, gui));
          levels.add(new Level3(screenWidth, screenHeight, gui));
          levels.add(new Level4(screenWidth, screenHeight, gui));
      }
      flow.runLevels(levels);
    }
}


