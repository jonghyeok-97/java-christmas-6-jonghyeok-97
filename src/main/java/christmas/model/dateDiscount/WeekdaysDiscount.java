package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.DateDiscount;

public class WeekdaysDiscount extends DateDiscount {
    private int weekdaysDiscountPrice;

    public WeekdaysDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekdaysDate() && order.isOverMinDiscountPrice()) {
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
