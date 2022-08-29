package core;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;

/**
 * The interface represents sprite object such as balls or blocks.
 */
public interface Sprite {
    /**
     * The function gets a draws the Sprite object on the given draw surface.
     * @param d given draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * The function adds the sprite to both given game sprite and collidable collection.
     * @param g given game.
     */
    void addToGame(game.GameLevel g);
}
