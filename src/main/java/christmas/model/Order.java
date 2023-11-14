package christmas.model;

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

    private final Map<String, Integer> countByOrderedMenu = new HashMap<>();
    private int totalPrice;

    public Order(String input) throws IllegalArgumentException {
        validate(input);
        List<String> dashes = Stream.of(input.split(","))
                .toList();
        Set<String> duplicateMenu = new HashSet<>();
        int totalCount = 0;
        boolean hasDish = false;
        for (String s : dashes) {
            int idx = s.indexOf('-');
            if (idx == -1) {
                throw new IllegalArgumentException(ERROR_RETRY_ORDER);
            }
            String menu = s.substring(0, idx);
            if (menu.isEmpty()) {
                throw new IllegalArgumentException(ERROR_RETRY_ORDER);
            }
            int count = 0;
            try {
                count = Integer.parseInt(s.substring(idx + 1));
                if (count <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException error) {
                throw new IllegalArgumentException(ERROR_RETRY_ORDER);
            }
            totalCount += count;

            if (!MenuBoard.compare(menu)) {
                throw new IllegalArgumentException(ERROR_RETRY_ORDER);
            }
            if (!hasDish && MenuBoard.findDish(menu)) {
                hasDish = true;
            }
            duplicateMenu.add(menu);

            countByOrderedMenu.put(menu ,count);
        }
        if (dashes.size() != duplicateMenu.size()) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
        if (totalCount > 20) {
            throw new IllegalArgumentException(ERROR_MAX_MENU_COUNT);
        }
        if (!hasDish) {
            throw new IllegalArgumentException(ERROR_ONLY_BEVERAGE);
        }
        for (String menu : countByOrderedMenu.keySet()) {
            this.totalPrice += MenuBoard.getPrice(menu) * countByOrderedMenu.get(menu);
        }
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<String, Integer> getCountByOrderedMenu() {
        return countByOrderedMenu;
    }

    public boolean isOverMinAmount() {
        return totalPrice >= 10000;
    }
    public boolean isOverMinPresentPrice() {
        return totalPrice >= 120000;
    }

    public int countDessertMenu() {
        return countByOrderedMenu.keySet().stream()
                .filter(menu -> MenuBoard.findType(menu).equals(MenuBoard.DESSERT))
                .mapToInt(menu -> countByOrderedMenu.get(menu))
                .sum();
    }

    public int countMainMenu() {
        return countByOrderedMenu.keySet().stream()
                .filter(menu -> MenuBoard.findType(menu).equals(MenuBoard.MAIN))
                .mapToInt(menu -> countByOrderedMenu.get(menu))
                .sum();
    }

    private void validate(String input) {
        validateSplitedDelemeter(input);
        validateEndsWithDelemeter(input);
    }

    private void validateSplitedDelemeter(String input) {
        Stream.of(input.split(","))
                .filter(String::isEmpty)
                .findAny()
                .ifPresent(splited -> {
                    throw new IllegalArgumentException(ERROR_RETRY_ORDER);
                });
    }

    private void validateEndsWithDelemeter(String input) {
        if (input.endsWith(",")) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }
}
