package christmas.model.dateDiscount;

import christmas.model.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekendDiscount extends DateDiscount{
    private int weekendDiscountPrice;

    public WeekendDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekendDate()) {
            this.weekendDiscountPrice = order.countMainMenu() * 2023;
        }
    }

    public String getMessage() {
        return December.Message(December.WEEKEND);
    }

    public int getPrice() {
        return this.weekendDiscountPrice;
    }

}
