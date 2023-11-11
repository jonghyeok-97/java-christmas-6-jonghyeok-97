package christmas.View;

import christmas.Model.Order;

public class OutputView {

    public void printEventResultMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printMenu(Order order) {
        order.getCountByOrderedMenu().entrySet()
                .forEach(entry -> {
                    System.out.printf("%s %d개\n", entry.getKey(), entry.getValue());
                });
    }
}
