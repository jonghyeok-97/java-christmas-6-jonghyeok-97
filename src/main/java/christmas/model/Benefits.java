package christmas.model;

import christmas.model.dateDiscount.DateDiscount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Benefits {
    private final List<DateDiscount> dateDiscounts;
    // 날짜 할인
    private final int amountOfDateDiscount;
    // 날짜 할인 + 증정
    private final int totalAmountOfAllDiscount;

    public Benefits(Order order, GiftEvent gift, DateDiscount ... dateDiscounts) {
        this.dateDiscounts = setDateDiscounts(order, dateDiscounts);
        this.amountOfDateDiscount = sumDiscountPriceByDate();
        this.totalAmountOfAllDiscount = this.amountOfDateDiscount + gift.getPresentDiscountPrice();
    }

    private List<DateDiscount> setDateDiscounts(Order order, DateDiscount ... dateDiscounts) {
        if (order.isOverMinAmount()) {
            return List.of(dateDiscounts);
        }
        return Collections.unmodifiableList(new ArrayList<>());
    }

    public Map<String, Integer> tooString() {
        return dateDiscounts.stream()
                .collect(Collectors.toMap(
                        DateDiscount::getMessage,
                        DateDiscount::getPrice
                ));
    }

    public int getAmountOfDateDiscount() {
        return amountOfDateDiscount;
    }

    public int getTotalAmountOfAllDiscount() {
        return totalAmountOfAllDiscount;
    }

    private int sumDiscountPriceByDate() {
        return dateDiscounts.stream()
                .mapToInt(DateDiscount::getPrice)
                .sum();
    }
}
