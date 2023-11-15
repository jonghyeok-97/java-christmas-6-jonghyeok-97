package christmas.model;

import static christmas.Constants.ZERO;
import static christmas.model.OrderGenerator.ORDER_DELIMETER;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class OrderValidator {
    private static final String ERROR_RETRY_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ERROR_MAX_MENU_COUNT = "[ERROR] 메뉴의 총 개수는 최대 20개 입니다.";
    private static final String ERROR_ONLY_BEVERAGE = "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.";
    private static final int NOT_EXIST_LOCATION = -1;
    private static final int MAX_COUNT = 20;

    public void validateOrder(String input) {
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

    public void validateFound(int location) {
        if (location == NOT_EXIST_LOCATION) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    public void validateEmpty(String menu) {
        if (menu.isEmpty()) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    public void validateCountIsNumber(String extractedCount) {
        try {
            Integer.parseInt(extractedCount);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    public void validateExist(String extractedCount) {
        if (isUnderZero(extractedCount)) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private boolean isUnderZero(String extractedCount) {
        return Integer.parseInt(extractedCount) <= ZERO;
    }

    public void validateMenu(int sizeOfOrder, Map<String, Integer> countByOrderedMenu) {
        validateDuplicatedMenu(sizeOfOrder, countByOrderedMenu);
        validateMenuExistOnMenuBorad(countByOrderedMenu);
        validateTotalCountOfAllMenu(countByOrderedMenu);
        validateOnlyBeverage(countByOrderedMenu);
    }

    private void validateDuplicatedMenu(int sizeOfOrder, Map<String, Integer> countByOrderedMenu) {
        Set<String> duplicacy = new HashSet<>(countByOrderedMenu.keySet());
        if (sizeOfOrder != duplicacy.size()) {
            throw new IllegalArgumentException(ERROR_RETRY_ORDER);
        }
    }

    private void validateMenuExistOnMenuBorad(Map<String, Integer> countByOrderedMenu) {
        countByOrderedMenu.keySet().stream()
                .filter(menu -> !MenuBoard.contains(menu))
                .findAny()
                .ifPresent(notExistOnMenuBoard -> {
                    throw new IllegalArgumentException(ERROR_RETRY_ORDER);
                });
    }

    private void validateTotalCountOfAllMenu(Map<String, Integer> countByOrderedMenu) {
        if (sumCountOfAllMenu(countByOrderedMenu) > MAX_COUNT) {
            throw new IllegalArgumentException(ERROR_MAX_MENU_COUNT);
        }
    }

    private int sumCountOfAllMenu(Map<String, Integer> countByOrderedMenu) {
        return countByOrderedMenu.keySet().stream()
                .mapToInt(countByOrderedMenu::get)
                .sum();
    }

    private void validateOnlyBeverage(Map<String, Integer> countByOrderedMenu) {
        if (!hasDishFromMenu(countByOrderedMenu)) {
            throw new IllegalArgumentException(ERROR_ONLY_BEVERAGE);
        }
    }

    private boolean hasDishFromMenu(Map<String, Integer> countByOrderedMenu) {
        return countByOrderedMenu.keySet().stream()
                .anyMatch(MenuBoard::findDish);
    }
}
