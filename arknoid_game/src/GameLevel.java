import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author [marah mahajne][marahmahajne68@gmail.com]
 * @version 2
 * @since 2021-06-06
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter livesNum;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private int screenWidth = 800;
    private int screenHeight = 600;
    private CountdownAnimation countDA;
    private LevelInformation levelInfo;
    private Paddle paddle;
    private boolean levelRunning;

    /**
     * This is an instructor for class "Game".
     * <p>
     * the instructor creates a new game.
     * </p>
     * @param keyboard - the keyboard.
     * @param gui - the gui.
     * @param levelInfo - the information about this level.
     * @param livesNum - our chances to lose and play.
     * @param runner - the animation runner.
     * @param score - the score of the game.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner runner, KeyboardSensor keyboard,
                     Counter score, Counter livesNum, GUI gui) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = score;
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInfo = levelInfo;
        this.livesNum = livesNum;
    }

    /**
     * This function adds the given collidable to the list of the collidables.
     *
     * @param c - a collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This function removes the given collidable from the list of the collidables.
     *
     * @param c - a collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This function adds the given sprite to the list of the sprites.
     *
     * @param s - a sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This function removes the given sprite from the list of the sprites.
     *
     * @param s - a sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This function initialize a new game.
     * <p>
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     * </p>
     */
    public void initialize() {
        //listeners
        BlockRemover listener1 = new BlockRemover(this, this.remainingBlocks);
        BallRemover listener2 = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener listener3 = new ScoreTrackingListener(this.score);

        //background
        Rectangle rc = new Rectangle(new Point(0, 0), this.screenWidth, this.screenHeight);
        Block background = new Block(rc, this.levelInfo.getBackgroundColor());
        background.addToGame(this);
        Background b = (Background) this.levelInfo.getBackground();
        if (b != null) {
            b.addToGame(this);
        }


        // edges
        int edgesThickness = 30;
        Color edgesColor = Color.gray;
        int scoreBlockHeight = (int) edgesThickness;
        this.edges(edgesColor, scoreBlockHeight, listener2, edgesThickness);

        // scoreBlock
        Point point = new Point(0, 0);
        Rectangle rectangle = new Rectangle(point, screenWidth, edgesThickness);
        Block scoreBlock = new Block(rectangle, Color.white);
        scoreBlock.addToGame(this);
        ScoreIndicator sIn = new ScoreIndicator(this.score, screenWidth, edgesThickness);
        LivesIndicator livesIndicator = new LivesIndicator(this.livesNum, screenWidth, edgesThickness);
        LevelNameIndicator name = new LevelNameIndicator(this.levelInfo.levelName(), screenWidth, edgesThickness);
        sIn.addToGame(this);
        livesIndicator.addToGame(this);
        name.addToGame(this);

        //paddle
        int paddleWidth = this.levelInfo.paddleWidth();
        int paddleHeight = 20;
        int paddleSpeed = this.levelInfo.paddleSpeed();
        Point paddleUpperLeft = new Point((screenWidth - (2 * edgesThickness)) / 2 - paddleWidth / 4,
                screenHeight - edgesThickness - paddleHeight);
        Rectangle recPaddle = new Rectangle(paddleUpperLeft, paddleWidth, paddleHeight);
        this.paddle = new Paddle(this.gui, recPaddle, Color.yellow);
        this.paddle.addToGame(this);
        //ballS
        createBalls();

        // removable blocks
        List<Block> blocks = new ArrayList<>(this.levelInfo.blocks());
        for (int i = 0; i < blocks.size(); i++) {
                blocks.get(i).addToGame(this);
                blocks.get(i).addHitListener(listener1);
                blocks.get(i).addHitListener(listener2);
                blocks.get(i).addHitListener(listener3);
        }
        this.remainingBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
    }

    /**
     * this function is for creating the edges for our game.
     * @param color - the color of the edges.
     * @param listener2 - ballremover listener.
     * @param scoreBlockHeight - the upper edge that includes the score.
     * @param edgeThickness - the thickness of the edges.
     * */
    private void edges(java.awt.Color color, double scoreBlockHeight,
                       BallRemover listener2, int edgeThickness) {
        Block[] edges = new Block[4];
        edges[0] = new Block(new Rectangle(new Point(0, scoreBlockHeight), screenWidth, edgeThickness),
                Color.gray);
        edges[1] = new Block(new Rectangle(new Point(edgeThickness, screenHeight - 10),
                screenWidth - (edgeThickness * 2), 10), Color.red);
        edges[1].setFlag(true);
        edges[2] = new Block(new Rectangle(new Point(0, edgeThickness + scoreBlockHeight), edgeThickness,
                screenHeight - edgeThickness - scoreBlockHeight), Color.gray);
        edges[3] = new Block(new Rectangle(new Point(screenWidth - edgeThickness,
                edgeThickness + scoreBlockHeight), edgeThickness, screenHeight - edgeThickness - scoreBlockHeight),
                Color.gray);
        for (int i = 0; i < edges.length; i++) {
            edges[i].addHitListener(listener2);
            edges[i].addToGame(this);
        }
    }

    /**
     * This function runs the  game.
     */
    public void run() {
        this.countDA = new CountdownAnimation(2, 3, this.sprites, screenWidth, screenHeight);
        this.runner.run(countDA);
        this.levelRunning = true;
        this.runner.run(this);
        System.out.println(this.levelRunning);
    }
    /**
     * this function creates the balls in the game.
     * */
    public void createBalls() {
        int ballsNum = this.levelInfo.numberOfBalls();
        this.remainingBalls.increase(ballsNum);
        this.levelInfo.createBalls();
        List<Ball> balls = this.levelInfo.getBalls();
        List<Velocity> ballsVelocity = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            System.out.println(ballsVelocity);
            balls.get(i).setVelocity(ballsVelocity.get(i));
            balls.get(i).addToGame(this);
            balls.get(i).setEnv(this.environment);
        }
    }

    /**
     * sets the remained block counter to the new value.
     *
     * @param newRemainingBlocks - a counter of the remained blocks.
     */
    public void setRemainingBlocks(Counter newRemainingBlocks) {
        this.remainingBlocks = newRemainingBlocks;
    }

    /**
     * @return the counter of the remaining blocks in the game.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * this function is to set the remainingBalls value.
     * @param newRemainedBalls - a counter of the remained balls.
     */
    public void setRemainingBalls(Counter newRemainedBalls) {
        this.remainingBalls = newRemainedBalls;
    }
    /**
     * @return the number of lives remaining.
     * */
    public Counter getLivesNum() {
        return this.livesNum;
    }
    /**
     * @return the score.
     * */
    public Counter getScore() {
        return this.score;
    }

    /**
     * @return true if the game should stop and false if the game shouldn't.
     * */
    public boolean shouldStop() {
        return !this.levelRunning;
    }
    /**
     * this function changes the levelRunning value.
     * @param value - the new value of the levelRunning field.
     * */
    public void setRunning(boolean value) {
        this.levelRunning = value;
    }

    /**
     * this function does a frame of the gamePlay.
     * @param d - the drawSurface.
     * */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.levelRunning = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.livesNum.decrease(1);
            if (this.getLivesNum().getValue() == -1) {
                this.levelRunning = false;
            } else {
                createBalls();
                this.countDA = new CountdownAnimation(2, 3, this.sprites, screenWidth, screenHeight);
                this.runner.run(countDA);
            }
        }
            if (this.keyboard.isPressed("p")) {
                this.runner.run(new PauseScreen(this.keyboard));
            }

    }
}