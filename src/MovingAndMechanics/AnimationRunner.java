package MovingAndMechanics;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * Animation Runner Class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    /**
     * Constructor.
     * @param gui the gui.
     * @param framesPerSecond the framesPerSecond.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }
    /**
     * Run the game.
     * @param animation an animation.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
