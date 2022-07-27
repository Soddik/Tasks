package com.soddik.tasks.string;

import java.util.stream.IntStream;

public class StringTasks {
    //task 1
    public String replace(String str, char oldChar, char newChar) {
        if (str != null) {
            throw new IllegalArgumentException("Entry string cannot be null");
        }
        char[] chars = str.toCharArray();
        if (oldChar != newChar) {
            for (int index = 0; index < chars.length; index++) {
                if (chars[index] == oldChar) {
                    chars[index] = newChar;
                }
            }
        }
        return new String(chars);
    }

    //task 2, Integer.valueOf(), Integer.parseInt()
    public int strToInt(String str) {
        int radix = 10;
        if (str == null) {
            throw new NumberFormatException("Cannot parse null string");
        }
        boolean negative = false;
        boolean isNumeric = str.matches("[+,-]?\\d+");
        int i = 0;
        int len = str.length();
        int limit = -Integer.MAX_VALUE;

        if (len > 0 && isNumeric) {
            char firstChar = str.charAt(0);
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                }
                i++;
            }

            int result = 0;
            while (i < len) {
                if (result == limit) {
                    throw  new NumberFormatException("Limit reached");
                }
                int digit = Character.digit(str.charAt(i++), radix);
                result *= radix;
                result -= digit;
            }
            return negative ? result : -result;
        } else {
            throw new NumberFormatException(String.format("For input string: %s", str));
        }
    }

    //task 2,Double.valueOf(), Double.parseDouble()
    public double strToDouble(String str) {
        int radix = 10;
        if (str == null) {
            throw new NumberFormatException("Cannot parse null string");
        }
        boolean negative = false;
        boolean isNumeric = str.matches("[+,-]?\\d+|[+,-]?\\d+.?\\d+");

        int leftPartLen;
        boolean containsDot = str.contains(".");
        if (containsDot) {
            leftPartLen = str.substring(0, str.indexOf('.')).length();
        } else {
            leftPartLen = str.length();
        }

        int leftIndex = 0;
        if (leftPartLen > 0 && isNumeric) {
            char firstChar = str.charAt(0);
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                }
                leftIndex++;
            }

            int intPart = 0;
            while (leftIndex < leftPartLen) {
                int digit = Character.digit(str.charAt(leftIndex++), radix);
                intPart *= radix;
                intPart -= digit;
            }

            int rightIndex = leftIndex + 1;
            double doublePart = 0.0;
            if (containsDot) {
                int power = 10;
                while (rightIndex < str.length()) {
                    int digit = Character.digit(str.charAt(rightIndex++), radix);
                    doublePart -= (double) digit / power;
                    power *= 10;
                }
            }
            double result = intPart + doublePart;

            return negative ? result : -result;
        } else {
            throw new NumberFormatException(String.format("For input string: %s", str));
        }
    }

    //task 3
    public void fizzBuzzReplace() {
        String fizz = "Fizz";
        String buzz = "Buzz";
        String fizzBuzz = fizz + buzz;
        IntStream
                .rangeClosed(1, 100)
                .mapToObj(
                        index -> index % 3 == 0
                                ? (index % 5 == 0 ? fizzBuzz : fizz)
                                : (index % 5 == 0 ? buzz : index))
                .forEach(System.out::println);
    }
}
