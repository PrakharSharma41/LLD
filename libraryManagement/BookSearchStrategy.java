public interface BookSearchStrategy {
    Book search(String input, Catalog catalog);
}