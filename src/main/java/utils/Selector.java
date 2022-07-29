package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Selector {
    public static <T extends HasName>  T select(List<T> options) {
        int selectedNumber = -2;
        while (selectedNumber == -2) {
            int counter = 0;
            for (HasName option : options) {
                System.out.println(counter + ". " + option.getName());
                counter += 1;
            }
            if (counter >= 1) {
                selectedNumber = readCommandNumber(-1, counter - 1);
                if (selectedNumber == -1) {
                    return null;
                }
                if (selectedNumber == -2) {
                    System.out.println("Введите число от " + 0 + " до " + (counter - 1));
                }
            } else {
                selectedNumber = 0;
            }
        }
        return options.get(selectedNumber);
    }

    public static <T extends HasName> T select(T[] options) {
        return select(Arrays.stream(options).toList());
    }

    private static int readCommandNumber(int from, int to) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input;
        try {
            input = Integer.parseInt(reader.readLine());
            if ((input >= from && input <= to)) {
                return input;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}