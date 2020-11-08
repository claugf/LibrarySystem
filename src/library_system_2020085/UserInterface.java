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
import ls.controller.BorrowingController;
import ls.utils.tools;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class UserInterface {

    //  Declaring aour controllers
    private BookController books;
    private ReaderController readers;
    private BorrowingController borrowings;

    public UserInterface() {
        //  Initializing controllers
        books = new BookController();
        readers = new ReaderController();
        borrowings = new BorrowingController();

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
        System.out.println(tools.separator);
        System.out.println("*                                 LIBRARY SYSTEM                                  *");
        System.out.println(tools.separator);

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
            System.out.println("6 - Register a return");
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
                    addBorrowing();
                    break;
                case "6":
                    registerReturn();
                    break;
                case "7":
                    listBorrowingsbyReader();
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
                    target = tools.getString(sc, "Please insert Book's Title");
                    books.searchBooks(true, target);
                    keepgoing = false;
                    break;
                case "2":
                    //  Asking an input to the user
                    target = tools.getString(sc, "Please insert Book's Author");
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
                    target = tools.getString(sc, "Please insert Reader's Id");
                    readers.searchReaders(1, target);
                    keepgoing = false;
                    break;
                case "2":
                    //  Asking an input to the user
                    target = tools.getString(sc, "Please insert Reader's Name");
                    readers.searchReaders(2, target);
                    keepgoing = false;
                    break;
                case "3":
                    //  Asking an input to the user
                    target = tools.getString(sc, "Please insert Reader's Surname");
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
     *  Opt 5. Method for display all borrowings
     */
    private void addBorrowing() {
        String[] reader;
        String[] book;
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt, readerId, readerName, bookId, bookTitle;

        //  Asking for a valid reader to the user
        reader = readers.askToUserValidReader();

        //  If readerId is valid, we proceed to ask for bookId
        if (!reader[0].equals("")) {
            //  Set readerId 
            readerId = reader[0];
            //  Set readerName to show it further
            readerName = reader[1];

            //  Asking for a valid book to the user
            book = books.askToUserValidBook();

            //  If bookId is valid, we proceed to add borrowing
            if (!book[0].equals("")) {
                //  Set bookId 
                bookId = book[0];
                //  Set bookTitle to show it further
                bookTitle = book[1];

                //  Now we ask to the user if he wants to add the borrowing
                System.out.println();
                System.out.println("Would you like to add the following borrowing?");
                System.out.println(readerId + " " + readerName + " - " + bookId + " " + bookTitle);
                System.out.println();
                System.out.println("1 - Confirm, Add Borrowing");
                System.out.println("0 - Cancel, Back to General Menu");

                //  It will keep asking to the user for a valid input
                Boolean keepgoing = true;
                while (keepgoing) {
                    //  Asking an input to the user
                    opt = sc.nextLine();
                    System.out.println();
                    if (opt.equals("1")) {
                        borrowings.addBorrowing(readerId, bookId);
                        System.out.println("Borrowing added successfully to our database!");
                        keepgoing = false;
                    } else {
                        if (opt.equals("0")) {
                            keepgoing = false;
                        } else {
                            System.out.println("Please select an option from the menu!");
                        }
                    }
                }
            }
        }
    }

    /*
     *  Opt 6. Method for register a return of a borrowed book
     */
    private void registerReturn() {
        String[] borrowing;
        String[] reader;
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt, borrowingId, readerId, readerName, bookId, bookTitle;

        //  Asking for a valid reader to the user
        reader = readers.askToUserValidReader();

        //  If readerId is valid, we proceed to display borrowings by reader
        if (!reader[0].equals("")) {
            //  Set readerId 
            readerId = reader[0];

            //  Display all the borrowings of the reader
            if (borrowings.displayBorrowingsByReader(readerId, true)) {

                //  Asking for a valid borrowing to the user
                borrowing = borrowings.validateBorrowing();

                //  If borrowingId is valid, we proceed to confirm
                if (!borrowing[0].equals("")) {

                    //  Getting borrowing data
                    borrowingId = borrowing[0];
                    readerName = borrowing[2];
                    bookId = borrowing[3];
                    bookTitle = borrowing[4];

                    //  Now we ask to the user if he wants to add the borrowing
                    System.out.println();
                    System.out.println("Would you like to proceed to return this book?");
                    System.out.println("Reader: " + readerId + " " + readerName + " - Book: " + bookId + " " + bookTitle);
                    System.out.println();
                    System.out.println("1 - Confirm, Return Book");
                    System.out.println("0 - Cancel, Back to General Menu");

                    //  It will keep asking to the user for a valid input
                    Boolean keepgoing = true;
                    while (keepgoing) {
                        //  Asking an input to the user
                        opt = sc.nextLine();
                        System.out.println();
                        if (opt.equals("1")) {
                            borrowings.returnBorrowing(borrowingId);
                            keepgoing = false;
                        } else {
                            if (opt.equals("0")) {
                                keepgoing = false;
                            } else {
                                System.out.println("Please select an option from the menu!");
                            }
                        }
                    }
                }
            } else {
                System.out.println("No borrowings found for this reader");
            }
        }
    }

    /*
     *  Opt 7. Method for list borrowings by reader
     */
    private void listBorrowingsbyReader() {
        Scanner sc = new Scanner(System.in);// Initializing scanner
        String opt;
        Boolean keepgoing = true;//  Flag for menu

        System.out.println("1 - Kardex");
        System.out.println("2 - Only no returned books");
        System.out.println("0 - Back to General Menu");
        System.out.println();
        
        //  It will keep asking to the user for a valid input
        while (keepgoing) {

            //  Asking an input to the user
            opt = sc.nextLine();
            System.out.println();
            switch (opt) {
                case "1":
                    borrowings.listKardex();
                    keepgoing = false;
                    break;
                case "2":
                    borrowings.listNoReturnedBorrowings();
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
}
