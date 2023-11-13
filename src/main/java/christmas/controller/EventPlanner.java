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
    private InputView inputView;
    private OutputView outputView;

    public EventPlanner() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        VisitDate visitDate = createVisitDate();
        Order order = createOrder();

        outputView.printEventResultMessage();
        outputView.printMenu(order);
        outputView.printTotalPrice(order);

        PresentDiscount presentDiscount = new PresentDiscount(order);
        outputView.printPresent(presentDiscount);

        NormalDiscount normalDiscount = new NormalDiscount(visitDate);
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, order);
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, order);
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate);
        Payment payment = new Payment(order, normalDiscount, weekdaysDiscount, weekendDiscount, specialDiscount);

        outputView.printDiscounts(payment, presentDiscount);
        outputView.printTotalDiscounts(payment, presentDiscount);
    }
    private VisitDate createVisitDate () {
        try {
            return new VisitDate(inputView.readDate());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return createVisitDate();
        }
    }

    private Order createOrder () {
        try {
            return new Order(inputView.readOrder());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return createOrder();
        }
    }
}