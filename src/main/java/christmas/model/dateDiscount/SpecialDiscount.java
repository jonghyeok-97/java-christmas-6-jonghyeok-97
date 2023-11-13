package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class SpecialDiscount extends DateDiscount {
//    private boolean isSpecialDiscount;
//    private int specialDiscountPrice;
//
//    public SpecialDiscount(VisitDate visitDate, Order order) {
//        if (visitDate.isSpecialDate() && order.isOverMinDiscountPrice()) {
//            this.isSpecialDiscount = true;
//            this.specialDiscountPrice = 1000;
//        }
//    }
    private int specialDiscountPrice;

    public SpecialDiscount(VisitDate visitDate) {
        if (visitDate.isSpecialDate()) {
            this.specialDiscountPrice = 1000;
        }
    }

    public String getMessage() {
        return December.Message(December.SPECIAL);
    }

    public int getPrice() {
        return this.specialDiscountPrice;
    }
}
