package christmas.controller;

import christmas.model.Order;
import christmas.model.Payment;
import christmas.model.PresentDiscount;
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

        outputView.printPreviewMessage(visitDate);
        outputView.printMenu(order);


        PresentDiscount presentDiscount = new PresentDiscount(order);

        NormalDiscount normalDiscount = new NormalDiscount(visitDate, order);
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, order);
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, order);
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate, order);
        Payment payment = new Payment(order, presentDiscount, normalDiscount, weekdaysDiscount, weekendDiscount, specialDiscount);

        outputView.printTotalPrice(order);
        outputView.printPresent(presentDiscount);
        // 혜택 내역
        outputView.printDiscounts(payment, presentDiscount);
        //총혜택금액
        outputView.printTotalDiscounts(payment, presentDiscount);
        // 할인후예상결제금액
        outputView.printExpectedPriceByOrder(order, payment, presentDiscount);
        // 배지
        outputView.printBadge(payment, presentDiscount);
    }
    private VisitDate createVisitDate () {
        try {
            return new VisitDate(inputView.readDate());
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return createVisitDate();
        }
    }

    private Order createOrder () {
        try {
            return new Order(inputView.readOrder());
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return createOrder();
        }
    }
}