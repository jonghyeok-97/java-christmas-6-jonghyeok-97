package christmas.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderGenerator {
    public static final String ORDER_DELIMETER = ",";
    public static final String MENU_DELIMETER = "-";

    private final OrderValidator orderValidator;
    private final OrderCalculator orderCalculator;

    public OrderGenerator() {
        this.orderValidator = new OrderValidator();
        this.orderCalculator = new OrderCalculator();
    }

    public Order createCountByOrdereMenu(String input) throws IllegalArgumentException {
        orderValidator.validateOrder(input);
        Map<String, Integer> countByOrderedMenu = new HashMap<>();
        List<String> menusWithMenuDelimeter = orderCalculator.splitWithOrderDelimeter(input);
        for (String menuWithDelimeter : menusWithMenuDelimeter) {
            int locationOfMenuDelimeter = orderCalculator.findLocationOfMenuDelimeter(menuWithDelimeter);
            String menu = orderCalculator.extractMenu(menuWithDelimeter, locationOfMenuDelimeter);
            int count = toInt(orderCalculator.extractCount(menuWithDelimeter, locationOfMenuDelimeter));
            countByOrderedMenu.put(menu, count);
        }
        orderValidator.validateMenu(menusWithMenuDelimeter.size(), countByOrderedMenu);
        return new Order(countByOrderedMenu);
    }

    private int toInt(String number) {
        return Integer.parseInt(number);
    }
}