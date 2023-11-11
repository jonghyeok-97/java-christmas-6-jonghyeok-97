package christmas.View;

import christmas.Model.Order;
import christmas.Model.PresentDiscount;
import java.text.DecimalFormat;

public class OutputView {

    public void printEventResultMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");
        order.getCountByOrderedMenu().entrySet()
                .forEach(entry -> {
                    System.out.printf("%s %d개\n", entry.getKey(), entry.getValue());
                });
    }

    public void printTotalPrice(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.println(decimalFormat.format(order.getTotalPrice()) + "원");
    }

    public void printPresent(PresentDiscount presentDiscount) {
        System.out.println("<증정 메뉴>");
        if (!presentDiscount.getPresent()) {
            System.out.println("없음");
        }
        if (presentDiscount.getPresent()) {
            System.out.println("샴페인 1개");
        }
    }
}
