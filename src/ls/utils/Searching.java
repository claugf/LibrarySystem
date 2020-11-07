/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.utils;

import java.util.ArrayList;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class Searching {
    /*
     * Linear Search algorithm was choosen for this project as we need to going throught all the array 
     * for possible multiple results, and the elements will not be sorted
     * This method will receive an array and a value to be searched in the array
     * and return an array with the findings
     */
    public static ArrayList<String> linealSearch(String[] array, String target){
        //  Array to save all the findings
        ArrayList<String> temp=new ArrayList<>();
        //  Going one by one the elements in the array
        for (String arr : array) {
            //  When the element is found, it is save in the temporary array
            //  Convert both sides of the comparison in LowerCase, to avoid KeySensitivity
            if (arr.toLowerCase().contains(target.toLowerCase())) {
                temp.add(arr);
            }            
        }
        return temp;
    }
}
