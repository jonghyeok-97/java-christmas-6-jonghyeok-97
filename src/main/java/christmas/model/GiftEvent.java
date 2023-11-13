package christmas.model;

public class GiftEvent {
    private boolean isFree;
    private int amount;

    public GiftEvent(Order order) {
        if (order.isOverMinPresentPrice()) {
            this.isFree = true;
            this.amount = 25000;
        }
    }

    public boolean getFree() {
        return isFree;
    }

    public int getAmount() {
        return amount;
    }
}
