package christmas.model;

import christmas.model.dateDiscount.DateDiscount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Payment {

    private List<DateDiscount> dateDiscounts = new ArrayList<>();
    private final int totalDiscountPriceByDate;

    public Payment(Order order, DateDiscount... dateDiscounts) {
        if (order.isOverMinDiscountPrice()) {
            this.dateDiscounts = Arrays.asList(dateDiscounts);
        }
        this.totalDiscountPriceByDate = sumDiscountPriceByDate();
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


}
