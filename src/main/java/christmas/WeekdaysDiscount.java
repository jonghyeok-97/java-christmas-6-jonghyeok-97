package christmas;

import christmas.Model.Order;
import christmas.Model.VisitDate;

public class WeekdaysDiscount {
    private boolean weekdaysDate = false;
    private int weekdaysDiscountPrice = 0;

    public WeekdaysDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekdaysDate()) {
            weekdaysDate = true;
            int dessertPrice = order.findPriceOfDessert();
            int countTotalMenu = order.countOrderedMenu();
        }
    }
}
