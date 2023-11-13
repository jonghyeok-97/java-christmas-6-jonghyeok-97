package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class SpecialDiscount extends DateDiscount {
    private int specialDiscountPrice;

    public SpecialDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isSpecialDate() && order.isOverMinDiscountPrice()) {
            this.specialDiscountPrice = 1000;
        }
    }

    public String getMessage() {
        return December.Message(December.SPECIAL);
    }

    public int getPrice() {
        return this.specialDiscountPrice;
    }
}