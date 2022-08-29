package animation;

import biuoop.DrawSurface;

/**
 * The interface represents an animation.
 */
public interface Animation {
    /**
     * The method describes what the animation does in a single frame.
     * @param d the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The method shows the animation without changing it.
     * @param d the draw surface.
     */
    void show(DrawSurface d);

    /**
     * The method returns true if the animation should stop, false otherwise.
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}
