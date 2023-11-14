package christmas.model;

import christmas.model.dateDiscount.DateDiscount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Benefits {
    private final List<DateDiscount> dateDiscounts;
    private final int dateDiscountAmount; // 날짜 할인
    private final int totalDiscountAmount; // 날짜 할인 + 증정

    public Benefits(Order order, GiftEvent gift, DateDiscount ... dateDiscounts) {
        this.dateDiscounts = setDateDiscounts(order, dateDiscounts);
        this.dateDiscountAmount = sumAmountOfDateDiscount();
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
        List<DateDiscount> discounts = new ArrayList<>();
        for (DateDiscount dateDiscount : dateDiscounts) {
            if (dateDiscount.getPrice() != 0 && order.isOverMinAmount()) {
                discounts.add(dateDiscount);
            }
        }
//        if (order.isOverMinAmount()) {
//            return List.of(dateDiscounts);
//        }
        return Collections.unmodifiableList(discounts);
    }

    private int sumAmountOfDateDiscount() {
        return this.dateDiscounts.stream()
                .mapToInt(DateDiscount::getPrice)
                .sum();
    }
}
