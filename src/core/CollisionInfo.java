package core;

// Ofir Sasoni
// 325690386

/**
 * The class represent info about collision accrued.
 */
public class CollisionInfo {
    private geometry.Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * @param collisionPoint the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(geometry.Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Get method.
     * @return the collision point.
     */
    public geometry.Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Get method.
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Set method.
     * @param collisionPoint new collision point.
     */
    public void setCollisionPoint(geometry.Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * Set method.
     * @param collisionObject new collision object.
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
}
