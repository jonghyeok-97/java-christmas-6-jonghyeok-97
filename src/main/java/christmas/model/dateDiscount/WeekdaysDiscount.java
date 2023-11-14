package christmas.model.dateDiscount;

import static christmas.Constants.ZERO;

import christmas.model.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekdaysDiscount extends DateDiscount {
    private static final int WEEKDAY_AMOUNT_PER_DESSERT_MENU = 2023;

    private final int weekdaysAmount;

    public WeekdaysDiscount(VisitDate visitDate, Order order) {
        this.weekdaysAmount = calculateWeekdaysAmount(visitDate, order);
    }

    @Override
    public String getDiscountType() {
        return December.find(December.WEEKDAY);
    }

    @Override
    public int getAmount() {
        return this.weekdaysAmount;
    }

    private int calculateWeekdaysAmount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekdaysDate()) {
            return order.countDessertMenu() * WEEKDAY_AMOUNT_PER_DESSERT_MENU;
        }
        return ZERO;
    }
}
