public class Utility {
    public static Book getBookCopy(Book book){ // we can create separate book copy class instead of creating new book object like this
        Book copyBook=new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getNumberOfBooks());
        return copyBook;
    }
}
