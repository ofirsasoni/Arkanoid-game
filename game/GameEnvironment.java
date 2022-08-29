package game;

// Ofir Sasoni
// 325690386

import core.Collidable;
import core.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * The class represents all the collidable objects in the game.
 */
public class GameEnvironment {
    private java.util.ArrayList<Collidable> collidables;

    /**
     * Constructor.
     * @param collidables new list of collidable object in the game.
     */
    public GameEnvironment(java.util.ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Constructor.
     */
    public GameEnvironment()  {
        this.collidables = new java.util.ArrayList<Collidable>();
    }

    /**
     * The function adding a given collidable object to the list of collidable objects.
     * @param c new collidable object to be added to the list.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The function removes all the appearances of given collidable from the game environment.
     * @param c collidable object we want to remove from game environment.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Get method.
     * @return this collidables.
     */
    public java.util.ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * The function returns the info about the closest collision about to happen.
     * @param trajectory a line segment describes a moving of an object.
     * @return the info about the closest collision about to happen.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int amountOfCollidables = this.collidables.size();
        Point[] closestInterPointToEachCollidableObject = new Point[amountOfCollidables];
        double closestDistance = -1;
        Point closestPoint = null;
        Collidable collisionObject = null;

        for (int i = 0; i < amountOfCollidables; i++) {
            Rectangle currentRectangle = this.collidables.get(i).getCollisionRectangle();
            closestInterPointToEachCollidableObject[i] = trajectory.closestIntersectionToStartOfLine(currentRectangle);
        }

        for (int i = 0; i < amountOfCollidables; i++) {
            Point point = closestInterPointToEachCollidableObject[i];
            if (point == null) {
                continue;
            }
            if (closestDistance == -1 || point.distance(trajectory.start()) < closestDistance) {
                closestDistance = point.distance(trajectory.start());
                closestPoint = point;
                collisionObject = this.collidables.get(i);
            }
        }

        if (closestPoint == null || collisionObject == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, collisionObject);
    }
}
