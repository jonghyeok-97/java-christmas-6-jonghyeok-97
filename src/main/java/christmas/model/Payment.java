package christmas.model;

import christmas.model.dateDiscount.DateDiscount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Payment {

    private List<DateDiscount> dateDiscounts = new ArrayList<>();
    // 할인 혜택들 다 더한것
    private final int totalDiscountPriceByDate;
    // 할인 혜택 + 증정
    private final int totalDiscountBenefit;

    public Payment(Order order, PresentDiscount presentDiscount, DateDiscount... dateDiscounts) {
        if (order.isOverMinDiscountPrice()) {
            this.dateDiscounts = Arrays.asList(dateDiscounts);
        }
        this.totalDiscountPriceByDate = sumDiscountPriceByDate();
        this.totalDiscountBenefit = this.totalDiscountPriceByDate + presentDiscount.getPresentDiscountPrice();
    }

    private int sumDiscountPriceByDate() {
        return dateDiscounts.stream()
                .mapToInt(DateDiscount::getPrice)
                .sum();
    }

    public Map<String, Integer> tooString() {
        return dateDiscounts.stream()
                .collect(Collectors.toMap(
                        DateDiscount::getMessage,
                        DateDiscount::getPrice
                ));
    }

    public int getTotalDiscountPriceByDate() {
        return totalDiscountPriceByDate;
    }

    public int getTotalDiscountBenefit() {
        return totalDiscountBenefit;
    }
}
