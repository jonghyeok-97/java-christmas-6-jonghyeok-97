package christmas.model.dateDiscount;

import christmas.December;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.DateDiscount;

public class NormalDiscount extends DateDiscount {
//    private boolean normalDate;
//    private int normalDiscountPrice;
//    private Map<December, Integer> priceByDiscountType = new HashMap<>();
//
//    public NormalDiscount(VisitDate visitDate, Order order) {
//        if (visitDate.isNormalDate() && order.isOverMinDiscountPrice()) {
//           this.priceByDiscountType.put(December.NORMAL, visitDate.calculateNormalDiscount());
//        }
//    }
    private int normalPrice;

    public NormalDiscount(VisitDate visitDate) {
        if (visitDate.isNormalDate()) {
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

