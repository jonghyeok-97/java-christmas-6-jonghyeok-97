package christmas.model;

import java.util.List;
import java.util.stream.Stream;

public enum December {
    NORMAL(List.of(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25),
            "크리스마스 디데이 할인:"),
    WEEKDAY(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31),
            "평일 할인:"),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30), "주말 할인:"),
    SPECIAL(List.of(3, 10, 17, 24, 25, 31), "특별 할인:"),
    NONE(List.of(), "");

    private final List<Integer> discountDates;
    private final String message;

    December(List<Integer> discountDays, String message) {
        this.discountDates = discountDays;
        this.message = message;
    }

    public static boolean checkNormalDate(int visitDate) {
        return Stream.of(December.NORMAL)
                .anyMatch(type -> type.discountDates.contains(visitDate));
    }

    public static boolean checkWeekdaysDate(int visitDate) {
        return Stream.of(December.WEEKDAY)
                .anyMatch(type -> type.discountDates.contains(visitDate));
    }

    public static boolean checkWeekendDate(int visitDate) {
        return Stream.of(December.WEEKEND)
                .anyMatch(type -> type.discountDates.contains(visitDate));
    }

    public static boolean checkSpecialDate(int visitDate) {
        return Stream.of(December.SPECIAL)
                .anyMatch(week -> week.discountDates.contains(visitDate));
    }

    public static String getDiscountTypeMessage(December december) {
        return december.message;
    }

    public static String findMessageByType(December december) {
        return Stream.of(December.values())
                .filter(type -> type.equals(december))
                .map(December::getDiscountTypeMessage)
                .findAny()
                .orElse(NONE.message);
    }
}