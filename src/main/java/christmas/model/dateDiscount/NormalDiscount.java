package christmas.model.dateDiscount;

import static christmas.Constants.ZERO;

import christmas.model.December;
import christmas.model.VisitDate;

public class NormalDiscount extends DateDiscount {
    private static final int NORMAL_AMOUNT_PER_DATE = 100;
    private static final int DEFAULT_AMOUNT = 900;

    private final int normalAmount;

    public NormalDiscount(VisitDate visitDate) {
        this.normalAmount = calculateNormalAmount(visitDate);
    }

    @Override
    public String getDiscountType() {
        return December.find(December.NORMAL);
    }

    @Override
    public int getAmount() {
        return this.normalAmount;
    }

    private int calculateNormalAmount(VisitDate visitDate) {
        if (visitDate.isNormalDate()) {
            return visitDate.getDate() * NORMAL_AMOUNT_PER_DATE + DEFAULT_AMOUNT;
        }
        return ZERO;
    }
}

