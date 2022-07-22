package com.soddik.tasks;

import com.soddik.tasks.string.StringTasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringTasksTest {
    private final StringTasks stringTasks = new StringTasks();

    @Test
    void replace() {
        String result = stringTasks.replace("ABACADAEAFAGAH", 'E', 'Z');
        Assertions.assertEquals("ABACADAZAFAGAH", result);
    }

    @Test
    void strToInt() {
        String num = "3";
        Assertions.assertEquals(3, stringTasks.strToInt(num));
        String positiveNum = "+23";
        Assertions.assertEquals(23, stringTasks.strToInt(positiveNum));
        String negativeNum = "-25";
        Assertions.assertEquals(-25, stringTasks.strToInt(negativeNum));


        String msg = "";
        String numericWithChar = "25a";
        try {
            stringTasks.strToInt(numericWithChar);
        } catch (NumberFormatException e) {
            msg = e.getMessage();
        } finally {
            Assertions.assertEquals(String.format("For input string: %s", numericWithChar), msg);
        }
    }

    @Test
    void strToDouble() {
        String num = "20.6345778";
        Assertions.assertEquals(20.6345778, stringTasks.strToDouble(num));

        String positiveNum = "+20.2345";
        Assertions.assertEquals(+20.2345, stringTasks.strToDouble(positiveNum));

        String negativeNum = "-20.4322";
        Assertions.assertEquals(-20.4322, stringTasks.strToDouble(negativeNum));

        String intLikeNum = "20";
        Assertions.assertEquals(20.0, stringTasks.strToDouble(intLikeNum));

        String msg = "";
        String numericWithChar = "25.234a";
        try {
            stringTasks.strToDouble(numericWithChar);
        } catch (NumberFormatException e) {
            msg = e.getMessage();
        } finally {
            Assertions.assertEquals(String.format("For input string: %s", numericWithChar), msg);
        }

        String msg2 = "";
        String numericWithDots = "25..234";
        try {
            stringTasks.strToDouble(numericWithDots);
        } catch (NumberFormatException e) {
            msg2 = e.getMessage();
        } finally {
            Assertions.assertEquals(String.format("For input string: %s", numericWithDots), msg2);
        }
    }

    @Test
    void fizzBuzzReplace() {
        stringTasks.fizzBuzzReplace();
    }
}