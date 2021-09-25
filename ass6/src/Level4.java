import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com][209107234]
 * @version 4
 * @since 2021-06-17
 */
public class Level4 implements LevelInformation {

    private int screenWidth;
    private int screenHeight;
    private int numberOfBalls;
    private int paddleSpeed;
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
    public Level4(int screenWidth, int screenHeight, GUI newGui) {
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
        this.numberOfBalls = 3;
        this.ballsRadius = 5;
        this.ballsColor = Color.white;
        this.createBalls();


        // paddle
        this.paddleWidth = 80;
        this.paddleSpeed = 3;

        // removableBlocks
        Color[] colors = {Color.darkGray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        int rows = 7;
        int blocksWidth = 37;
        int blockHeight = 25;
        int blocksInRow = (screenWidth - (2 * edgesWidth)) / blocksWidth;
        int x;
        int y = 100;
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
        Velocity v1, v2;
        int i;
        for (i = 0; i < this.numberOfBalls; i++) {
            if (i % 2 == 0) {
                v1 = Velocity.fromAngleAndSpeed(-4 * ((i + 1) * 2), 8);
                ballsVelocity.add(v1);
            } else {
                v2 = Velocity.fromAngleAndSpeed(4 * ((i + 1) * 2), 8);
                ballsVelocity.add(v2);
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
     * this function draws the background on the drawSurface.
     * @param d - the drawSurface.
     * */
    public void drawBackground(DrawSurface d) {
        //rectangle
        List<Rectangle> rectangles =  new ArrayList<>();

        // circles
        List<Circle> circles = new ArrayList<>();
        int r = 10;
        int x1 = 600;
        int y = screenHeight;
        Point center = new Point(x1 + 110, screenHeight * 0.67);
        Circle c1 = new Circle(new Point(x1, screenHeight * 0.67), 40, Color.lightGray, true);
        Circle c2 = new Circle(new Point(x1 + 60, screenHeight * 0.6), 45, Color.lightGray, true);
        Circle c3 = new Circle(new Point(x1 + 20, screenHeight * 0.61), 49, Color.lightGray, true);
        Circle c4 = new Circle(new Point(x1 + 65, screenHeight * 0.64), 49, Color.lightGray, true);
        Circle c5 = new Circle(new Point(x1 + 110, screenHeight * 0.65), 45, Color.lightGray, true);
        Circle c6 = new Circle(new Point(x1 + 100, screenHeight * 0.6), 43, Color.lightGray, true);
        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        circles.add(c4);
        circles.add(c5);
        circles.add(c6);

        //lines
        List<Line> lines = new ArrayList<>();
        int linesLength = 180;
        for (int i = 1; i < 15; i++) {
            Line l = new Line(center.getX() - (i * 4), center.getY(), center.getX() - (i * 20),
                    center.getY() + linesLength, Color.white);
            lines.add(l);
        }

        this.backgroundDrawings = new Background(lines, circles,  rectangles);
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
        return Color.blue;
    }
}
