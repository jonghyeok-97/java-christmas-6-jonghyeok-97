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

    public int extractCount(String menuWithDelimeter, int locationOfDelimeter) {
        String extractedCount = extract(menuWithDelimeter,locationOfDelimeter + 1, ZERO);
        orderValidator.validateCountIsNumber(extractedCount);
        orderValidator.validateExist(extractedCount);
        return Integer.parseInt(extractedCount);
    }

    private String extract(String menuWithDelimeter, int first, int end) {
        return menuWithDelimeter.substring(first, end);
    }
}
