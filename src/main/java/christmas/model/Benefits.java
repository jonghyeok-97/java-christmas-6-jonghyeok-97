package christmas.model;

import static christmas.Constants.ZERO;

import christmas.model.dateDiscount.DateDiscount;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Benefits {
    private final List<DateDiscount> dateDiscounts;
    private final int dateDiscountAmount; // 날짜 할인

    public Benefits(Order order, DateDiscount ... dateDiscounts) {
        this.dateDiscounts = setDateDiscounts(order, dateDiscounts);
        this.dateDiscountAmount = sumDateDiscountAmount();
    }

    public Map<String, Integer> findHistory() {
        return dateDiscounts.stream()
                .collect(Collectors.toMap(
                        DateDiscount::getDiscountType,
                        DateDiscount::getAmount
                ));
    }

    public int getDateDiscountAmount() {
        return dateDiscountAmount;
    }

    private List<DateDiscount> setDateDiscounts(Order order, DateDiscount ... dateDiscounts) {
        return Stream.of(dateDiscounts)
                .filter(dateDiscount -> isValidDiscount(order, dateDiscount))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isValidDiscount(Order order, DateDiscount dateDiscount) {
        return isOverMinTotalAmount(order) && hasDiscountAmount(dateDiscount);
    }

    private boolean isOverMinTotalAmount(Order order) {
        return order.isOverMinAmount();
    }

    private boolean hasDiscountAmount(DateDiscount dateDiscount) {
        return dateDiscount.getAmount() != ZERO;
    }

    private int sumDateDiscountAmount() {
        return this.dateDiscounts.stream()
                .mapToInt(DateDiscount::getAmount)
                .sum();
    }
}