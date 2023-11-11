package christmas.Model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Order {
    private final Map<Map<String, Integer>, Integer> orderedMenu = new HashMap<>();

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
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            String menu = s.substring(0, idx);
            if (menu.isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            int count = 0;
            try {
                count = Integer.parseInt(s.substring(idx + 1));
                if (count <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException error) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            totalCount += count;

            if (!MenuBoard.compare(menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            if (!hasDish && MenuBoard.findDish(menu)) {
                hasDish = true;
            }
            duplicateMenu.add(menu);
            orderedMenu.put(MenuBoard.find(menu) ,count);
        }
        if (dashes.size() != duplicateMenu.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (totalCount >= 21) {
            throw new IllegalArgumentException("[ERROR] 메뉴의 총 개수는 최대 20개 입니다.");
        }
        if (!hasDish) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
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
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                });
    }

    private void validateEndsWithDelemeter(String input) {
        if (input.endsWith(",")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
