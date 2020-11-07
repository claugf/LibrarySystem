/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.utils;

import java.util.ArrayList;
import ls.model.Sort;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class SortingOld {
    ArrayList<Sort> sort;

    public SortingOld(ArrayList<Sort> sort) {
        this.sort = sort;
    }
    public void sort(ArrayList<Sort> sort){
        
    }
    //  Method 
    public void mergeSort( ArrayList<Sort> a){
        int mid;
        ArrayList<Sort> firstHalf = new ArrayList<>();
        ArrayList<Sort> secondHalf = new ArrayList<>();
        
        //  Chek if the array has more than one object
        if(a.size() > 1){
            //  Split original array into 2 sub-arrays
            mid = a.size()/2;
            
            //  First sublist
            for (int i = 0; i < mid; i++) {
                firstHalf.add(a.get(i));
            }
            //  Second sublist
            for (int i = mid; i < a.size(); i++) {
                secondHalf.add(a.get(i));
            }
            
            //  Recursively call mergeSort method for first and second sublist
            mergeSort(firstHalf);
            mergeSort(secondHalf);
            
            //  Now merge both halves
            merge(firstHalf, secondHalf, sort);
        }   
    }
    // Params: FirstHalf, SecondHalf, Original Array
    private void merge(ArrayList<Sort> a, ArrayList<Sort> b, ArrayList<Sort> c){
        // Declaring index variables
        int countA = 0;
        int countB = 0;
        int countC = 0;
        
        // traverse left and righ lists for merging
        while(a.size() > countA && b.size() > countB){
            //  Comparing string between first and second array
            if(a.get(countA).getField().compareTo(b.get(countB).getField()) < 0){
                //If fieldA is smaller than fieldB, we set A into final array
                c.set(countC, a.get(countA));
                countA++;
            }else{
                //If fieldA is grater than fieldB, we set B into final array
                c.set(countC, b.get(countB));
                countB++;
            }
            countC++;
        }
        
        //  Copy remaining element from both sublist, in case any left
        while(a.size() > countA){
            c.set(countC, a.get(countA));
            countC++;
            countA++;
        }
        while(b.size()>countB){
            c.set(countC, b.get(countB));
            countC++;
            countB++;
        }
    }
}
