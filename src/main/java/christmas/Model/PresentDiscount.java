package christmas.Model;

public class PresentDiscount {
    private Order order;
    boolean present;

    public PresentDiscount(Order order) {
        this.order = order;
        this.present = this.order.isOverPresentPrice();
    }

    public boolean getPresent() {
        return present;
    }
}
