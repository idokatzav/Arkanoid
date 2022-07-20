package MovingAndMechanics;
import biuoop.DrawSurface;
/**
 * Interface Animations.
 */
public interface Animation {
    /**
     * does the operations of one frame.
     * @param d the drawsurface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Checks if the game should stop.
     * @return true if it should, otherwise false.
     */
    boolean shouldStop();
}
