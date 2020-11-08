/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.utils;

import java.util.Scanner;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class tools {
    public static String separator = "-----------------------------------------------------------------------------------";
    
    /*
     *  Method for valid input as a string
     */
    public static String getString(Scanner sc, String prompt) {
        String seq = "";
        boolean isValid = false;

        while (isValid == false) {
            System.out.println(prompt);
            seq = sc.nextLine();

            if (!seq.equals("")) {
                isValid = true;
            } else {
                System.out.println("Error! This entry is required. Try again.");
            }
        }
        return seq;
    }

    /*
     *  Method for valid input as an integer
     */
    public static int getInt(Scanner sc, String prompt) {
        int value = 0;
        String number;
        boolean isDouble = false;
        while (isDouble == false) {
            number = getString(sc, prompt);
            if (number.matches("\\d*-?\\d*")) {
                value = Integer.parseInt(number);
                isDouble = true;
            } else {
                System.out.println("Error! Invalid integer value. Try again.");
            }
        }
        return value;
    }
}
