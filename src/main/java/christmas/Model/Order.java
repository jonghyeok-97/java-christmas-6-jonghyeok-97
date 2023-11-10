package christmas.Model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order {


    public Order(String input) throws IllegalArgumentException {

    }

    private void validate(String input) {
        validateSplitedDelemeter(input);
    }

    private void validateSplitedDelemeter(String input) {
        Stream.of(input.split(","))
                .filter(String::isEmpty)
                .findAny()
                .ifPresent(splited -> {
                    throw new IllegalArgumentException();
                });
    }
}
