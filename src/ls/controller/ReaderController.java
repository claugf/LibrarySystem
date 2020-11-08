/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ls.model.Book;
import ls.model.Reader;
import ls.utils.Searching;
import ls.utils.Sorting;
import ls.utils.tools;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class ReaderController {

    //  Declaring variables
    private ArrayList<Reader> readers;
    private BufferedReader br;

    /*
     *  Declaring constructor and initializing variables
     */
    public ReaderController() {
        // Initializing Readers array
        readers = new ArrayList<>();
        try {
            // Instanciating BufferedReader with our readers file
            br = new BufferedReader(new FileReader("Readers.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Method for load all the readers from the data file
     */
    public void loadReaders() {
        try {
            //  Reading first line (TITLES) - we will ignore this line
            br.readLine();
            //  Reading second line (first reader)
            String line = br.readLine();
            //  Going throught the file
            while (line != null) {
                //  Spliting each line to get the variables
                String[] data = line.split(";");
                //  Adding the data as a elemArrayReader object to the Readers Array
                readers.add(new Reader(data[0], data[1], data[2], data[3], data[4]));
                //  Reading next file's line
                line = br.readLine();
            }
        } catch (IOException e) {
            //  Printing the error
            e.printStackTrace();
        }
    }

    /*
     *  Display all the readers in the list
     */
    public void displayReaders(ArrayList<Reader> listReaders) {
        //  Printing Headers with a proper format
        System.out.println(tools.separator);
        System.out.printf("%-10s", "READERID");
        System.out.printf("%-15s", "NAME");
        System.out.printf("%-15s", "SURNAME");
        System.out.printf("%-20s", "PHONE");
        System.out.println("ADDRESS");//  There is no need to format as it's the last field

        //  Going throught Readers array
        for (Reader r : listReaders) {
            //  Printing each elemArrayReader
            System.out.printf("%-10s", r.getReaderId());
            System.out.printf("%-15s", r.getName());
            System.out.printf("%-15s", r.getSurname());
            System.out.printf("%-20s", r.getPhone());
            System.out.println(r.getAddress());
        }
    }

    /*
     *  This method fill an String array by field of interest, to use it later 
     *  in searching or sorting methods
     */
    private String[] fillArray(int sortField) {
        //  Creating a string array to add the field of interest
        String[] arrayReaders = new String[readers.size()];
        //  Filling array with the field of interest
        for (int i = 0; i < readers.size(); i++) {
            //  If field of interes is 1(ReaderID), we add it to the string array
            if (sortField == 1) {
                arrayReaders[i] = readers.get(i).getReaderId();
            } else {
                if (sortField == 2) {
                    //  If it's 2 , we add Name
                    arrayReaders[i] = readers.get(i).getName();
                } else {
                    //  If it's 2 , we add Surname
                    arrayReaders[i] = readers.get(i).getSurname();
                }
            }
        }
        return arrayReaders;
    }

    /*
     *  This method sort books by 3 fields of interest and display the results
     */
    public void sortReaders(int sortField) {
        //  If field of interes is 1(ReaderID)
        if (sortField == 1) {
            // We first sort by Surname
            sorting(3);
            //  Then by Name
            sorting(2);
            //  Then by ReaderID
            sorting(1);
            //  Print headers
            System.out.println("-----------------------------------READERS BY ID-----------------------------------");
        } else {
            if (sortField == 2) {
                // We first sort by ReaderID
                sorting(1);
                //  Then by Surname
                sorting(3);
                //  Then by Name
                sorting(2);
                //  Print headers
                System.out.println("----------------------------------READERS BY NAME----------------------------------");
            } else {
                // We first sort by ReaderID
                sorting(1);
                //  Then by Name
                sorting(2);
                //  Then by Surname
                sorting(3);
                //  Print headers
                System.out.println("--------------------------------READERS BY SURNAME---------------------------------");
            }
        }

        //  Now readers are sorted by 3 fields
        displayReaders(readers);
    }

    /*
     *  This method sort by one field of interest only
     */
    private void sorting(int sortField) {
        //  Creating a string array to add the field of interest with method "fillArray"
        String[] arrayReaders = fillArray(sortField);

        //  Sorting array
        Sorting.mergeSort(arrayReaders);
        //  Declaring temporary array to fill it later with sorted array
        ArrayList<Reader> tempR = new ArrayList<>();
        //  Going throught the array of sorted strings
        for (String elemArrayReader : arrayReaders) {
            //  Going throught array of readers
            for (Reader reader : readers) {
                //If ID is the field of interes, we will compare it later on
                if (sortField == 1) {
                    // Asking if the field of the sorted array is the same that the reader
                    //  to add it into the temporary array of readers
                    if (reader.getReaderId().equals(elemArrayReader)) {
                        //  Asking if the element is not already in the arraylist before to add it
                        if (!tempR.contains(reader)) {
                            tempR.add(reader);
                        }
                    }
                } else {
                    if (sortField == 2) {
                        //  If it's 2 , we compare by Names 
                        if (reader.getName().equals(elemArrayReader)) {
                            //  Asking if the element is not already in the arraylist before to add it
                            if (!tempR.contains(reader)) {
                                tempR.add(reader);
                            }
                        }
                    } else {
                        //  If it's 3 , we compare by Surnames 
                        if (reader.getSurname().equals(elemArrayReader)) {
                            //  Asking if the element is not already in the arraylist before to add it
                            if (!tempR.contains(reader)) {
                                tempR.add(reader);
                            }
                        }
                    }
                }
            }
        }

        //  Now we replace readers array with our new sorted array of readers
        readers = tempR;
    }

    /*
     *  This method search readers by field of interest, and in case there is none
     *  it search by the next field of interest
     */
    public void searchReaders(int sortField, String target) {
        //  First sort ReadersList
        sorting(sortField);
        //  Declaring list of findings and Searching by given target
        ArrayList<Reader> findings = searching(sortField, target);

        //If Title is the field of interest
        if (sortField == 1) {
            // If there is finding, display results, if there is no findings, we search by author
            if (!findings.isEmpty()) {
                //  Print headers
                System.out.println("-----------------------------------READERS BY ID-----------------------------------");
            } else {
                System.out.println("Sorry! None reader was found with that ID. Try search by Name or Surname.");

            }
        } else {
            if (sortField == 2) {
                // If there is finding, display results, if there is no findings, we search by Surname
                if (!findings.isEmpty()) {
                    //  Print headers
                    System.out.println("----------------------------------READERS BY NAME----------------------------------");
                } else {
                    //  First sort ReadersList by Surname
                    sorting(3);
                    //  Searching by given target
                    findings = searching(3, target);
                    if (!findings.isEmpty()) {
                        System.out.println("None reader was found with that search criteria. However, some readers were found under that surname.");
                        //  Print headers
                        System.out.println("--------------------------------READERS BY SURNAME---------------------------------");
                    } else {
                        System.out.println("Sorry! None reader was found with that criteria. Either by Name or Surname.");
                    }
                }
            } else {
                // If there is finding, display results, if there is no findings, we search by Name
                if (!findings.isEmpty()) {
                    //  Print headers
                    System.out.println("----------------------------------READERS BY SURNAME----------------------------------");
                } else {
                    //  First sort BooksList by Name
                    sorting(2);
                    //  Searching by given target
                    findings = searching(2, target);
                    if (!findings.isEmpty()) {
                        System.out.println("None reader was found with that search criteria. However, some readers were found under that name.");
                        //  Print headers
                        System.out.println("--------------------------------READERS BY NAME---------------------------------");
                    } else {
                        System.out.println("Sorry! None reader was found with that criteria. Either by Name or Surname.");
                    }
                }
            }
        }

        //  Now display found books, if there is any
        if (!findings.isEmpty()) {
            displayReaders(findings);
        }
    }

    /*
     *  This method search by one field of interest only
     */
    private ArrayList<Reader> searching(int sortField, String target) {
        //  Creating a string array to add the field of interest with method "fillArray"
        String[] arrayReaders = fillArray(sortField);

        //  Searching in array
        ArrayList<String> findings = Searching.linealSearch(arrayReaders, target);
        //  Declaring temporary array to fill it later with sorted array
        ArrayList<Reader> tempR = new ArrayList<>();

        //  Going throught array of findings strings
        for (String finding : findings) {
            //  Going throught array of readers
            for (Reader reader : readers) {
                //If ID is the field of interes, we will compare it later on
                if (sortField == 1) {
                    // Asking if the field of the sorted array is the same that the reader
                    //  to add it into the temporary array of readers
                    if (reader.getReaderId().equals(finding)) {
                        //  Asking if the element is not already in the arraylist before to add it
                        if (!tempR.contains(reader)) {
                            tempR.add(reader);
                        }
                    }
                } else {
                    if (sortField == 2) {
                        //  If it's 2 , we compare by Names 
                        if (reader.getName().equals(finding)) {
                            //  Asking if the element is not already in the arraylist before to add it
                            if (!tempR.contains(reader)) {
                                tempR.add(reader);
                            }
                        }
                    } else {
                        //  If it's 3 , we compare by Surnames 
                        if (reader.getSurname().equals(finding)) {
                            //  Asking if the element is not already in the arraylist before to add it
                            if (!tempR.contains(reader)) {
                                tempR.add(reader);
                            }
                        }
                    }
                }
            }
        }

        //  Now we return all readers found list
        return tempR;
    }

    /*
     *  This method validate if the given readerId is registered in the database
     *  and return the reader if it exists, if not return null
     */
    public Reader getReader(String readerId) {
        for (Reader reader : readers) {
            if (reader.getReaderId().equals(readerId)) {
                return reader;
            }
        }
        return null;
    }

    /*
     *  This method ask to the user a valid reader, it return a string array with readerId and readerName
     *  in case user give up, it will return an empty array of Strings 
     */
    public String[] askToUserValidReader() {
        String[] resultReader = {"",""};//  Set resulting array 
        
        Reader reader;
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt, readerId, readerName;
        Boolean validReader = false;

        //  Keep asking to the user a valid readerId
        while (!validReader) {
            //  Ask readerId to the user
            readerId = String.valueOf(tools.getInt(sc, "Please insert ID of the reader"));
            //  Get reader for readerID
            reader = getReader(readerId);
            try {
                //  Set readerName to show it further, if readerId is not valid
                //  it will thrown a NullPointerException and ask user for a valid reader
                readerName = reader.getName();
                //  Set validReader as true to not ask reader again 
                validReader = true;

                //  Set resulting array 
                resultReader[0] = readerId;
                resultReader[1] = readerName;

            } //  If readerId is not valid, it offers searching options to the user
            catch (NullPointerException e) {
                System.out.println("Reader's Id does not exist in our database!");
                System.out.println("Would you like to search the reader by name or surname?");
                System.out.println("1 - Yes, By Name");
                System.out.println("2 - Yes, By Surname");
                System.out.println("0 - No, Back to General Menu");
                System.out.println();

                //  It will keep asking to the user for a valid input
                Boolean keepgoing = true;
                String target;
                while (keepgoing) {
                    //  Asking an input to the user
                    opt = sc.nextLine();
                    System.out.println();
                    switch (opt) {
                        case "1":
                            //  Asking an input to the user
                            target = tools.getString(sc, "Please insert Reader's Name");
                            searchReaders(2, target);
                            System.out.println();
                            keepgoing = false;
                            break;
                        case "2":
                            //  Asking an input to the user
                            target = tools.getString(sc, "Please insert Reader's Surname");
                            searchReaders(3, target);
                            System.out.println();
                            keepgoing = false;
                            break;
                        case "0":
                            keepgoing = false;
                            validReader = true;
                            break;
                        default:
                            System.out.println("Please select an option from the menu!");
                            break;
                    }
                }
            }
        }
        return resultReader;
    }
}
