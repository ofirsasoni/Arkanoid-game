package game;

// Ofir Sasoni
// 325690386

import biuoop.DrawSurface;
import core.Sprite;

import java.util.ArrayList;

/**
 * The class represents a list of sprite objects.
 */
public class SpriteCollection {
    private final java.util.ArrayList<Sprite> sprites;

    /**
     * Constructor.
     * @param sprites list of Sprite objects.
     */
    public SpriteCollection(java.util.ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Get method.
     * @return sprites.
     */
    public java.util.ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * The function adds a given sprite to the list.
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The function removes a sprite from the sprite list.
     * @param s the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            if (sprites.get(i) != null) {
                sprites.get(i).timePassed();
            }
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d given draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
