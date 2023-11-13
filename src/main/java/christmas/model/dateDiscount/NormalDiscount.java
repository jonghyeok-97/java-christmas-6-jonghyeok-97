package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.Order;
import christmas.model.VisitDate;
import org.mockito.internal.matchers.Or;

public class NormalDiscount extends DateDiscount {
    private int normalPrice;

    public NormalDiscount(VisitDate visitDate, Order order) {
        if (visitDate.isNormalDate() && order.isOverMinDiscountPrice()){
            this.normalPrice = visitDate.calculateNormalDiscount();
        }
    }

    public String getMessage() {
        return December.Message(December.NORMAL);
    }

    public int getPrice() {
        return this.normalPrice;
    }

}

