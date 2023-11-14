package christmas.model;

import christmas.model.dateDiscount.DateDiscount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Benefits {
    private final List<DateDiscount> dateDiscounts;
    private final int dateDiscountAmount; // 날짜 할인
    private final int totalDiscountAmount; // 날짜 할인 + 증정

    public Benefits(Order order, GiftEvent gift, DateDiscount ... dateDiscounts) {
        this.dateDiscounts = setDateDiscounts(order, dateDiscounts);
        this.dateDiscountAmount = sumDateDiscountAmount();
        this.totalDiscountAmount = this.dateDiscountAmount + gift.getAmount();
    }

    public Map<String, Integer> findHistory() {
        return dateDiscounts.stream()
                .collect(Collectors.toMap(
                        DateDiscount::getMessage,
                        DateDiscount::getPrice
                ));
    }

    public int getDateDiscountAmount() {
        return dateDiscountAmount;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    private List<DateDiscount> setDateDiscounts(Order order, DateDiscount ... dateDiscounts) {
        return Stream.of(dateDiscounts)
                .filter(dateDiscount -> isValidDiscount(order, dateDiscount))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isValidDiscount(Order order, DateDiscount dateDiscount) {
        return isValidOrderedAmount(order) && hasDiscountAmount(dateDiscount);
    }

    private boolean isValidOrderedAmount(Order order) {
        return order.isOverMinAmount();
    }

    private boolean hasDiscountAmount(DateDiscount dateDiscount) {
        return dateDiscount.getPrice() != 0;
    }

    private int sumDateDiscountAmount() {
        return this.dateDiscounts.stream()
                .mapToInt(DateDiscount::getPrice)
                .sum();
    }
}
