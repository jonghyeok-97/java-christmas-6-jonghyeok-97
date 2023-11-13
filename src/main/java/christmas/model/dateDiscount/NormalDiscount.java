package christmas.model.dateDiscount;

import christmas.model.December;
import christmas.model.Order;
import christmas.model.VisitDate;

public class NormalDiscount extends DateDiscount {
    private int normalPrice;

    public NormalDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isNormalDate() && order.isOverMinDiscountPrice()){
            this.normalPrice = visitDate.getDate() * 100 + 900;
        }
    }

    public String getMessage() {
        return December.Message(December.NORMAL);
    }

    public int getPrice() {
        return this.normalPrice;
    }

}

