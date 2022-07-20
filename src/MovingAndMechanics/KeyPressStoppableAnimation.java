package MovingAndMechanics;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation Class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private Animation animation;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * Constructor.
     * @param sensor keyboard sensor.
     * @param key the key.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.animation = animation;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.isAlreadyPressed && this.keyboardSensor.isPressed(key)) {
            this.stop = true;
        } else if (!this.keyboardSensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
