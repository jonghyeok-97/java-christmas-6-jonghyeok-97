package christmas.model;

import static christmas.Constants.ZERO;

public class GiftEvent {
    private static final int GIFT_AMOUNT = 25000;

    private final boolean isFree;
    private final int amount;

    public GiftEvent(Order order) {
        this.isFree = setFree(order);
        this.amount = setAmount(order);
    }

    public boolean getFree() {
        return isFree;
    }

    public int getAmount() {
        return amount;
    }

    private boolean setFree(Order order) {
        return order.isOverMinGiftAmount();
    }

    private int setAmount(Order order) {
        if (order.isOverMinGiftAmount()) {
            return GIFT_AMOUNT;
        }
        return ZERO;
    }
}