package christmas.Model;

import java.util.Map;
import java.util.stream.Stream;

public enum MenuBoard {
    APPETIZER(Map.of("양송이수프", 6000, "타파스", 5500, "시저샐러드", 8000)),
    MAIN(Map.of("티본스테이크", 55000, "바비큐립", 54000,
            "해산물파스타", 35000, "크리스마스파스타", 25000)),
    DESSERT(Map.of("초코케이크", 15000, "아이스크림", 5000)),
    BEVERAGE(Map.of("제로콜라", 3000, "레드와인", 60000, "샴페인", 25000)),
    NONE(Map.of());

    private final Map<String, Integer> menu;

    MenuBoard(Map<String, Integer> menu) {
        this.menu = menu;
    }

    public static boolean compare(String orderedMenu) {
        return Stream.of(MenuBoard.values())
                .anyMatch(type -> type.menu.keySet().contains(orderedMenu));
    }

    public static Map<String, Integer> find(String orderedMenu) {
        return Stream.of(MenuBoard.values())
                .filter(type -> type.menu.keySet().contains(orderedMenu))
                .findAny()
                .map(type -> Map.of(orderedMenu, type.menu.get(orderedMenu)))
                .orElse(NONE.menu);
    }

    public static boolean findDish(String orderedMenu) {
        return Stream.of(MenuBoard.APPETIZER, MenuBoard.DESSERT, MenuBoard.MAIN)
                .anyMatch(type -> type.menu.keySet().contains(orderedMenu));
    }
}
