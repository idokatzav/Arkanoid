import GameMain.GameFlow;
import GameMain.GameLevel;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import Levels.LevelInformation;
import MovingAndMechanics.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;
/**
 * Game Level class.
 */
public class Ass6Game {
    /**
     * Main method. creates a game according to cmd.
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        GUI gui = new GUI(GameLevel.NAME, GameLevel.WIDTH, GameLevel.HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> lst = new ArrayList<LevelInformation>();
        boolean wasThereLevel = false;
        for (String s: args) {
            switch (s) {
                case "1":
                    wasThereLevel = true;
                    LevelInformation levelInformation1 = new Level1(keyboardSensor);
                    lst.add(lst.size(), levelInformation1);
                    break;
                case "2":
                    wasThereLevel = true;
                    LevelInformation levelInformation2 = new Level2(keyboardSensor);
                    lst.add(lst.size(), levelInformation2);
                    break;
                case "3":
                    wasThereLevel = true;
                    LevelInformation levelInformation3 = new Level3(keyboardSensor);
                    lst.add(lst.size(), levelInformation3);
                    break;
                case "4":
                    wasThereLevel = true;
                    LevelInformation levelInformation4 = new Level4(keyboardSensor);
                    lst.add(lst.size(), levelInformation4);
                    break;
                default:
                    break;
            }
        }
        if (!wasThereLevel) {
            LevelInformation levelInformation = new Level1(keyboardSensor);
            lst.add(lst.size(), levelInformation);
            levelInformation = new Level2(keyboardSensor);
            lst.add(lst.size(), levelInformation);
            levelInformation = new Level3(keyboardSensor);
            lst.add(lst.size(), levelInformation);
            levelInformation = new Level4(keyboardSensor);
            lst.add(lst.size(), levelInformation);
        }
        AnimationRunner animationRunner = new AnimationRunner(gui, GameLevel.FPS);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        gameFlow.runLevels(lst);
    }
}
