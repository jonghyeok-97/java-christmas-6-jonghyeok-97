package christmas.Model;

import org.mockito.internal.matchers.Or;

public class WeekendDiscount {

    public WeekendDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekendDate() && order.isOverMinDiscountPrice()) {

        }
    }
}
