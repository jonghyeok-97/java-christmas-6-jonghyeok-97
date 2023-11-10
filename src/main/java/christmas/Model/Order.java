package christmas.Model;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {
    //private final Map<MenuBoard, Integer> order;

    public Order(String input) throws IllegalArgumentException {
        validate(input);
        List<String> dashes = Stream.of(input.split(","))
                .collect(Collectors.toList());

        int totalCount = 0;
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

            //MenuBoard.compare(menu);
        }
        if (totalCount >= 21) {
            throw new IllegalArgumentException("[ERROR] 메뉴의 총 개수는 최대 20개 입니다.");
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
