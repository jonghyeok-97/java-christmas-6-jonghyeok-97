package christmas;

import christmas.Model.VisitDate;

public class NormalDiscount {
    private boolean normalDate = false;
    private int normalDiscountPrice = 0;
    public NormalDiscount(VisitDate visitDate) {
        if (visitDate.isNormalDate()) {
            this.normalDate = true;
            this.normalDiscountPrice = visitDate.calculateNormalDiscount();       }
    }
}
