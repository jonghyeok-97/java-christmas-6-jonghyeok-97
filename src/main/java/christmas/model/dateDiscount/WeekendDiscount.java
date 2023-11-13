package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekendDiscount extends DateDiscount{

    private int weekendDiscountPrice;
    public WeekendDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekendDate() && order.isOverMinDiscountPrice()) {
            int mainPrice = order.findPriceOfMain();
            int countMainMenu = order.countMainMenu();
            this.weekendDiscountPrice = mainPrice - countMainMenu * 2023;
        }
    }

    public String getMessage() {
        return December.Message(December.WEEKEND);
    }

    public int getPrice() {
        return this.weekendDiscountPrice;
    }

}
