package christmas.model.dateDiscount;

import static christmas.Constants.ZERO;

import christmas.model.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekendDiscount extends DateDiscount{
    private static final int WEEKEND_AMOUNT_PER_MAIN_MENU = 2023;

    private final int weekendAmount;

    public WeekendDiscount(VisitDate visitDate, Order order) {
        this.weekendAmount = calculateWeekendAmount(visitDate, order);
    }

    @Override
    public String getDiscountType() {
        return December.find(December.WEEKEND);
    }

    @Override
    public int getAmount() {
        return this.weekendAmount;
    }

    private int calculateWeekendAmount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekendDate()) {
            return order.countMainMenu() * WEEKEND_AMOUNT_PER_MAIN_MENU;
        }
        return ZERO;
    }
}
