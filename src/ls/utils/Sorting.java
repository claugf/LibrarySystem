/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.utils;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class Sorting {    
    /*
     * This Merge Sort algorithm was choosen for this project as its complexity
     * for Worst case is O(N log N) compared to Selection Sort with a complexity of O(N2)
     * This method will receive an array of Strings which will be sorted
     */
    public static void mergeSort(String[] array) {
        int mid;
        String[] firstHalf;
        String[] secondHalf;

        //  Chek if the array has more than one object
        if (array.length > 1) {
            //  Split original array into 2 sub-arrays
            mid = array.length / 2;

            //  First sublist
            firstHalf = new String[mid];
            System.arraycopy(array, 0, firstHalf, 0, mid);
            //  Second sublist
            secondHalf = new String[array.length - mid];
            System.arraycopy(array, mid, secondHalf, 0, array.length - mid);

            //  Recursively call mergeSort method for first and second sublist
            mergeSort(firstHalf);
            mergeSort(secondHalf);

            //  Now merge both halves
            merge(firstHalf, secondHalf, array);
        }
    }

    // Params: FirstHalf, SecondHalf, Original Array
    private static void merge(String[] a, String[] b, String[] c) {
        // Declaring index variables
        int countA = 0;
        int countB = 0;
        int countC = 0;

        // Traverse left and righ lists for merging
        while (a.length > countA && b.length > countB) {
            //  Comparing string between first and second array
            if (a[countA].compareTo(b[countB]) < 0) {
                //If fieldA is smaller than fieldB, we set A into final array
                c[countC] = a[countA];
                countA++;
            } else {
                //If fieldA is grater than fieldB, we set B into final array
                c[countC] = b[countB];
                countB++;
            }
            countC++;
        }

        //  Copy remaining element from both sublist, in case any left
        while (a.length > countA) {
            c[countC] = a[countA];
            countC++;
            countA++;
        }
        while (b.length > countB) {
            c[countC] = b[countB];
            countC++;
            countB++;
        }
    }

}
