package game;

// Ofir Sasoni
// 325690386

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import core.Collidable;
import core.Counter;
import core.Sprite;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Paddle;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listenersRemovers.BallRemover;
import listenersRemovers.BlockRemover;
import listenersRemovers.HitListener;
import listenersRemovers.ScoreTrackingListener;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The class represents and runs the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score = new Counter(0);
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private ScoreIndicator scoreIndicator;
    private boolean lastLevel;
    private KeyboardSensor keyboard;

    private static final Rectangle LEFT_BOARD = new Rectangle(new Point(0, 0), 25, 600);
    private static final Rectangle RIGHT_BOARD = new Rectangle(new Point(770, 0), 30, 600);
    private static final Rectangle UPPER_BOARD = new Rectangle(new Point(0, 30), 800, 30);
    private static final Rectangle DEATH_BLOCK = new Rectangle(new Point(0, 615), 800, 30);
    private static final double EPSILON = Math.pow(10, -10);

    /**
     * Constructor.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection(new ArrayList<Sprite>());
        this.environment = new GameEnvironment(new ArrayList<Collidable>());
    }

    /**
     * Constructor.
     * @param sprites sprites collection.
     * @param environment game environment.
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
        remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        remainingBalls = new Counter(levelInformation.numberOfBalls());
    }

    /**
     * Constructor.
     * @param sprites sprites collection.
     * @param environment game environment.
     * @param levelInformation the information about the level of the game.
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment, LevelInformation levelInformation) {
        this.sprites = sprites;
        this.environment = environment;
        this.levelInformation = levelInformation;
        remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        remainingBalls = new Counter(levelInformation.numberOfBalls());
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * Constructor.
     * @param levelInformation the information about the game level.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
        this.environment = new GameEnvironment(new ArrayList<Collidable>());
        this.sprites = new SpriteCollection(new ArrayList<Sprite>());
        remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        remainingBalls = new Counter(levelInformation.numberOfBalls());
    }

    /**
     * Constructor.
     * @param levelInformation the information about the game level.
     * @param score the current score of the game.
     * @param lastLevel true if this is the last level in "GameFlow", false otherwise.
     */
    public GameLevel(LevelInformation levelInformation, Counter score, boolean lastLevel) {
        this.levelInformation = levelInformation;
        this.environment = new GameEnvironment(new ArrayList<Collidable>());
        this.sprites = new SpriteCollection(new ArrayList<Sprite>());
        remainingBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        remainingBalls = new Counter(levelInformation.numberOfBalls());
        this.score = score;
        this.lastLevel = lastLevel;
    }

    /**
     * Get method.
     * @return remaining blocks counter.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * The function adds a given collidable object to the game environment.
     * @param c given collidable to be added to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The function removes all the appearances of given collidable from the game environment.
     * @param c collidable object we want to remove from game environment.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * The function removes all the appearances of given sprite from the game environment.
     * @param s sprite object we want to remove from game environment.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * The function adds a sprite to the sprite collection.
     * @param s given sprite to be added to the sprite collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Set method.
     * @param gameEnvironment new game environment.
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * Set method.
     * @param sprites new sprite collection.
     */
    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
    }

    /**
     * The function adds the boarders to the game.
     */
    private void addBoarders() {
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ArrayList<HitListener> ballRemoverList = new ArrayList<>();
        ballRemoverList.add(ballRemover);

        Block leftBlock = new Block(LEFT_BOARD, Color.gray);
        Block rightBlock = new Block(RIGHT_BOARD, Color.gray);
        Block upperBlock = new Block(UPPER_BOARD, Color.gray);
        Block deathBlock = new Block(DEATH_BLOCK, Color.gray, ballRemoverList);

        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
        upperBlock.addToGame(this);
        deathBlock.addToGame(this);
    }

    /**
     * The function gets the line number of the stair, and returns the appropriate line color.
     * @param i the line of the stair.
     * @return the color of the given line.
     */
    private java.awt.Color setColor(int i) {
        if (i == 0) {
            return Color.green;
        }
        if (i == 1) {
            return Color.pink;
        }
        if (i == 2) {
            return Color.cyan;
        }
        if (i == 3) {
            return Color.yellow;
        }
        if (i == 4) {
            return Color.red;
        }
        return Color.magenta;
    }

    /**
     * The function adds the stairs to the game, using all the above functions.
     */
    private void addStairs() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 6; j++) {

                Color color = this.setColor(i);
                Rectangle rectangle = new Rectangle(new Point(723 - 47 * j, 225 - 25 * i), 47, 25);

                ArrayList<HitListener> blockRemoverList = new ArrayList<>();
                blockRemoverList.add(blockRemover);
                blockRemoverList.add(scoreTrackingListener);
                Block block = new Block(rectangle, color, blockRemoverList);
                block.addToGame(this);
            }
        }
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        for (Ball ball : this.levelInformation.balls()) {
            ball.addToGame(this);
        }
        this.addBoarders();
        this.addStairs();
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     * @param gui the gui of the game.
     */
    public void initialize(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        for (Sprite sprite : this.levelInformation.background()) {
            this.addSprite(sprite);
        }
        for (Ball ball : this.levelInformation.balls()) {
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        for (Block block : this.levelInformation.blocks()) {
            ArrayList<HitListener> blockRemoverList = new ArrayList<>();
            blockRemoverList.add(blockRemover);
            blockRemoverList.add(scoreTrackingListener);
            block.setHitListeners(blockRemoverList);
            block.addToGame(this);
        }
        this.addBoarders();
        this.createPaddle(gui);
        ScoreIndicator scoreIndicator = new ScoreIndicator(new ScoreTrackingListener(score), gui.getDrawSurface());
        this.addSprite(scoreIndicator);
    }

    /**
     * The function creates the paddle using the keyboard sensor of the given GUI.
     * @param gui given GUI that we will take the keyboard sensor from.
     */
    public void createPaddle(GUI gui) {
//        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        this.keyboard = gui.getKeyboardSensor();
        Rectangle paddleRectangle = this.levelInformation.paddleRectangle();
        int paddleVelocity = levelInformation.paddleSpeed();
        Paddle paddle = new Paddle(keyboard, paddleRectangle, Color.orange, paddleVelocity);
        paddle.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return remainingBlocks.getValue() == 0 || remainingBalls.getValue() == 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.setFirstRun(false);
            this.runner.run(new PauseScreen(this.keyboard));
        }
        d.setColor(this.levelInformation.getBackgroundColor());
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);

        this.sprites.removeSprite(scoreIndicator);
        scoreIndicator = new ScoreIndicator(new ScoreTrackingListener(score), d);
        this.addSprite(scoreIndicator);
        d.drawText(550, 25, "Level Name: " + levelInformation.levelName(), 15);
    }

    @Override
    public void show(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        d.setColor(this.levelInformation.getBackgroundColor());
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        this.levelInformation.background();

        this.sprites.removeSprite(scoreIndicator);
        scoreIndicator = new ScoreIndicator(new ScoreTrackingListener(score), d);
        this.addSprite(scoreIndicator);
        d.drawText(550, 25, "Level Name: " + levelInformation.levelName(), 15);
    }

    /**
     * The function runs the game.
     */
    public void run() {
        GUI gui = new GUI("game", 800, 600);
        Sleeper sleeper = new Sleeper();
        runner = new AnimationRunner(gui, sleeper, remainingBlocks, remainingBalls, score, lastLevel);


        this.initialize(gui);

        runner.run(this);
    }
}