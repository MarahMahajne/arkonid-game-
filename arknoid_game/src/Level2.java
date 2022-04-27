import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 4
 * @since 2021-06-21
 */
public class Level2 implements LevelInformation {
    private int remainingBlocks;
    private Sprite backgroundDrawings;
    private int ballsRadius;
    private Color ballsColor;
    private GUI gui;
    private int screenWidth;
    private int screenHeight;
    private int numberOfBalls;
    private int paddleSpeed;
    private Point paddleUpperLeft;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private List<Ball> balls;
    /**
     * Constructor.
     * @param newGui - the gui.
     * @param screenWidth  -  width f the screen.
     * @param screenHeight - height of the screen.
     */
    public Level2(int screenWidth, int screenHeight, GUI newGui) {
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

        this.levelName = "Wide Easy";
        int edgesWidth = 30;

        //balls
        this.numberOfBalls = 10;
        this.ballsRadius = 5;
        this.ballsColor = Color.black;
        this.createBalls();


        // paddle
        int paddleHeight = 20;
        this.paddleWidth = screenWidth - 300;
        this.paddleSpeed = 3;
        this.paddleUpperLeft = new Point((screenWidth -  (2  * edgesWidth)) / 2 - paddleWidth / 2,
                screenHeight - edgesWidth - paddleHeight);

        // removableBlocks
        int removableBlocks = 0;
        int blocksNum = 16;
        double  blocksWidth = (screenWidth - (2 * edgesWidth)) / blocksNum;
        int blocksHeight = 20;
        Rectangle rBlock;
        Block block;
        Point point;
        List<Color> blockColors = Arrays.asList(Color.red, Color.orange,  Color.yellow, Color.green, Color.blue,
                Color.magenta, Color.cyan, Color.orange);
        Iterator<Color> itr  = blockColors.listIterator();
        Color color = itr.next();
        int c = 0;
        for (int i = 0; i < blocksNum; i++) {
            if (c == 2) {
                if (itr.hasNext()) {
                    color = itr.next();
                    c = 0;
                }
            }
                point = new Point(edgesWidth + (i * blocksWidth), screenHeight / 2);
                rBlock = new Rectangle(point, blocksWidth, blocksHeight);
                block = new Block(rBlock, color);
                removableBlocks++;
                this.blocks.add(block);
                c++;
        }
        this.remainingBlocks = removableBlocks;

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
     * this function draws the background on the drawSurface.
     * @param d - the drawSurface.
     * */
    public void drawBackground(DrawSurface d) {
        //rectangle
        List<Rectangle> rectangles =  new ArrayList<>();

        // circles
        List<Circle> circles = new ArrayList<>();
        int r = 80;
        Point center = new Point(160, 160);
        Circle c1 = new Circle(center, 80, Color.orange, true);
        Circle c2 = new Circle(center, 60, Color.yellow,  true);
        circles.add(c1);
        circles.add(c2);

        //lines
        List<Line> lines = new ArrayList<>();
        int linesLength = 150;
        for (int i = -40; i < 100; i++) {
            Line l = new Line(center.getX(), center.getY(), center.getX() + (i * 5),
                    center.getY() + linesLength, Color.yellow);
            lines.add(l);
        }
        this.backgroundDrawings = new Background(lines, circles,  rectangles);
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
        return Color.white;
    }

    /**
     * @return - paddle upper left point.
     */
    public Point paddleUpperLeft() {
        return this.paddleUpperLeft;
    }
}
