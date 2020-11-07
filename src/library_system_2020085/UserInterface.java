/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package library_system_2020085;

//  Importing controllers
import java.util.Scanner;
import ls.controller.BookController;
import ls.controller.ReaderController;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class UserInterface {

    //  Declaring aour controllers
    private BookController books;
    private ReaderController readers;
    public static String separator = "-----------------------------------------------------------------------------------";

    public UserInterface() {
        //  Initializing controllers
        books = new BookController();
        readers = new ReaderController();

        //  Loading data
        books.loadBooks();
        readers.loadReaders();

        // Initializing user interface
        welcome();
        menu();

    }

    /*
     *  Method for print welcome message
     */
    public static void welcome() {
        System.out.println(separator);
        System.out.println("*                                 LIBRARY SYSTEM                                  *");
        System.out.println(separator);

    }

    /*
     *  Method for print menu
     */
    private void menu() {
        //Declaring variables
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt;
        Boolean keepgoing = true;//  Flag for menu

        //  It will keep asking to the user for a valid input
        while (keepgoing) {

            //  Printing menu
            System.out.println();
            System.out.println("----------------------------------GENERAL MENU-------------------------------------");
            System.out.println();
            System.out.println("1 - Search a book");
            System.out.println("2 - List all books");
            System.out.println("3 - Search a reader");
            System.out.println("4 - List all readers");
            System.out.println("5 - Register a borrowing");
            System.out.println("6 - Register a returning");
            System.out.println("7 - Borrowed books by specific Reader");
            System.out.println("0 - Exit");
            System.out.println();

            //  Asking an input to the user
            opt = sc.nextLine();
            System.out.println();

            //  Redirecting according the user's selection
            switch (opt) {
                case "1":
                    searchBooks();
                    break;
                case "2":
                    listBooks();
                    break;
                case "3":
                    searchReaders();
                    break;
                case "4":
                    listReaders();
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "0":
                    keepgoing = false;
                    System.out.println("Thanks for using Library System! Missing you already!");
                    break;
                default:
                    System.out.println("Please select an option from the menu!");
                    break;
            }
        }
    }

    /*
     *  Opt 1. Method for search books
     */
    private void searchBooks() {
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt, target;
        Boolean keepgoing = true;//  Flag for menu

        //  It will keep asking to the user for a valid input
        while (keepgoing) {
            System.out.println("1 - By Title");
            System.out.println("2 - By Author");
            System.out.println("0 - Back to General Menu");
            System.out.println();

            //  Asking an input to the user
            opt = sc.nextLine();
            System.out.println();
            switch (opt) {
                case "1":
                    //  Asking an input to the user
                    target = getString(sc, "Please insert Book's Title");
                    books.searchBooks(true, target);
                    keepgoing = false;
                    break;
                case "2":
                    //  Asking an input to the user
                    target = getString(sc, "Please insert Book's Author");
                    books.searchBooks(false, target);
                    keepgoing = false;
                    break;
                case "0":
                    keepgoing = false;
                    break;
                default:
                    System.out.println("Please select an option from the menu!");
                    break;
            }
        }
    }

    /*
     *  Opt 2. Method for display all the books
     */
    private void listBooks() {
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt;
        Boolean keepgoing = true;//  Flag for menu

        //  It will keep asking to the user for a valid input
        while (keepgoing) {
            System.out.println("1 - By Title");
            System.out.println("2 - By Author");
            System.out.println("0 - Back to General Menu");
            System.out.println();

            //  Asking an input to the user
            opt = sc.nextLine();
            System.out.println();
            switch (opt) {
                case "1":
                    books.sortBooks(true);
                    keepgoing = false;
                    break;
                case "2":
                    books.sortBooks(false);
                    keepgoing = false;
                    break;
                case "0":
                    keepgoing = false;
                    break;
                default:
                    System.out.println("Please select an option from the menu!");
                    break;
            }
        }
    }

    /*
     *  Opt 3. Method for search readers
     */
    private void searchReaders() {
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt, target;
        Boolean keepgoing = true;//  Flag for menu

        //  It will keep asking to the user for a valid input
        while (keepgoing) {
            System.out.println("1 - By ID");
            System.out.println("2 - By Name");
            System.out.println("3 - By Surname");
            System.out.println("0 - Back to General Menu");
            System.out.println();

            //  Asking an input to the user
            opt = sc.nextLine();
            System.out.println();
            switch (opt) {
                case "1":
                    //  Asking an input to the user
                    target = getString(sc, "Please insert Reader's Id");
                    readers.searchReaders(1, target);
                    keepgoing = false;
                    break;
                case "2":
                    //  Asking an input to the user
                    target = getString(sc, "Please insert Reader's Name");
                    readers.searchReaders(2, target);
                    keepgoing = false;
                    break;
                case "3":
                    //  Asking an input to the user
                    target = getString(sc, "Please insert Reader's Surname");
                    readers.searchReaders(3, target);
                    keepgoing = false;
                    break;
                case "0":
                    keepgoing = false;
                    break;
                default:
                    System.out.println("Please select an option from the menu!");
                    break;
            }
        }
    }
    
    /*
     *  Opt 4. Method for display all the readers
     */
    private void listReaders() {
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt;
        Boolean keepgoing = true;//  Flag for menu

        //  It will keep asking to the user for a valid input
        while (keepgoing) {
            System.out.println("1 - By ID");
            System.out.println("2 - By Name");
            System.out.println("3 - By Surname");
            System.out.println("0 - Back to General Menu");
            System.out.println();

            //  Asking an input to the user
            opt = sc.nextLine();
            System.out.println();
            switch (opt) {
                case "1":
                    readers.sortReaders(1);
                    keepgoing = false;
                    break;
                case "2":
                    readers.sortReaders(2);
                    keepgoing = false;
                    break;
                case "3":
                    readers.sortReaders(3);
                    keepgoing = false;
                    break;
                case "0":
                    keepgoing = false;
                    break;
                default:
                    System.out.println("Please select an option from the menu!");
                    break;
            }
        }
    }

    /*
     *  Method for valid input
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

}
