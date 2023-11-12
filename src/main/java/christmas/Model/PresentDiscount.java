package christmas.Model;

public class PresentDiscount {
    private Order order;
    boolean isPresent;

    public PresentDiscount(Order order) {
        if (order.isOverMinDiscountPrice()) {
            this.order = order;
            this.isPresent = this.order.isOverMinPresentPrice();
        }
    }

    public boolean getPresent() {
        return isPresent;
    }
}
