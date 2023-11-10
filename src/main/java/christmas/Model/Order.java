package christmas.Model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {


    public Order(String input) throws IllegalArgumentException {
        validate(input);
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
