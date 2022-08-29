package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import core.Counter;

import java.awt.Color;

/**
 * The class represents an object which runs the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private final int framesPerSecond;
    private Sleeper sleeper;
//    private boolean running;
    private Counter blocks;
    private Counter balls;
    private Counter score;
    private boolean lastLevel;
    private boolean firstRun;

    /**
     * Constructor.
     * @param gui the gui we run the animation at.
     * @param sleeper the sleeper.
     * @param blocks remaining blocks' counter.
     * @param balls remaining balls' counter.
     * @param score score counter.
     */
    public AnimationRunner(GUI gui, Sleeper sleeper, Counter blocks, Counter balls, Counter score) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = sleeper;
        this.balls = balls;
        this.blocks = blocks;
        this.score = score;
        this.lastLevel = false;
        this.firstRun = true;
    }

    /**
     * Constructor.
     * @param gui the gui we run the animation at.
     * @param sleeper the sleeper.
     * @param blocks remaining blocks' counter.
     * @param balls remaining balls' counter.
     * @param score score counter.
     * @param lastLevel true if the level is the last one in the game flow, false otherwise.
     */
    public AnimationRunner(GUI gui, Sleeper sleeper, Counter blocks, Counter balls, Counter score, boolean lastLevel) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = sleeper;
        this.balls = balls;
        this.blocks = blocks;
        this.score = score;
        this.lastLevel = lastLevel;
        this.firstRun = true;
    }

    /**
     * Set method.
     * @param firstRun true if this is the first run of the animation, false otherwise.
     */
    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    /**
     * The method runs the given animation.
     * @param animation the animation we want to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;


        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d;
            Color navyBlue = new Color(32, 42, 68);

            if (firstRun) {
                int count = 3;
                while (count > 0) {
                    d = gui.getDrawSurface();
                    animation.show(d);
                    d.setColor(navyBlue);
                    d.drawText(d.getWidth() / 2 - 35, d.getHeight() / 2 + 30, Integer.toString(count), 100);
                    gui.show(d);
                    sleeper.sleepFor(1000);
                    count -= 1;
                }
                firstRun = false;
            } else {
                d = gui.getDrawSurface();
                animation.doOneFrame(d);

                gui.show(d);

                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    this.sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        }

        if (blocks.getValue() == 0 && lastLevel) {
            while (!gui.getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY)) {
                DrawSurface d = gui.getDrawSurface();
                d.drawText(50, 300, "You Win! Your score is " + score.getValue(), 50);
                gui.show(d);
                sleeper.sleepFor(5);
            }
            gui.close();
        }

        if (balls.getValue() == 0) {
            while (!gui.getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY)) {
                DrawSurface d = gui.getDrawSurface();
                d.drawText(50, 300, "Game Over! Your score is " + score.getValue(), 50);
                gui.show(d);
                sleeper.sleepFor(5);
            }
            gui.close();
        }
    }
}