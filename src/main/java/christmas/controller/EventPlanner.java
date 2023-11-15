package christmas.controller;

import christmas.model.Order;
import christmas.model.Benefits;
import christmas.model.GiftEvent;
import christmas.model.OrderGenerator;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.WeekendDiscount;
import christmas.model.dateDiscount.NormalDiscount;
import christmas.model.dateDiscount.SpecialDiscount;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.model.dateDiscount.WeekdaysDiscount;

public class EventPlanner {
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlanner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        outputView.printWelcomeMessage();
        VisitDate visitDate = createVisitDate();
        Order order = createOrder();
        printOrderDetails(visitDate, order);
        GiftEvent gift = new GiftEvent(order);
        Benefits benefit = createBenefits(visitDate, order);
        printOrderResultByBenefits(benefit, order, gift);
    }
    private VisitDate createVisitDate() {
        try {
            return new VisitDate(inputView.readDate());
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return createVisitDate();
        }
    }

    private Order createOrder() {
        try {
            OrderGenerator orderGenerator = new OrderGenerator();
            Order order = orderGenerator.createCountByOrdereMenu(inputView.readOrder());
            return order;
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return createOrder();
        }
    }

    private void printOrderDetails(VisitDate visitDate, Order order) {
        outputView.printPreviewMessage(visitDate);
        outputView.printMenuAndCount(order);
    }

    private Benefits createBenefits(VisitDate visitDate, Order order) {
        return new Benefits(order, new NormalDiscount(visitDate), new WeekdaysDiscount(visitDate, order),
                new WeekendDiscount(visitDate, order), new SpecialDiscount(visitDate));
    }

    private void printOrderResultByBenefits(Benefits benefit, Order order, GiftEvent gift) {
        outputView.printTotalAmountBeforeDiscount(order);
        outputView.printPresent(gift);
        outputView.printBenefits(benefit, gift);
        outputView.printTotalDiscounts(benefit, gift);
        outputView.printExpectedPriceByOrder(order, benefit);
        outputView.printBadge(benefit, gift);
    }
}