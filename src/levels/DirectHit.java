package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Ball;
import gameObjects.Block;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the "Direct hit" game level.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public Rectangle paddleRectangle() {
        double x = 325 - (double) (paddleWidth() / 2);
        return new Rectangle(new Point(x, 570), paddleWidth(), 15);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        Velocity velocity = new Velocity(3, 6);
        velocities.add(velocity);

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        ArrayList<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(51, 300, 4, Color.white, initialBallVelocities().get(0));
        balls.add(ball);

        return balls;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public ArrayList<Sprite> background() {
        ArrayList<Sprite> sprites = new ArrayList<>();

        sprites.add(new Circle(395, 200, 60, Color.blue, false));
        sprites.add(new Circle(395, 200, 100, Color.blue, false));
        sprites.add(new Circle(395, 200, 140, Color.blue, false));

        sprites.add(new Line(200, 200, 370, 200, Color.blue));
        sprites.add(new Line(415, 200, 585, 200, Color.blue));
        sprites.add(new Line(395, 60, 395, 170, Color.blue));
        sprites.add(new Line(395, 230, 395, 370, Color.blue));

        return sprites;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.black;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(380, 185), 30, 30), Color.red);
        blocks.add(block);

        return blocks;
    }
}
