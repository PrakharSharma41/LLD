public class TitleSearchStrategy implements BookSearchStrategy {
    @Override
    public Book search(String title, Catalog catalog) {
        return catalog.getBookByTitle(title);
    }
}