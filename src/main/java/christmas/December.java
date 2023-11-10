package christmas;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum December {
    D_DAY(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)),
    WEEKDAY(List.of(4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 26, 27, 28)),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    SPECIAL(List.of(3, 10, 17, 24, 25, 31));


    private final List<Integer> discountDays;

    December(List<Integer> discountDays) {
        this.discountDays = discountDays;
    }

    public static List<December> checkDiscountType(int day) {
        return Stream.of(December.values())
                .filter(type -> type.getDiscountDays().contains(day))
                .collect(Collectors.toList());
    }


    public List<Integer> getDiscountDays() {
        return discountDays;
    }
}