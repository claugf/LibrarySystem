/*
 * Main Assignment
 * Author: Claudia Gonzalez
 * Student Number: 2020085
 */
package ls.model;

import java.time.LocalDate;

/**
 *
 * @author claudialuizagonzalezferrufino
 */
public class Borrowing {

    private String borrowingId;
    private String readerId;
    private String bookId;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public Borrowing(String borrowingId, String readerId, String bookId, LocalDate borrowingDate, LocalDate returnDate) {
        this.borrowingId = borrowingId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public String getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(String borrowingId) {
        this.borrowingId = borrowingId;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrowing{" + "borrowingId=" + borrowingId + ", readerId=" + readerId + ", bookId=" + bookId + ", borrowingDate=" + borrowingDate + ", returnDate=" + returnDate + '}';
    }

}
