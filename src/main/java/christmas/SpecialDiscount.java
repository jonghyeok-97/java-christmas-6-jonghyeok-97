package christmas;

import christmas.Model.Order;
import christmas.Model.VisitDate;
import org.mockito.internal.matchers.Or;

public class SpecialDiscount {

    public SpecialDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isSpecialDate() && order.isOverMinDiscountPrice()) {

        }
    }
}
