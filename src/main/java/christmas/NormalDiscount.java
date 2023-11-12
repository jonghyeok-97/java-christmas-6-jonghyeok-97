package christmas;

import christmas.Model.Order;
import christmas.Model.VisitDate;

public class NormalDiscount {
    private boolean normalDate = false;
    private int normalDiscountPrice = 0;
    public NormalDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isNormalDate() && order.isOverMinDiscountPrice()) {
            this.normalDate = true;
            this.normalDiscountPrice = visitDate.calculateNormalDiscount();
        }
    }
}
