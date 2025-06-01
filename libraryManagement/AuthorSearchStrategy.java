public class AuthorSearchStrategy implements BookSearchStrategy {
    @Override
    public Book search(String author, Catalog catalog) {
        return catalog.getBookByAuthor(author);
    }
}