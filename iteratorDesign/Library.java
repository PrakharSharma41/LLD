package iteratorDesign;

import java.util.List;

public class Library implements Aggregator{ // it is concrete iterator like arraylist
    private List<Book>booksList;

    public Library(List<Book> booksList) {
        this.booksList = booksList;
    }
    public Iterator createIterator(){
        return new BookIterator(booksList);
    }
}
