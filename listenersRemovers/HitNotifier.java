package listenersRemovers;

// Ofir Sasoni
// 325690386

/**
 * The interface represents notifier to a hit.
 */
public interface HitNotifier {
    /**
     * The function adds a listener.
     * @param hl the listener we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * The function removes a listener.
     * @param hl the listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}
