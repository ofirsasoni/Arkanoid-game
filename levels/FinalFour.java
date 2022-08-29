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
 * The class represents the "FinalFour" game level.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public Rectangle paddleRectangle() {
        return new Rectangle(new Point(380, 570), paddleWidth(), 15);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(3, 2));
        velocities.add(new Velocity(2, -3));
        velocities.add(new Velocity(3, -2));

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(470, 500, 4, Color.white, initialBallVelocities().get(0)));
        balls.add(new Ball(470, 520, 4, Color.white, initialBallVelocities().get(1)));
        balls.add(new Ball(370, 510, 4, Color.white, initialBallVelocities().get(2)));

        return balls;
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(0, 145, 234);
    }

    @Override
    public ArrayList<Sprite> background() {
        ArrayList<Sprite> sprites = new ArrayList<>();

        Color lightGray = new Color(211, 211, 211);
        Color silver = new Color(192, 192, 192);
        Color whiteSmoke = new Color(245, 245, 245);

        sprites.add(new Line(90, 450, 70, 600, Color.white));
        sprites.add(new Line(100, 450, 80, 600, Color.white));
        sprites.add(new Line(110, 450, 90, 600, Color.white));
        sprites.add(new Line(120, 450, 100, 600, Color.white));
        sprites.add(new Line(130, 450, 110, 600, Color.white));
        sprites.add(new Line(140, 450, 120, 600, Color.white));
        sprites.add(new Line(150, 450, 130, 600, Color.white));
        sprites.add(new Line(160, 450, 140, 600, Color.white));
        sprites.add(new Line(170, 450, 150, 600, Color.white));
        sprites.add(new Line(180, 450, 160, 600, Color.white));

        sprites.add(new Circle(150, 450, 25, Color.white, true));
        sprites.add(new Circle(120, 445, 25, lightGray, true));
        sprites.add(new Circle(140, 470, 25, silver, true));
        sprites.add(new Circle(100, 470, 25, whiteSmoke, true));
        sprites.add(new Circle(170, 470, 25, Color.white, true));

        sprites.add(new Line(590, 480, 570, 600, Color.white));
        sprites.add(new Line(600, 480, 580, 600, Color.white));
        sprites.add(new Line(610, 480, 590, 600, Color.white));
        sprites.add(new Line(620, 480, 600, 600, Color.white));
        sprites.add(new Line(630, 480, 610, 600, Color.white));
        sprites.add(new Line(640, 480, 620, 600, Color.white));
        sprites.add(new Line(650, 480, 630, 600, Color.white));
        sprites.add(new Line(660, 480, 640, 600, Color.white));
        sprites.add(new Line(670, 480, 650, 600, Color.white));
        sprites.add(new Line(680, 480, 660, 600, Color.white));

        sprites.add(new Circle(650, 480, 25, Color.white, true));
        sprites.add(new Circle(620, 475, 25, lightGray, true));
        sprites.add(new Circle(640, 500, 25, silver, true));
        sprites.add(new Circle(600, 500, 25, whiteSmoke, true));
        sprites.add(new Circle(670, 500, 25, Color.white, true));

        return sprites;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     * The function gets the line number of the stair, and returns the appropriate line color.
     * @param i the line of the stair.
     * @return the color of the given line.
     */
    private java.awt.Color setColor(int i) {
        if (i == 0) {
            return Color.cyan;
        }
        if (i == 1) {
            return Color.pink;
        }
        if (i == 2) {
            return Color.white;
        }
        if (i == 3) {
            return Color.green;
        }
        if (i == 4) {
            return Color.yellow;
        }
        if (i == 5) {
            return Color.red;
        }
        return Color.GRAY;
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Color color = this.setColor(i);
                Rectangle rectangle = new Rectangle(new Point(25 + 50 * j, 325 - 25 * i), 50, 25);
                Block block = new Block(rectangle, color);
                blocks.add(block);
            }
        }

        return blocks;
    }
}
