package christmas.model.dateDiscount;

import christmas.model.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekdaysDiscount extends DateDiscount {
    private int weekdaysDiscountPrice;

    public WeekdaysDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekdaysDate()) {
            this.weekdaysDiscountPrice = order.countDessertMenu() * 2023;
        }
    }

    public String getMessage() {
        return December.Message(December.WEEKDAY);
    }

    public int getPrice() {
        return this.weekdaysDiscountPrice;
    }
}
