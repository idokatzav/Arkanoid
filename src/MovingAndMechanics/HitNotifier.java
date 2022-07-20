package MovingAndMechanics;
/**
 * HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * Add hit listener as a listener to hit events.
     * @param hl the listener to add.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hit listner from the list of listeners to hit events.
     * @param hl the listener to remove.
     */
    void removeHitListener(HitListener hl);
}
