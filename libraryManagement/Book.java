public class Book{
    String title;
    String author;
    int numberOfBooks;
    int id;
    public Book(int id,String title, String author, int numberOfBooks) {
        this.id=id;
        this.title = title;
        this.author = author;
        this.numberOfBooks = numberOfBooks;
    }
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", numberOfBooks=" + numberOfBooks + ", id=" + id + "]";
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getNumberOfBooks() {
        return numberOfBooks;
    }
    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
    public boolean isBookAvailable(){
        return numberOfBooks>0;
    }
    public int getId() {
        return id;
    }
}