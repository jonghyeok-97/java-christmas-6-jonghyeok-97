package christmas.model;

import java.util.Collections;
import java.util.Map;

public class Order {
    private static final int MIN_TOTAL_AMOUNT_FOR_DISCOUNT = 10000;
    private static final int MIN_TOTAL_AMOUNT_FOR_GIFT = 120000;

    private final Map<String, Integer> countByOrderedMenu;
    private final int totalAmount;

    public Order(String input) throws IllegalArgumentException {
        OrderGenerator orderGenerator = new OrderGenerator();
        this.countByOrderedMenu = orderGenerator.createCountByOrdereMenu(input);
        this.totalAmount = calculateTotalAmount();
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Map<String, Integer> getCountByOrderedMenu() {
        return Collections.unmodifiableMap(countByOrderedMenu);
    }

    public boolean isOverMinAmount() {
        return totalAmount >= MIN_TOTAL_AMOUNT_FOR_DISCOUNT;
    }
    public boolean isOverMinGiftAmount() {
        return totalAmount >= MIN_TOTAL_AMOUNT_FOR_GIFT;
    }

    public int countDessertMenu() {
        return countByOrderedMenu.keySet().stream()
                .filter(menu -> MenuBoard.findType(menu).equals(MenuBoard.DESSERT))
                .mapToInt(countByOrderedMenu::get)
                .sum();
    }

    public int countMainMenu() {
        return countByOrderedMenu.keySet().stream()
                .filter(menu -> MenuBoard.findType(menu).equals(MenuBoard.MAIN))
                .mapToInt(countByOrderedMenu::get)
                .sum();
    }

    private int calculateTotalAmount() {
        return this.countByOrderedMenu.keySet().stream()
                .mapToInt(menu -> priceOf(menu) * countOf(menu))
                .sum();
    }

    private int priceOf(String menu) {
        return MenuBoard.getPrice(menu);
    }

    private int countOf(String menu) {
        return this.countByOrderedMenu.get(menu);
    }
}