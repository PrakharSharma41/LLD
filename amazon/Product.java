import java.util.List;

public class Product {
    int id;
    String name;
    String company;
    int count;
    Category category;
    List<Review> reviews;
    public Product(int id, String name, String company, int count, Category category, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.count = count;
        this.category = category;
        this.reviews = reviews;
    }
}
