package christmas;

import christmas.Model.Order;
import christmas.Model.VisitDate;
import org.mockito.internal.matchers.Or;

public class SpecialDiscount {
    private boolean isSpecialDiscount;
    private int specialDiscountPrice;

    public SpecialDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isSpecialDate() && order.isOverMinDiscountPrice()) {
            this.isSpecialDiscount = true;
            this.specialDiscountPrice = 1000;
        }
    }
}
