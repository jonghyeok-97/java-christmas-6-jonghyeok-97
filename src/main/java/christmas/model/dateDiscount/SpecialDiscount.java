package christmas.model.dateDiscount;

import static christmas.Constants.ZERO;

import christmas.model.December;
import christmas.model.VisitDate;

public class SpecialDiscount extends DateDiscount {
    private static final int SPECIAL_AMOUNT = 1000;

    private final int specialAmount;

    public SpecialDiscount(VisitDate visitDate) {
        this.specialAmount = setSpecialAmount(visitDate);
    }

    @Override
    public String getDiscountType() {
        return December.find(December.SPECIAL);
    }

    @Override
    public int getAmount() {
        return this.specialAmount;
    }

    private int setSpecialAmount(VisitDate visitDate) {
        if (visitDate.isSpecialDate()) {
            return SPECIAL_AMOUNT;
        }
        return ZERO;
    }
}
