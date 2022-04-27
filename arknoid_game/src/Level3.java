import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 4
 * @since 2021-06-21
 */
public class Level3 implements LevelInformation {

    private int screenWidth;
    private int screenHeight;
    private int numberOfBalls;
    private int paddleSpeed;
    private Point paddleUpperLeft;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private List<Ball> balls;
    private int remainingBlocks;
    private int ballsRadius;
    private Color ballsColor;
    private GUI gui;
    private Sprite backgroundDrawings;
    /**
     * Constructor.
     * @param newGui - the gui.
     * @param screenWidth  -  width f the screen.
     * @param screenHeight - height of the screen.
     */
    public Level3(int screenWidth, int screenHeight, GUI newGui) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.blocks = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.gui = newGui;
        this.initialize();
    }
    /**
     * initialize all the sprites and background of the level.
     */
    private void initialize() {

        this.levelName = "Green 3";
        int edgesWidth = 30;

        //balls
        this.numberOfBalls = 2;
        this.ballsRadius = 5;
        this.ballsColor = Color.white;
        this.createBalls();


        // paddle
        this.paddleWidth = 80;
        this.paddleSpeed = 3;

        // removableBlocks
        Color[] colors = {Color.darkGray, Color.red, Color.yellow, Color.blue, Color.white};
        int rows = 5;
        int blocksInRow = 10;
        int blocksWidth = 60;
        int blockHeight = 25;
        int x;
        int y = 200;
        int i, j;
        for (i = 0; i < rows; i++) {
            x = screenWidth - edgesWidth;
            for (j = 0; j < blocksInRow; j++) {
                x -= blocksWidth;
                Point p = new Point(x, y);
                Rectangle r = new Rectangle(p, blocksWidth, blockHeight);
                Block block = new Block(r, colors[i]);
                this.blocks.add(block);
                this.remainingBlocks++;
            }
            y += blockHeight;
            blocksInRow -= 1;
        }
        //drawBackground
        drawBackground(this.gui.getDrawSurface());
    }
    /**
     * @return -  number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * @return - balls initial velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls; i++) {
            if (i % 2 == 0) {
                ballsVelocity.add(Velocity.fromAngleAndSpeed(-4 * ((i + 1) * 2), 8));
            } else {
                ballsVelocity.add(Velocity.fromAngleAndSpeed(4 * ((i + 1) * 2), 8));
            }
        }
        return ballsVelocity;
    }
    /**
     * this function creates the balls of the level.
     * */
    public void createBalls() {
        this.balls = new ArrayList<Ball>();
        int i;
        Ball ball;
        Point center = new Point(this.screenWidth / 2, 500);
        for (i = 0; i < this.numberOfBalls; i++) {
            ball = new Ball(center, this.ballsRadius, this.ballsColor);
            this.balls.add(ball);
        }
    }
    /**
     * @return - paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return - paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return - level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return - the back grounf of the level.
     */
    public Sprite getBackground() {
        return this.backgroundDrawings;
    }

    /**
     * @return - list of the blocks in the game.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return - number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.remainingBlocks;
    }
    /**
     * @return balls in the level.
     */
    public List<Ball> getBalls() {
        return this.balls;
    }
    /**
     * @return the background color.
     * */
    public Color getBackgroundColor() {
        return Color.green;
    }
    /**
     * this function draws the background on the drawSurface.
     * @param d - the drawSurface.
     * */
    public void drawBackground(DrawSurface d) {
        //rectangle
        List<Rectangle> rectangles =  new ArrayList<>();
        Rectangle r1 = new Rectangle(new Point(100, 450), 100, 150, Color.darkGray, true);
        Rectangle r2 = new Rectangle(new Point(125, 450 - 80), 50, 80, Color.gray, true);
        Rectangle r3 = new Rectangle(new Point(145, 450 - 280), 10, 200, Color.gray, true);
        rectangles.add(r1);
        rectangles.add(r2);
        rectangles.add(r3);
        int rWidth = 5;
        int rHeight = 10;
        int x = 105;
        int y = 455;
        int columns = (int) (r1.getWidth() / rWidth) - 12;
        int rows = (int) (r1.getHeight() / rHeight);
        for (int i = 0; i < rows; i++) {
            y +=  rHeight;
            for (int j = 0; j < columns; j++) {
                x += rWidth;
                Point p = new Point(x, y);
                Rectangle r = new Rectangle(p, rWidth, rHeight, Color.white, true);
                rectangles.add(r);
                x += 6;
            }
            x = 105;
            y +=  6;
        }

        // circles
        List<Circle> circles = new ArrayList<>();
        int r = 10;
        Point center = new Point(150, 450 - 280 - 10);
        Circle c1 = new Circle(center, r, Color.white, true);
        circles.add(c1);

        //lines
        List<Line> lines = new ArrayList<>();

        this.backgroundDrawings = new Background(lines, circles,  rectangles);
    }
}
