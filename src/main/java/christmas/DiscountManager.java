package christmas;

import java.util.List;

public class DiscountManager {
    private final List<December> discountTypes;

    public DiscountManager(int day) {
        this.discountTypes = December.checkDiscountType(day);
    }

    public boolean isOverMinPrice(int price) {
        return false;
    }


    public int sumDiscountPrices(List<December> discountTypes) {
        return 0;
    }

}
