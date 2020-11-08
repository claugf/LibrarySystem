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
import ls.utils.Sorting;
import ls.utils.Searching;
import ls.utils.tools;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class BookController {

    //  Declaring variables
    private ArrayList<Book> books;
    private BufferedReader br;

    /*
     *  Declaring constructor and initializing variables
     */
    public BookController() {
        // Initializing Books array
        books = new ArrayList<>();
        try {
            // Instanciating BufferedReader with our books file
            br = new BufferedReader(new FileReader("Books.txt"));
        } catch (IOException e) {
            //  Printing the error
            e.printStackTrace();
        }
    }

    /*
     *  Method for load all the books from the data file
     */
    public void loadBooks() {
        try {
            //  Reading first line (TITLES) - we will ignore this line
            br.readLine();
            //  Reading second line (first book)
            String line = br.readLine();
            //  Going throught the file
            while (line != null) {
                //  Spliting each line to get the variables
                String[] data = line.split(";");
                //  Adding the data as a book object to the Books Array
                books.add(new Book(data[0], data[1], data[2]));
                //  Reading next file's line
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  Display all the books in the list
     */
    public void displayBooks(ArrayList<Book> listBooks) {
        //  Printing Headers with a proper format
        System.out.println(tools.separator);
        System.out.printf("%-10s", "BOOKID");
        System.out.printf("%-55s", "TITLE");
        System.out.println("AUTHOR");//  There is no need to format as it's the last field

        //  Going throught Books array
        for (Book b : listBooks) {
            //  Printing each book
            System.out.printf("%-10s", b.getBookId());
            System.out.printf("%-55s", b.getTitle());
            System.out.println(b.getAuthor());
        }
    }

    /*
     *  This method fill an String array by field of interest, to use it later 
     *  in searching or sorting methods
     */
    private String[] fillArray(Boolean sortbyTitle) {
        //  Creating a string array to add the field of interest
        String[] arrayBooks = new String[books.size()];
        //  Filling array with the field of interest
        for (int i = 0; i < books.size(); i++) {
            //  If Title is the field of interes we add it to the string array
            if (sortbyTitle) {
                arrayBooks[i] = books.get(i).getTitle();
            } else {
                //  If it is not Title , we add Author
                arrayBooks[i] = books.get(i).getAuthor();
            }
        }
        return arrayBooks;
    }

    /*
     *  This method sort books by 2 fields of interest and display the results
     */
    public void sortBooks(Boolean sortbyTitle) {
        //If Title is the field of interest
        if (sortbyTitle) {
            // We first sort by Author
            sorting(false);
            //  Then by Title
            sorting(true);
            //  Print headers
            System.out.println("----------------------------------BOOKS BY TITLE-----------------------------------");
        } else {
            // We first sort by Title
            sorting(true);
            //  Then by Author
            sorting(false);
            //  Print headers
            System.out.println("---------------------------------BOOKS BY AUTHOR-----------------------------------");
        }

        //  Now books are sorted by 2 fields
        displayBooks(books);
    }

    /*
     *  This method sort by one field of interest only
     */
    private void sorting(Boolean sortbyTitle) {
        //  Creating a string array to add the field of interest with method "fillArray"
        String[] arrayBooks = fillArray(sortbyTitle);

        //  Sorting array
        Sorting.mergeSort(arrayBooks);
        //  Declaring temporary array to fill it later with sorted array
        ArrayList<Book> tempB = new ArrayList<>();
        //  Going throught array of sorted strings
        for (String elemArrayBook : arrayBooks) {
            //  Going throught array of books
            for (Book book : books) {
                //If Title is the field of interest, we will compare it later on
                if (sortbyTitle) {
                    // Asking if the field of the sorted array is the same that the book
                    //  to add it into the temporary array of books
                    if (book.getTitle().equals(elemArrayBook)) {
                        //  Asking if the element is not already in the arraylist before to add it
                        if (!tempB.contains(book)) {
                            tempB.add(book);
                        }
                    }
                } else {
                    //  If Title is not hte field of interest,we compare by author
                    if (book.getAuthor().equals(elemArrayBook)) {
                        //  Asking if the element is not already in the arraylist before to add it
                        if (!tempB.contains(book)) {
                            tempB.add(book);
                        }
                    }
                }
            }
        }

        //  Now we replace books array with our new sorted array of books
        books = tempB;
    }

    /*
     *  This method search books by field of interest, and in case there is none
     *  it search by the next field of interest
     */
    public void searchBooks(Boolean searchbyTitle, String target) {
        //  First sort BooksList
        sorting(searchbyTitle);
        //  Declaring list of findings and Searching by given target
        ArrayList<Book> findings = searching(searchbyTitle, target);

        //If Title is the field of interest
        if (searchbyTitle) {
            // If there is finding, display results, if there is no findings, we search by author
            if (!findings.isEmpty()) {
                //  Print headers
                System.out.println("----------------------------------BOOKS BY TITLE-----------------------------------");
            } else {
                //  First sort BooksList by Author
                sorting(!searchbyTitle);
                //  Searching by given target
                findings = searching(!searchbyTitle, target);
                if (!findings.isEmpty()) {
                    System.out.println("None title was found with that search criteria. However, here some author were found.");
                    //  Print headers
                    System.out.println("---------------------------------BOOKS BY AUTHOR-----------------------------------");
                } else {
                    System.out.println("Sorry! None book was found with that criteria. Either by Title or Author.");
                }
            }
        } else {
            // If there is finding, display results, if there is no findings, we search by title
            if (!findings.isEmpty()) {
                //  Print headers
                System.out.println("---------------------------------BOOKS BY AUTHOR-----------------------------------");
            } else {
                //  First sort BooksList by Title
                sorting(!searchbyTitle);
                //  Searching by given target
                findings = searching(!searchbyTitle, target);
                if (!findings.isEmpty()) {
                    System.out.println("None author was found with that search criteria. However, here some titles were found.");
                    //  Print headers
                    System.out.println("----------------------------------BOOKS BY TITLE-----------------------------------");
                } else {
                    System.out.println("Sorry! None book was found with that criteria. Either by Title or Author.");
                }
            }
        }

        //  Now display found books, if there is any
        if (!findings.isEmpty()) {
            displayBooks(findings);
        }
    }

    /*
     *  This method search by one field of interest only
     */
    private ArrayList<Book> searching(Boolean searchbyTitle, String target) {
        //  Creating a string array to add the field of interest with method "fillArray"
        String[] arrayBooks = fillArray(searchbyTitle);

        //  Searching in array
        ArrayList<String> findings = Searching.linealSearch(arrayBooks, target);
        //  Declaring temporary array to fill it later with sorted array
        ArrayList<Book> tempB = new ArrayList<>();

        //  Going throught array of findings strings
        for (String finding : findings) {
            //  Going throught array of books
            for (Book book : books) {
                //If Title is the field of interest, we will compare it later on
                if (searchbyTitle) {
                    // Asking if the finding is the same that the book
                    //  to add it into the temporary array of books
                    if (book.getTitle().equals(finding)) {
                        //  Asking if the element is not already in the arraylist before to add it
                        if (!tempB.contains(book)) {
                            tempB.add(book);
                        }
                    }
                } else {
                    //  If Title is not hte field of interest,we compare by author
                    if (book.getAuthor().equals(finding)) {
                        //  Asking if the element is not already in the arraylist before to add it
                        if (!tempB.contains(book)) {
                            tempB.add(book);
                        }
                    }
                }
            }
        }

        //  Now we return all books found list
        return tempB;
    }

    /*
     *  This method validate if the given bookId is registered in the database
     *  and return the book if it exists, if not return null
     */
    public Book getBook(String readerId) {
        for (Book book : books) {
            if (book.getBookId().equals(readerId)) {
                return book;
            }
        }
        return null;
    }

    /*
     *  This method ask to the user a valid book, it returns a string array with bookId and bookTitle
     *  in case user give up, it will return an empty array of Strings 
     */
    public String[] askToUserValidBook() {
        String[] resultBook =  {"",""};;//  Set resulting array 

        Book book;
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt, bookId, bookTitle;
        Boolean validbook = false;

        //  Keep asking to the user a valid bookId
        while (!validbook) {
            //  Ask bookId to the user
            bookId = String.valueOf(tools.getInt(sc, "Please insert ID of the book"));
            //  Get book for bookId
            book = getBook(bookId);
            try {
                //  Set bookTitle to show it further, if bookId is not valid
                //  it will thrown a NullPointerException and ask user for a valid book
                bookTitle = book.getTitle();
                //  Set validBook as true to not ask book again 
                validbook = true;

                //  Set resulting array 
                resultBook[0] = bookId;
                resultBook[1] = bookTitle;
            }//  If bookId is not valid, it offers searching options to the user 
            catch (NullPointerException e) {
                System.out.println("Book's Id does not exist in our database!");
                System.out.println("Would you like to search the book by title or author?");
                System.out.println("1 - Yes, By Title");
                System.out.println("2 - Yes, By Author");
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
                            target = tools.getString(sc, "Please insert Book's Title");
                            searchBooks(true, target);
                            System.out.println();
                            keepgoing = false;
                            break;
                        case "2":
                            //  Asking an input to the user
                            target = tools.getString(sc, "Please insert Book's Author");
                            searchBooks(false, target);
                            System.out.println();
                            keepgoing = false;
                            break;
                        case "0":
                            keepgoing = false;
                            validbook = true;
                            break;
                        default:
                            System.out.println("Please select an option from the menu!");
                            break;
                    }
                }

            }
        }
        return resultBook;
    }
}
