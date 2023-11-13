package christmas.view;

import christmas.model.Order;
import christmas.model.Payment;
import christmas.model.PresentDiscount;
import java.text.DecimalFormat;
import java.util.Map;

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
        if (!presentDiscount.getPresentDiscount()) {
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 1개");
    }

    public void printDiscounts(Payment payment, PresentDiscount presentDiscount) {
        System.out.println("<혜택 내역>");
        Map<String, Integer> discountHistory = payment.tooString();
        if (discountHistory.isEmpty()) {
            System.out.println("없음");
            return;
        }

        payment.tooString().entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> {
                    System.out.printf("%s -%d원\n", entry.getKey(), entry.getValue());
                });

        if (presentDiscount.getPresentDiscount()) {
            System.out.printf("증정 이벤트: -%d원", presentDiscount.getPresentDiscountPrice());
        }
    }
}
