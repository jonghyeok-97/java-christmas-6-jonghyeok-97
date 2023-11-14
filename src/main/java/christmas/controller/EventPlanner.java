package christmas.controller;

import christmas.model.Order;
import christmas.model.Benefits;
import christmas.model.GiftEvent;
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
        outputView.printMenuAndCount(order);

        GiftEvent presentDiscount = new GiftEvent(order);

//        NormalDiscount normalDiscount = new NormalDiscount(visitDate, order);
//        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, order);
//        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, order);
//        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate, order);
//        Benefits payment = new Benefits(order, presentDiscount, normalDiscount, weekdaysDiscount, weekendDiscount, specialDiscount);
        Benefits payment = new Benefits(order, presentDiscount, new NormalDiscount(visitDate, order),
                new WeekdaysDiscount(visitDate, order)
                , new WeekendDiscount(visitDate, order), new SpecialDiscount(visitDate, order));
        outputView.printTotalAmountBeforeDiscount(order);
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