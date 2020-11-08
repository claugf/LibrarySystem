/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import ls.model.Borrowing;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import ls.utils.tools;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class BorrowingController {

    //  Declaring variables for bussines logic
    private ArrayList<Borrowing> borrowings;
    private ReaderController readers;
    private BookController books;
    //  Declaring variables for manage file
    private BufferedReader br;
    private BufferedWriter bw;
    private File file;
    private DateTimeFormatter myFormat;

    /*
     *  Declaring constructor and initializing variables
     */
    public BorrowingController() {
        //  Initializating controllers
        readers = new ReaderController();
        books = new BookController();

        //  Load readers and books
        readers.loadReaders();
        books.loadBooks();
        //  Declaring file
        file = new File("Borrowings.txt");

        //  Setting format for dates
        myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        prepareFile();
        loadBorrowings();
    }

    /*
     *  Method for check data file, if it exists prepare reader
     *  if not create file and headers
     */
    private void prepareFile() {
        // Initializing Borrowings list
        borrowings = new ArrayList<>();
        try {
            //  Checking if the file exists, if not, it creates the file
            if (!file.exists()) {
                //  Create file
                file.createNewFile();
                //  Open the file for writing
                bw = new BufferedWriter(new FileWriter(new File(file.getName()), false));

                // Write headers in the file.
                bw.write("BORROWINGID;READERID;BOOKID;BORROWINGDATE;RETURNDATE");
                bw.close();
            }
        } catch (IOException e) {
            //  Printing the error
            System.err.println("An IOException was caught!");
            e.printStackTrace();
        }
    }

    /*
     *  Method for load all the borrowings from the data file
     */
    private void loadBorrowings() {
        //  Initializating array, and clearing in case there is data already
        borrowings = new ArrayList<>();

        try {
            // Instanciating BufferedReader with our borrowings file
            br = new BufferedReader(new FileReader(file));

            //  Reading first line (TITLES) - we will ignore this line
            br.readLine();
            //  Reading second line (first borrowing)
            String line = br.readLine();
            //  Going throught the file
            while (line != null) {
                //  Spliting each line to get the variables
                String[] data = line.split(";");
                //  Preparing dates before add them to the arraylist
                LocalDate borrowingDate;
                LocalDate returnDate;
                try {
                    //  Formating borrowingDate
                    borrowingDate = LocalDate.parse(data[3], myFormat);
                    //  Validating if returnDate is not null
                    if (data[4].equals("--")) {
                        returnDate = null;
                    } else {
                        //  Formating returnDate
                        returnDate = LocalDate.parse(data[4], myFormat);
                    }
                    //  Adding the data as a book object to the Books Array
                    borrowings.add(new Borrowing(data[0], data[1], data[2], borrowingDate, returnDate));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("An ParsingDatesException was caught");
                }
                //  Reading next file's line
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            //  Printing the error
            System.err.println("An IOException was caught!");
            e.printStackTrace();
        }
    }

    /*
     *  Display all the borrowings in the list
     */
    public void displayBorrowings(ArrayList<Borrowing> borrowingsList) {
        //  Print headers
        System.out.println("--------------------------------------------------------BORROWINGS--------------------------------------------------------");
        //  If list of borrowings is not empty, we proceed to display
        if (!borrowingsList.isEmpty()) {
            //  Printing Headers with a proper format
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s", "BORROWINGID");
            System.out.printf("%-10s", "READERID");
            System.out.printf("%-15s", "READERNAME");
            System.out.printf("%-10s", "BOOKID");
            System.out.printf("%-50s", "BOOKTITLE");
            System.out.printf("%-15s", "BORROWINGDATE");
            System.out.println("RETURNDATE");//  There is no need to format as it's the last field

            //  Going throught Books array
            for (Borrowing b : borrowingsList) {
                //  Printing each book
                System.out.printf("%-12s", b.getBorrowingId());
                System.out.printf("%-10s", b.getReaderId());
                System.out.printf("%-15s", readers.getReader(b.getReaderId()).getName());
                System.out.printf("%-10s", b.getBookId());
                System.out.printf("%-50s", books.getBook(b.getBookId()).getTitle());
                System.out.printf("%-15s", b.getBorrowingDate());
                if (b.getReturnDate() == null) {
                    System.out.println("--");
                } else {
                    System.out.println(b.getReturnDate());
                }
            }
        } //    If there is no borrowing registered, show a message
        else {
            System.out.println("There is no borrowing registered");
        }
    }

    /*
     *  Method for add a borrowing
     */
    public void addBorrowing(String readerId, String bookId) {
        //  Generating an Borrowing id
        String id;
        //  If there is none borrowing, we start the counting with 1000
        if (borrowings.isEmpty()) {
            id = "1000";
        } else {
            //  Otherwise, we count how many borrowing are for generate a new id
            id = String.valueOf(1000 + borrowings.size());
        }

        //  Set current time for date of the borrowing
        LocalDate borrowingDate = LocalDate.now();
        //  Formating borrowing date to write it in the file
        String formattedBorrowingDate = borrowingDate.format(myFormat);

        try {
            //  Open the file for writing, without remove its current content
            bw = new BufferedWriter(new FileWriter(new File(file.getName()), true));

            //  Write new borrowing in the file.
            bw.newLine();
            //  Writing new borrowing, we set return date with null (--)
            bw.write(id + ";" + readerId + ";" + bookId + ";" + formattedBorrowingDate + ";--");
            bw.close();
        } catch (IOException e) {
            //  Printing the error
            System.err.println("An IOException was caught!");
            e.printStackTrace();
        }

        //  Refresh borrowing list
        loadBorrowings();
    }

    /*
     *  Get all the borrowings by readerId, if is onlyNoReturned, list all borrowed books without return
     *  if is not onyNo Returned, list all borrowings returned and no returned
     */
    public ArrayList<Borrowing> getBorrowingsByReader(String readerId, boolean onlyNoReturned) {
        //  Declaring temporary array to fill it later with borrowings by user
        ArrayList<Borrowing> tempB = new ArrayList<>();

        //  Going throught Borrowings array
        for (Borrowing b : borrowings) {
            //  Ask if the parameter ask for only no returned borrowings
            if (onlyNoReturned) {
                //  If is the reader that we are looking for, we add it to the temporary arraylist
                if (b.getReaderId().equals(readerId) && b.getReturnDate() == null) {
                    tempB.add(b);
                }
            } else {
                //  If is the reader that we are looking for, we add it to the temporary arraylist
                if (b.getReaderId().equals(readerId)) {
                    tempB.add(b);
                }
            }
        }

        //  Return found borrowings
        return tempB;
    }

    /*
     *  Get all the borrowings by readerId
     */
    public boolean displayBorrowingsByReader(String readerId, boolean onlyNoReturned) {
        //  Declaring temporary array to fill it later with borrowings by user
        ArrayList<Borrowing> tempB = new ArrayList<>();

        //  Get Borrowings by Reader
        tempB = getBorrowingsByReader(readerId, onlyNoReturned);

        //  If we found borrowings we proceed to display them
        if (!tempB.isEmpty()) {
            //  Display all the borrowings of the reader
            displayBorrowings(tempB);
            return true;
        } else {
            return false;
        }

    }

    /*
     *  Method for register a return
     */
    public void returnBorrowing(String borrowingId) {
        //  Set current time for date of the return
        LocalDate returnDate = LocalDate.now();
        //  Formating borrowing date to write it in the file
        String formattedreturnDate = returnDate.format(myFormat);

        try {
            // Instanciating BufferedReader with our borrowings file
            br = new BufferedReader(new FileReader(file));
            String line, modifiedline;
            // Here we save the modified file
            StringBuffer inputBuffer = new StringBuffer();

            //  We append first line without processing as there is the TITLES
            line = br.readLine();
            inputBuffer.append(line);

            //  For the rest of the lines, we need to check borrowingId and modified its return
            while ((line = br.readLine()) != null) {
                //  Add new line
                inputBuffer.append('\n');
                //  Spliting each line to get the variables
                String[] data = line.split(";");

                //  If it is the borrowing that we are looking for, we modify its return
                if (data[0].equals(borrowingId)) {
                    //  Setting modifiedline for update borrowing
                    modifiedline = data[0] + ";" + data[1] + ";" + data[2] + ";" + data[3] + ";" + formattedreturnDate;

                    //  Append modifiedline
                    inputBuffer.append(modifiedline);
                } //    If it is not the one we are looking for, just append the same line
                else {
                    inputBuffer.append(line);
                }
            }
            br.close();

            String inputStr = inputBuffer.toString();

            // Write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(file.getName());
            fileOut.write(inputStr.getBytes());
            fileOut.close();

            System.out.println("Return registered successfully!");

        } catch (IOException e) {
            //  Printing the error
            System.err.println("An IOException was caught!");
            e.printStackTrace();
        }

        //  Refresh borrowing list
        loadBorrowings();
    }

    /*
     *  This method validate if the given borrowingId is registered in the database
     *  and return the borrowing if it exists, if not return null
     */
    public Borrowing getBorrowing(String borrowingId) {
        for (Borrowing b : borrowings) {
            if (b.getBorrowingId().equals(borrowingId)) {
                return b;
            }
        }
        return null;
    }

    /*
     *  This method ask to the user a valid borrowingId, it returns a string array with 
     *  borrowingId, readerId, readerName, bookId, bookTitle in case is valid
     *  and a empty string in case it is not a valid borrowingId
     */
    public String[] validateBorrowing() {
        String[] resultborrowing = {"", "", "", "", ""};
        String input;
        Scanner sc = new Scanner(System.in);// Initializing scanner
        Borrowing borrowing;

        //  Asking for a valid borrowingId to the user
        System.out.println();
        input = String.valueOf(tools.getInt(sc, "Please insert ID of the borrowing that you like to return"));

        //  Get borrowing for borrowingId
        borrowing = getBorrowing(input);

        try {
            //  Set borrowingId, if it is not valid
            //  it will thrown a NullPointerException and report to user 
            resultborrowing[0] = borrowing.getBorrowingId();
            resultborrowing[1] = borrowing.getReaderId();
            resultborrowing[2] = readers.getReader(borrowing.getReaderId()).getName();
            resultborrowing[3] = borrowing.getBookId();
            resultborrowing[4] = books.getBook(borrowing.getBookId()).getTitle();

        }//  If borrowingId is not valid, it reports to the user 
        catch (NullPointerException e) {
            //borrowingId = "";
            System.out.println("Borrowing's Id does not exist in our database!");
        }
        return resultborrowing;
    }
    
    public void listKardex(){
        String[] reader;
        String readerId;
                //  Asking for a valid reader to the user
        reader = readers.askToUserValidReader();

        //  If readerId is valid, we proceed to display borrowings by reader
        if (!reader[0].equals("")) {
            //  Set readerId 
            readerId = reader[0];

            //  Display all the borrowings of the reader
            if (!displayBorrowingsByReader(readerId, false)) {
                System.out.println("No borrowings found for this reader");
            }
        }
    }
    
        public void listNoReturnedBorrowings(){
        String[] reader;
        String readerId;
                //  Asking for a valid reader to the user
        reader = readers.askToUserValidReader();

        //  If readerId is valid, we proceed to display borrowings by reader
        if (!reader[0].equals("")) {
            //  Set readerId 
            readerId = reader[0];

            //  Display all the borrowings of the reader
            if (!displayBorrowingsByReader(readerId, true)) {
                System.out.println("No borrowings found for this reader");
            }
        }
    }
}
