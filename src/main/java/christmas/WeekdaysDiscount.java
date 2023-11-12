package christmas;

import christmas.Model.Order;
import christmas.Model.VisitDate;

public class WeekdaysDiscount {
    private boolean isWeekdaysDiscount;
    private int weekdaysDiscountPrice;

    public WeekdaysDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekdaysDate() && order.isOverMinDiscountPrice()) {
            this.isWeekdaysDiscount = true;
            int dessertPrice = order.findPriceOfDessert();
            int countDessertMenu = order.countDessertMenu();
            this.weekdaysDiscountPrice = dessertPrice - countDessertMenu * 2023;
        }
    }


}
