package christmas.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Order {
    private static final String ERROR_RETRY_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ERROR_MAX_MENU_COUNT = "[ERROR] 메뉴의 총 개수는 최대 20개 입니다.";
    private static final String ERROR_ONLY_BEVERAGE = "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.";
    private static final String ORDER_DELIMETER = ",";
    private static final String MENU_DELIMETER = "-";
    private static final int NOT_EXIST_LOCATION = -1;
    private static final int NOT_COUNT = 0;
    private static final int MAX_COUNT = 20;
    private static final int MIN_TOTAL_AMOUNT_FOR_DISCOUNT = 10000;
    private static final int MIN_TOTAL_AMOUNT_FOR_GIFT = 120000;

    private final Map<String, Integer> countByOrderedMenu = new HashMap<>();
    private final int totalAmount;

    public Order(String input) throws IllegalArgumentException {
        validateOrder(input);
        List<String> menusWithMenuDelimeter = splitWithOrderDelimeter(input);
        for (String menuWithDelimeter : menusWithMenuDelimeter) {
            int locationOfDelimeter = findLocationOfMenuDelimeter(menuWithDelimeter);
            String menu = extractMenu(menuWithDelimeter, locationOfDelimeter);
            int count = extractCount(menuWithDelimeter, locationOfDelimeter);
            this.countByOrderedMenu.put(menu ,count);
        }
        validateMenu(menusWithMenuDelimeter);
        this.totalAmount = calculateTotalAmount();
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Map<String, Integer> getCountByOrderedMenu() {
        return Collections.unmodifiableMap(countByOrderedMenu);
    }

    public boolean isOverMinAmount() {
        return totalAmount >= MIN_TOTAL_AMOUNT_FOR_DISCOUNT;
    }
    public boolean isOverMinGiftAmount() {
        return totalAmount >= MIN_TOTAL_AMOUNT_FOR_GIFT;
    }

    public int countDessertMenu() {
        return countByOrderedMenu.keySet().stream()
                .filter(menu -> MenuBoard.findType(menu).equals(MenuBoard.DESSERT))
                .mapToInt(countByOrderedMenu::get)
                .sum();
    }

    public int countMainMenu() {
        return countByOrderedMenu.keySet().stream()
                .filter(menu -> MenuBoard.findType(menu).equals(MenuBoard.MAIN))
                .mapToInt(countByOrderedMenu::get)
                .sum();
    }

    private void validateOrder(String input) {
        validateOrderDelemeter(input);
        validateEndsWithOrderDelemeter(input);
    }

    private void validateOrderDelemeter(String input) {
        Stream.of(input.split(ORDER_DELIMETER))
                .filter(String::isEmpty)
                .findAny()
                .ifPresent(invalidOrder -> {
                    throw new IllegalArgumentException(ERROR_RETRY_ORDER);
                });
    }

    private void validateEndsWithOrderDelemeter(String input) {
        if (input.endsWith(ORDER_DELIMETER)) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private void validateMenu(List<String> menusWithMenuDelimeter) {
        validateDuplicatedMenu(menusWithMenuDelimeter);
        validateMenuExistOnMenuBorad();
        validateTotalCountOfAllMenu();
        validateOnlyBeverage();
    }

    private void validateDuplicatedMenu(List<String> menusWithMenuDelimeter) {
        Set<String> duplicacy = new HashSet<>(this.countByOrderedMenu.keySet());
        if (menusWithMenuDelimeter.size() != duplicacy.size()) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private void validateMenuExistOnMenuBorad() {
        this.countByOrderedMenu.keySet().stream()
                .filter(menu -> !MenuBoard.contains(menu))
                .findAny()
                .ifPresent(notExistOnMenuBoard -> {
                    throw new IllegalArgumentException(ERROR_RETRY_ORDER);
                });
    }

    private void validateTotalCountOfAllMenu() {
        if (sumCountOfAllMenu() > MAX_COUNT) {
            throw new IllegalArgumentException(ERROR_MAX_MENU_COUNT);
        }
    }

    private int sumCountOfAllMenu() {
        return this.countByOrderedMenu.keySet().stream()
                .mapToInt(this::countOf)
                .sum();
    }

    private void validateOnlyBeverage() {
        if (!hasDishFromMenu()) {
            throw new IllegalArgumentException(ERROR_ONLY_BEVERAGE);
        }
    }

    private boolean hasDishFromMenu() {
        return this.countByOrderedMenu.keySet().stream()
                .anyMatch(MenuBoard::findDish);
    }

    private List<String> splitWithOrderDelimeter(String input) {
        return Stream.of(input.split(ORDER_DELIMETER))
                .toList();
    }

    private int findLocationOfMenuDelimeter(String orderedMenu) {
        int location = orderedMenu.indexOf(MENU_DELIMETER);
        validateFound(location);
        return location;
    }

    private void validateFound(int location) {
        if (location == NOT_EXIST_LOCATION) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private String extractMenu(String menuWithDelimeter, int locationOfDelimeter) {
        String menu = menuWithDelimeter.substring(0, locationOfDelimeter);
        validateEmpty(menu);
        return menu;
    }

    private void validateEmpty(String menu) {
        if (menu.isEmpty()) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private int extractCount(String menuWithDelimeter, int locationOfDelimeter) {
        String extractedCount = menuWithDelimeter.substring(locationOfDelimeter + 1);
        try {
            int count = Integer.parseInt(extractedCount);
            validateExist(count);
            return count;
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private void validateExist(int count) {
        if (count <= NOT_COUNT) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private int calculateTotalAmount() {
        return this.countByOrderedMenu.keySet().stream()
                .mapToInt(menu -> priceOf(menu) * countOf(menu))
                .sum();
    }

    private int priceOf(String menu) {
        return MenuBoard.getPrice(menu);
    }

    private int countOf(String menu) {
        return this.countByOrderedMenu.get(menu);
    }
}