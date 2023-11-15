package christmas.model;

import static christmas.Constants.ZERO;
import static christmas.model.OrderGenerator.MENU_DELIMETER;
import static christmas.model.OrderGenerator.ORDER_DELIMETER;

import java.util.List;
import java.util.stream.Stream;

public class OrderCalculator {
    private final OrderValidator orderValidator;

    public OrderCalculator() {
        this.orderValidator = new OrderValidator();
    }

    public List<String> splitWithOrderDelimeter(String input) {
        return Stream.of(input.split(ORDER_DELIMETER)).toList();
    }

    public int findLocationOfMenuDelimeter(String orderedMenu) {
        int location = orderedMenu.indexOf(MENU_DELIMETER);
        orderValidator.validateFound(location);
        return location;
    }

    public String extractMenu(String menuWithDelimeter, int locationOfDelimeter) {
        String menu = extract(menuWithDelimeter, ZERO, locationOfDelimeter);
        orderValidator.validateEmpty(menu);
        return menu;
    }

    public String extractCount(String menuWithDelimeter, int locationOfDelimeter) throws IllegalArgumentException {
        String extractedCount = extract(menuWithDelimeter,locationOfDelimeter + 1);
        orderValidator.validateCountIsNumber(extractedCount);
        orderValidator.validateExist(extractedCount);
        return extractedCount;
    }

    private String extract(String menuWithDelimeter, int first, int end) {
        return menuWithDelimeter.substring(first, end);
    }

    private String extract(String menuWithDelimeter, int first) {
        return menuWithDelimeter.substring(first);
    }
}