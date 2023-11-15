package christmas.discountTest;

import christmas.model.Order;
import christmas.model.OrderGenerator;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.WeekendDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekendDiscountTest {
    private OrderGenerator orderGenerator;

    @BeforeEach
    void set() {
        orderGenerator = new OrderGenerator();
    }

    @ParameterizedTest
    @DisplayName("주말이 아니면 주말 할인도 없는지 테스트")
    @CsvSource(value = {"티본스테이크-2,제로콜라-1:4046:1", "아이스크림-1,해산물파스타-3:6069:9",
            "티본스테이크-2,제로콜라-1:0:3", "아이스크림-1,해산물파스타-3:0:10"}, delimiter = ':')
    void validate_WEEKEND_date(String menu, int discountAmount, String date) {
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        VisitDate visitDate = new VisitDate(date);
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, order);

        int actual = weekendDiscount.getAmount();
        int expected = discountAmount;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주말이어도 메인매뉴개수가 없으면 할인도 없는지 테스트")
    @CsvSource(value = {"티본스테이크-2,양송이수프-1:4046:1", "초코케이크-2,아이스크림-1,해산물파스타-3,샴페인-2:6069:9",
            "양송이수프-1:0:1", "초코케이크-2,아이스크림-1,샴페인-3:0:9"}, delimiter = ':')
    void count_MAIN_menu(String menu, int discountBenefit, String date) {
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        VisitDate visitDate = new VisitDate(date);
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, order);

        int actual = weekendDiscount.getAmount();
        int expected = discountBenefit;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}