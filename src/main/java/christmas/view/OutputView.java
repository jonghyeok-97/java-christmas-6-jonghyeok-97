package christmas.view;

import christmas.model.Order;
import christmas.model.Benefits;
import christmas.model.GiftEvent;
import christmas.model.VisitDate;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String WELCOME = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_GUIDE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDERED_MENU = "<주문 메뉴>";
    private static final String MENU_AND_COUNT = "%s %d개\n";
    private static final String TOTAL_AMOUNT_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String EVERY_THREE_DIGITS = "###,###";
    private static final String GIFT_MENU = "<증정 메뉴>";
    private static final String ONE_GIFT = "샴페인 1개";
    private static final String NOTHING = "없음";
    private static final String BENEFITS_HISTORY = "<혜택 내역>";

    private DecimalFormat decimalFormat = new DecimalFormat(EVERY_THREE_DIGITS);
    private long expectedPriceOfDecember;
    private int countParticipateCustomer;

    public void printWelcomeMessage() {
        System.out.println(WELCOME);
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printPreviewMessage(VisitDate visitDate) {
        System.out.printf(PREVIEW_GUIDE, visitDate.getDate());
        newLine();
    }

    public void printMenuAndCount(Order order) {
        System.out.println(ORDERED_MENU);
        order.getCountByOrderedMenu().forEach(
                (menu, count) -> System.out.printf(MENU_AND_COUNT, menu, count));
    }

    public void printTotalAmountBeforeDiscount(Order order) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(decimalFormat.format(order.getTotalPrice()) + "원");
    }

    public void printPresent(GiftEvent gift) {
        System.out.println(GIFT_MENU);
        if (!gift.getFree()) {
            System.out.println(NOTHING);
            return;
        }
        System.out.println(ONE_GIFT);
    }

    public void printDiscounts(Benefits benefit, GiftEvent gift) {
        System.out.println(BENEFITS_HISTORY);
        Map<String, Integer> amountByBenefit = benefit.findHistory();
        if (amountByBenefit.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        benefit.findHistory().entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> {
                    System.out.printf("%s -%s원\n", entry.getKey(), decimalFormat.format(entry.getValue()));
                });

        if (gift.getFree()) {
            System.out.printf("증정 이벤트: -%s원\n", decimalFormat.format(gift.getAmount()));
        }
    }

    public void printTotalDiscounts(Benefits payment, GiftEvent presentDiscount) {
        System.out.println("<총혜택 금액>");
        int totalDiscounts = payment.getTotalDiscountAmount();

        if (totalDiscounts == 0) {
            System.out.println("0원\n");
            return;
        }
        System.out.printf("-%s원\n", decimalFormat.format(totalDiscounts));
    }

    public void printExpectedPriceByOrder(Order order, Benefits payment, GiftEvent presentDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        int totalPrice = order.getTotalPrice();
        int discountTotalPriceByDate = payment.getDateDiscountAmount();
        int diffPrice = totalPrice - discountTotalPriceByDate;

        expectedPriceOfDecember += diffPrice;
        countParticipateCustomer++;
        System.out.printf("-%s원\n", decimalFormat.format(diffPrice));
    }

    public void printBadge(Benefits payment, GiftEvent presentDiscount) {
        int totalDiscounts = payment.getTotalDiscountAmount();
        System.out.println("<12월 이벤트 배지>");
        if (totalDiscounts < 5000) {
            System.out.println("없음");
        }
        if (5000 <= totalDiscounts && totalDiscounts < 10000) {
            System.out.println("별");
        }
        if (10000 <= totalDiscounts && totalDiscounts < 20000) {
            System.out.println("트리");
        }
        if (20000 <= totalDiscounts) {
            System.out.println("산타");
        }
    }

    public void newLine() {
        System.out.println();
    }
}
