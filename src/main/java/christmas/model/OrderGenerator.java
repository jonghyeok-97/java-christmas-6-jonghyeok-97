package christmas.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class OrderGenerator {
    public static final String ORDER_DELIMETER = ",";
    private static final String MENU_DELIMETER = "-";

    private final OrderValidator orderValidator;

    public OrderGenerator() {
        this.orderValidator = new OrderValidator();
    }

    public Order createCountByOrdereMenu(String input) throws IllegalArgumentException {
        orderValidator.validateOrder(input);
        Map<String, Integer> countByOrderedMenu = new HashMap<>();
        List<String> menusWithMenuDelimeter = splitWithOrderDelimeter(input);
        for (String menuWithDelimeter : menusWithMenuDelimeter) {
            int locationOfMenuDelimeter = findLocationOfMenuDelimeter(menuWithDelimeter);
            String menu = extractMenu(menuWithDelimeter, locationOfMenuDelimeter);
            int count = extractCount(menuWithDelimeter, locationOfMenuDelimeter);
            countByOrderedMenu.put(menu ,count);
        }
        orderValidator.validateMenu(menusWithMenuDelimeter.size(), countByOrderedMenu);
        return new Order(countByOrderedMenu);
    }

    private List<String> splitWithOrderDelimeter(String input) {
        return Stream.of(input.split(ORDER_DELIMETER))
                .toList();
    }

    private int findLocationOfMenuDelimeter(String orderedMenu) {
        int location = orderedMenu.indexOf(MENU_DELIMETER);
        orderValidator.validateFound(location);
        return location;
    }

    private String extractMenu(String menuWithDelimeter, int locationOfDelimeter) {
        String menu = menuWithDelimeter.substring(0, locationOfDelimeter);
        orderValidator.validateEmpty(menu);
        return menu;
    }

    private int extractCount(String menuWithDelimeter, int locationOfDelimeter) {
        String extractedCount = menuWithDelimeter.substring(locationOfDelimeter + 1);
        orderValidator.validateCountIsNumber(extractedCount);
        int count = Integer.parseInt(extractedCount);
        orderValidator.validateExist(count);
        return count;
    }
}