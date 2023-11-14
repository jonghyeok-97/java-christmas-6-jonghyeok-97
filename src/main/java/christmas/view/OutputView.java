package christmas.view;

import static christmas.Constants.ZERO;
import static christmas.view.OutputViewMessage.AMOUNT;
import static christmas.view.OutputViewMessage.AMOUNT_DISCOUNT;
import static christmas.view.OutputViewMessage.AMOUNT_PER_BENEFIT;
import static christmas.view.OutputViewMessage.AMOUNT_RESULT;
import static christmas.view.OutputViewMessage.BADGE;
import static christmas.view.OutputViewMessage.BADGE_SANTA;
import static christmas.view.OutputViewMessage.BADGE_STAR;
import static christmas.view.OutputViewMessage.BADGE_TREE;
import static christmas.view.OutputViewMessage.BENEFITS_HISTORY;
import static christmas.view.OutputViewMessage.EXPECTED_AMOUNT_AFTER_BENEFIT;
import static christmas.view.OutputViewMessage.GIFT_BENEFIT;
import static christmas.view.OutputViewMessage.GIFT_MENU;
import static christmas.view.OutputViewMessage.MENU_AND_COUNT;
import static christmas.view.OutputViewMessage.MIN_AMOUNT_OF_SANTA;
import static christmas.view.OutputViewMessage.MIN_AMOUNT_OF_STAR;
import static christmas.view.OutputViewMessage.MIN_AMOUNT_OF_TREE;
import static christmas.view.OutputViewMessage.NOTHING;
import static christmas.view.OutputViewMessage.ONE_GIFT;
import static christmas.view.OutputViewMessage.ORDERED_MENU;
import static christmas.view.OutputViewMessage.PREVIEW_GUIDE;
import static christmas.view.OutputViewMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT;
import static christmas.view.OutputViewMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.OutputViewMessage.WELCOME;

import christmas.model.Order;
import christmas.model.Benefits;
import christmas.model.GiftEvent;
import christmas.model.VisitDate;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String EVERY_THREE_DIGITS = "###,###";
    private final DecimalFormat decimalFormat = new DecimalFormat(EVERY_THREE_DIGITS);
    // 최고 판매 금액을 위한 목표를 달성 하기 위해 이벤트 기간 중에 할인혜택들을 변동 할지말지에 대한 기준을 세울 수 있게
    private long expectedPriceOfDecember;
    // 중복참여하는 고객이 있을 수 있으나 이벤트 기간 중 참여 고객이 저조할 경우 할인혜택을 변동 할지말지에 대한 기준을 세울 수 있게
    private int countParticipatedCustomer;

    public void printWelcomeMessage() {
        System.out.println(WELCOME);
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
        newLine();
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.printf(AMOUNT, decimalFormat.format(order.getTotalAmount()));
    }

    public void printPresent(GiftEvent gift) {
        newLine();
        System.out.println(GIFT_MENU);
        if (!gift.getFree()) {
            System.out.println(NOTHING);
            return;
        }
        System.out.println(ONE_GIFT);
    }

    public void printBenefits(Benefits benefit, GiftEvent gift) {
        newLine();
        System.out.println(BENEFITS_HISTORY);
        printAmountByBenefit(benefit.findHistory());
        printGiftBenefit(gift);
    }

    private void printAmountByBenefit(Map<String, Integer> amountByBenefit) {
        if (amountByBenefit.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        amountByBenefit.entrySet().stream()
                .filter(amount -> hasAmount(amount.getValue()))
                .forEach(discountType -> {
                    System.out.printf(AMOUNT_PER_BENEFIT,
                            discountType.getKey(), decimalFormat.format(discountType.getValue()));
                });
    }

    private boolean hasAmount(int amount) {
        return amount != ZERO;
    }

    private void printGiftBenefit(GiftEvent gift) {
        if (gift.getFree()) {
            System.out.printf(GIFT_BENEFIT, decimalFormat.format(gift.getAmount()));
        }
    }

    public void printTotalDiscounts(Benefits benefit) {
        newLine();
        System.out.println(TOTAL_BENEFIT_AMOUNT);
        int totalDiscounts = benefit.getTotalDiscountAmount();
        if (totalDiscounts == ZERO) {
            System.out.printf(AMOUNT, ZERO);
            return;
        }
        System.out.printf(AMOUNT_DISCOUNT, decimalFormat.format(totalDiscounts));
    }

    public void printExpectedPriceByOrder(Order order, Benefits benefit) {
        newLine();
        System.out.println(EXPECTED_AMOUNT_AFTER_BENEFIT);
        int expectedAmount = order.getTotalAmount() - benefit.getDateDiscountAmount();
        System.out.printf(AMOUNT_RESULT, decimalFormat.format(expectedAmount));

        expectedPriceOfDecember += expectedAmount;
        countParticipatedCustomer++;
    }

    public void printBadge(Benefits benefit) {
        newLine();
        System.out.println(BADGE);
        int totalDiscountAmount = benefit.getTotalDiscountAmount();
        printBadgeOf(totalDiscountAmount);
    }

    private void printBadgeOf(int totalDiscountAmount) {
        if (totalDiscountAmount < MIN_AMOUNT_OF_STAR) {
            System.out.print(NOTHING);
        }
        else if (totalDiscountAmount < MIN_AMOUNT_OF_TREE) {
            System.out.print(BADGE_STAR);
        }
        else if (totalDiscountAmount < MIN_AMOUNT_OF_SANTA) {
            System.out.print(BADGE_TREE);
        }
        else {
            System.out.print(BADGE_SANTA);
        }
    }

    public void newLine() {
        System.out.println();
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}