class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product prod, int qty) {
        this.product  = prod;
        this.quantity = qty;
    }

    public double itemTotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }
}