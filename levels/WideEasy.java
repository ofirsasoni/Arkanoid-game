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
 * The class represents the "WideEasy" game level.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public Rectangle paddleRectangle() {
        return new Rectangle(new Point(75, 570), paddleWidth(), 15);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        Velocity velocity1 = new Velocity(-1, 5);
        Velocity velocity2 = new Velocity(-2, 6);
        Velocity velocity3 = new Velocity(-3, 4);
        Velocity velocity4 = new Velocity(-4, 3);
        Velocity velocity5 = new Velocity(-5, 6);
        Velocity velocity6 = new Velocity(1, 6);
        Velocity velocity7 = new Velocity(2, 7);
        Velocity velocity8 = new Velocity(3, 3);
        Velocity velocity9 = new Velocity(4, 4);
        Velocity velocity10 = new Velocity(5, 5);

        velocities.add(velocity1);
        velocities.add(velocity2);
        velocities.add(velocity3);
        velocities.add(velocity4);
        velocities.add(velocity5);
        velocities.add(velocity6);
        velocities.add(velocity7);
        velocities.add(velocity8);
        velocities.add(velocity9);
        velocities.add(velocity10);

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(201, 441, 4, Color.black, initialBallVelocities().get(0)));
        balls.add(new Ball(221, 421, 4, Color.black, initialBallVelocities().get(1)));
        balls.add(new Ball(241, 401, 4, Color.black, initialBallVelocities().get(2)));
        balls.add(new Ball(261, 381, 4, Color.black, initialBallVelocities().get(3)));
        balls.add(new Ball(281, 361, 4, Color.black, initialBallVelocities().get(4)));
        balls.add(new Ball(521, 361, 4, Color.black, initialBallVelocities().get(5)));
        balls.add(new Ball(541, 381, 4, Color.black, initialBallVelocities().get(6)));
        balls.add(new Ball(561, 401, 4, Color.black, initialBallVelocities().get(7)));
        balls.add(new Ball(581, 421, 4, Color.black, initialBallVelocities().get(8)));
        balls.add(new Ball(601, 441, 4, Color.black, initialBallVelocities().get(9)));

        return balls;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.white;
    }

    @Override
    public ArrayList<Sprite> background() {
        ArrayList<Sprite> sprites = new ArrayList<>();

        Color brightYellow = new Color(255, 233, 0);
        Color mediumYellow = new Color(254, 221, 0);
        Color darkerYellow = new Color(246, 239, 119);

        for (int i = 0; i < 18 * 6; i++) {
            Line line = new Line(200, 175, 30 + 6 * i, 295, Color.yellow);
            sprites.add(line);
        }

        sprites.add(new Circle(200, 175, 85, darkerYellow, true));
        sprites.add(new Circle(200, 175, 70, mediumYellow, true));
        sprites.add(new Circle(200, 175, 55, brightYellow, true));

        return sprites;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * The function gets an index represents the line of blocks, and return the color of the line.
     * @param i index of the line.
     * @return the color of the line.
     */
    public Color getColor(int i) {
        if (i <= 1) {
            return Color.red;
        } else if (i <= 3) {
            return Color.orange;
        } else if (i <= 5) {
            return Color.yellow;
        } else if (i <= 8) {
            return Color.green;
        } else if (i <= 10) {
            return Color.blue;
        } else if (i <= 12) {
            return Color.pink;
        } else {
            return Color.CYAN;
        }
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Color color = getColor(i);
            Rectangle rectangle = new Rectangle(new Point(25 + 50 * i, 300), 50, 25);
            Block block = new Block(rectangle, color);
            blocks.add(block);
        }

        return blocks;
    }
}
