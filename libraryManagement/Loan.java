import java.time.LocalDate;

public class Loan {
    int id;
    LocalDate borrowDate;
    LocalDate returnDate;
    LocalDate dueDate;
    public int getId() {
        return id;
    }
    Book book;
    final int MAX_DAYS=14;
    public Loan(Book book) {
        this.id=(int)(Math.random()*100);
        this.borrowDate = LocalDate.now();
        this.dueDate=borrowDate.plusDays(MAX_DAYS);
        this.book = book;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    
}
