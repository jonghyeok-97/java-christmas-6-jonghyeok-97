package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.DateDiscount;

public class WeekdaysDiscount extends DateDiscount {
//    private boolean isWeekdaysDiscount;
//    private int weekdaysDiscountPrice;
//
//    public WeekdaysDiscount(VisitDate visitDate, Order order) {
//        if (visitDate.isWeekdaysDate() && order.isOverMinDiscountPrice()) {
//            this.isWeekdaysDiscount = true;
//            int dessertPrice = order.findPriceOfDessert();
//            int countDessertMenu = order.countDessertMenu();
//            this.weekdaysDiscountPrice = dessertPrice - countDessertMenu * 2023;
//        }
//    }
    private int weekdaysDiscountPrice;
    public WeekdaysDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekdaysDate()) {
            int dessertPrice = order.findPriceOfDessert();
            int countDessertMenu = order.countDessertMenu();
            this.weekdaysDiscountPrice = countDessertMenu * 2023;
        }
    }

    public String getMessage() {
        return December.Message(December.WEEKDAY);
    }

    public int getPrice() {
        return this.weekdaysDiscountPrice;
    }


}
