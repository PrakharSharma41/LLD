import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    Map<String,Book>bookByTitle;
    Map<String,List<Book>>bookByAuthor;
    Map<Integer,Book>bookById;
    
    public Catalog() {
        bookByTitle=new HashMap<>();
        bookByAuthor=new HashMap<>();
        bookById=new HashMap<>();
    }
    public void addBook(int id,String title,String author,int numberOfBooks){
        Book book = new Book(id, title, author, numberOfBooks);
        bookByTitle.put(title, book);

        bookById.put(id, book);

        // Add book to list for this author
        bookByAuthor.computeIfAbsent(author, k -> new ArrayList<>()).add(book);
    }
    public void changeBookCount(int bookId,int changeCountBy){
        int oldCount=bookById.get(bookId).getNumberOfBooks();
        bookById.get(bookId).setNumberOfBooks(oldCount+changeCountBy);
    }
    public boolean isBookAvailable(int bookId){
        Book book=bookById.get(bookId);
        if(book==null)return false;
        return book.getNumberOfBooks()>0;
    }
    public Book getBookByAuthor(String author){ // just returning one book
        List<Book> books = bookByAuthor.get(author);
        if (books == null) return null;
        for (Book book : books) {
            if (book.isBookAvailable()) {
                return Utility.getBookCopy(book);
            }
        }
        return null;
    }
    public Book getBookByTitle(String title){
        Book book=bookByTitle.get(title);
        Book copyBook=Utility.getBookCopy(book);
        return copyBook;
    }
    public Book getBookById(Integer id){
        Book book=bookById.get(id);
        Book copyBook=Utility.getBookCopy(book);
        return copyBook;
    }
    
}
