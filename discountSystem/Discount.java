public abstract class Discount {
    private Discount next;

    public Discount() {
        this.next = null;
    }

    public void setNext(Discount nxt) {
        this.next = nxt;
    }

    public Discount getNext() {
        return next;
    }

    public void applyDiscount(Cart cart) {
        if (isApplicable(cart)) {
            double discount = getDiscount(cart);
            cart.applyDiscount(discount);
            System.out.println(name() + " applied: " + discount);
            if (!isCombinable()) {
                return;
            }
        }
        if (next != null) {
            next.applyDiscount(cart);
        }
    }

    public abstract boolean isApplicable(Cart cart);
    public abstract double getDiscount(Cart cart);
    public boolean isCombinable() {
        return true;
    }
    public abstract String name();
}
