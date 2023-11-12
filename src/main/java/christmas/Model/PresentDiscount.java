package christmas.Model;

public class PresentDiscount {
    boolean isPresent;

    public PresentDiscount(Order order) {
        if (order.isOverMinDiscountPrice()) {
            this.isPresent = order.isOverMinPresentPrice();
        }
    }

    public boolean getPresent() {
        return isPresent;
    }
}
