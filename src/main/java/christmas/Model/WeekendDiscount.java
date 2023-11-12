package christmas.Model;

import org.mockito.internal.matchers.Or;

public class WeekendDiscount {
    private boolean isWeekendDiscount;
    private int weekendDiscountPrice;
    public WeekendDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekendDate() && order.isOverMinDiscountPrice()) {
            this.isWeekendDiscount = true;
            int mainPrice = order.findPriceOfMain();
            int countMainMenu = order.countMainMenu();
        }
    }
}
