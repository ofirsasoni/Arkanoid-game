// Ofir Sasoni
// 325690386

import core.Counter;
import game.GameFlow;
import levels.LevelInformation;
import levels.FinalFour;
import levels.WideEasy;
import levels.Green3;
import levels.DirectHit;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the game run.
 */
public class Ass6Game {
    /**
     * The method checks if a given string is a string of number.
     * @param strNum the string we want to check.
     * @return true if the string is numeric, false otherwise.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    /**
     * function which gets an array of strings contains ints ( such as "123" ) and returns integers array with
     * the values given in the string array.
     * @param numbers array of strings. each string contains integers.
     * @return integers array with the values given in the string array.
     */
    public static int[] stringsToInts(String[] numbers) {
        if (numbers.length == 0) { // Checking if an input has been entered by the user.
            return null;
        }

        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (isNumeric(numbers[i])) {
                arr[i] = Integer.parseInt(numbers[i]);
            }
        }

        return arr;
    }

    private static void sortLevel(List<LevelInformation> levelInformations, int level) {
        if (level == 1) {
            levelInformations.add(new DirectHit());
        }
        if (level == 2) {
            levelInformations.add(new WideEasy());
        }
        if (level == 3) {
            levelInformations.add(new Green3());
        }
        if (level == 4) {
            levelInformations.add(new FinalFour());
        }
    }

    /**
     * Main method. The function runs the game.
     * @param args input from the user in the terminal. Not used in this method.
     */
    public static void main(String[] args) {
        int[] levels = stringsToInts(args);
        List<LevelInformation> levelInformation = new ArrayList<>();
        Counter score = new Counter(0);

        if (levels != null) {
            for (int level : levels) {
                sortLevel(levelInformation, level);
            }
        }

        if (levels == null || levelInformation.isEmpty()) {
            levelInformation.add(new DirectHit());
            levelInformation.add(new WideEasy());
            levelInformation.add(new Green3());
            levelInformation.add(new FinalFour());
        }

        GameFlow gameFlow = new GameFlow(score);
        gameFlow.runLevels(levelInformation);
    }
}
