package christmas.model;

import christmas.model.dateDiscount.DateDiscount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Payment {

    private List<DateDiscount> dateDiscounts = new ArrayList<>();

    public Payment(Order order, DateDiscount... dateDiscounts) {
        if (order.isOverMinDiscountPrice()) {
            this.dateDiscounts = Arrays.asList(dateDiscounts);
        }
    }

    public Map<String, Integer> tooString() {
        return dateDiscounts.stream()
                .collect(Collectors.toMap(
                        DateDiscount::getMessage,
                        DateDiscount::getPrice
                ));
    }
}
