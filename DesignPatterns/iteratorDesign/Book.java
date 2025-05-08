package DesignPatterns.iteratorDesign;

public class Book {
    private int price;
    private String bookName;
    public Book(int price, String bookName) {
        this.price = price;
        this.bookName = bookName;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
}
