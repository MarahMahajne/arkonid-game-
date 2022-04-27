import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 4
 * @since 2021-06-17
 */
public class Level1 implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private Point paddleUpperLeft;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private int remainingBlocks;
    private Sprite backgroundDrawings;
    private Color ballsColor;
    private int ballsRadius;
    private List<Ball> balls;
    private GUI gui;
    private int screenWidth;
    private int screenHeight;
    /**
     * Constructor.
     *
     * @param screenWidth  -  width of the screen.
     * @param screenHeight -  height of the screen.
     * @param gui - the gui.
     */
    public Level1(int screenWidth, int screenHeight, GUI gui) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.blocks = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.gui = gui;
        this.initialize();
}
    /**
     * initialize and draw  all bodies of the level.
     */
    private void initialize() {
        this.levelName = "Direct Hit";

       //paddle
        this.paddleWidth = (screenWidth / 8);
        this.paddleSpeed = 6;
        // removable Blocks
        int edgeWidth = 30;
        Point p = new Point((screenWidth - (2 * edgeWidth)) / 2 + 15, screenHeight / 3);
        Rectangle rBlock = new Rectangle(p, 40, 40);
        Block block = new Block(rBlock, Color.red);
        this.blocks.add(block);
        this.remainingBlocks = 1;

        //drawBackground
        drawBackground(this.gui.getDrawSurface());

        //balls
        this.numberOfBalls = 1;
        this.ballsRadius = 5;
        this.ballsColor = Color.white;
        createBalls();
    }

    /**
     * @return - balls number.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * @return - balls initial velocity.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (Ball ball : this.balls) {
            Velocity v = new Velocity(0, -7);
            velocities.add(v);
        }
        return velocities;
    }
    /**
     * this function creates the balls in the game.
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
     * @return the list of balls.
     * */
    public List<Ball> getBalls() {
        return this.balls;
    }
    /**
     * this function draws the background on the drawSurface.
     * @param d - the drawSurface.
     * */
    public void drawBackground(DrawSurface d) {
        List<Circle> circles =  new ArrayList<>();
        List<Rectangle> rectangles =  new ArrayList<>();
        //lines
        List<Line> lines =  new ArrayList<>();;
        int linesLength = 120;
        Rectangle rec = this.blocks.get(0).getRectangle();
        Line l1 = new Line(rec.getLup().middle().getX(), rec.getLup().middle().getY(), rec.getLup().middle().getX(),
                rec.getLup().middle().getY() - linesLength, Color.blue);

        Line l2 = new Line(rec.getLdown().middle().getX(), rec.getLdown().middle().getY(),
                rec.getLdown().middle().getX(), rec.getLdown().middle().getY() + linesLength, Color.blue);

        Line l3 = new Line(rec.getLright().middle().getX(), rec.getLright().middle().getY(),
                rec.getLright().middle().getX() + linesLength, rec.getLright().middle().getY(), Color.blue);

        Line l4 = new Line(rec.getLleft().middle().getX(), rec.getLleft().middle().getY(),
                rec.getLleft().middle().getX() - linesLength, rec.getLleft().middle().getY(), Color.blue);

        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        lines.add(l4);

        // circles
        Point p = new Point(rec.getLup().middle().getX(), rec.getLright().middle().getY());
        Circle c1 = new Circle(p, 50, Color.blue, false);
        Circle c2 = new Circle(p, 90, Color.blue, false);
        Circle c3 = new Circle(p, 120, Color.blue, false);
        circles.add(c1);
        circles.add(c2);
        circles.add(c3);

        this.backgroundDrawings =  new Background(lines,  circles, rectangles);
    }
    /**
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * @return level name.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * @return level's background.
     */
    public Sprite getBackground() {
        return this.backgroundDrawings;
    }
    /**
     * @return the backGround color.
     * */
    public Color getBackgroundColor() {
        return Color.black;
    }
    /**
     * @return the blocks value.
     * */
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * @return the number of blocks to remove.
     * */
    public int numberOfBlocksToRemove() {
        return this.remainingBlocks;
    }
    /**
     * @return the paddleUpperLeft value.
     * */
    public Point getPaddleUpperLeft() {
        return this.paddleUpperLeft;
    }


}
