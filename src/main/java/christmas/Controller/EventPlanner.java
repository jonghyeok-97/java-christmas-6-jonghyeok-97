package christmas.Controller;

import christmas.Model.Order;
import christmas.Model.PresentDiscount;
import christmas.Model.VisitDate;
import christmas.Model.WeekendDiscount;
import christmas.Model.NormalDiscount;
import christmas.SpecialDiscount;
import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.Model.WeekdaysDiscount;

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

        outputView.printPresent(new PresentDiscount(order));

        NormalDiscount normalDiscount = new NormalDiscount(visitDate, order);
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, order);
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, order);
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate, order);
    }

    private VisitDate createVisitDate() {
        try {
            return new VisitDate(inputView.readDate());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return createVisitDate();
        }
    }

    private Order createOrder() {
        try {
            return new Order(inputView.readOrder());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return createOrder();
        }
    }
}
