package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Ball;
import gameObjects.Block;
import geometry.Circle;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the "Green3" game level.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public int paddleSpeed() {
        return 8;
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
        velocities.add(new Velocity(3, 4));
        velocities.add(new Velocity(4, -4));

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(51, 301, 4, Color.white, initialBallVelocities().get(0)));
        balls.add(new Ball(101, 401, 4, Color.white, initialBallVelocities().get(1)));

        return balls;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(102, 51, 153);
    }

//    public Color getBackgroundColor() {
//        return new Color(17, 80, 8);
//    }

    @Override
    public ArrayList<Sprite> background() {
        ArrayList<Sprite> sprites = new ArrayList<>();
        sprites.add(new Rectangle(new Point(100, 450), 100, 150, Color.black));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sprites.add(new Rectangle(new Point(110 + 16 * j,  460 + 26 * i), 16, 26, Color.white));
            }
        }

        sprites.add(new Rectangle(new Point(125, 350), 50, 99, Color.gray));
        sprites.add(new Rectangle(new Point(145, 200), 10, 149, Color.DARK_GRAY));

        sprites.add(new Circle(150, 185, 15, Color.pink, true));
        sprites.add(new Circle(150, 185, 10, Color.red, true));
        sprites.add(new Circle(150, 185, 5, Color.white, true));

        return sprites;
    }

    @Override
    public String levelName() {
        return "Green 3";
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

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 6; j++) {

                Color color = this.setColor(i);
                Rectangle rectangle = new Rectangle(new Point(723 - 47 * j, 225 - 25 * i), 47, 25);
                Block block = new Block(rectangle, color);
                blocks.add(block);
            }
        }

        return blocks;
    }
}
