package christmas.discountTest;

import christmas.model.Order;
import christmas.model.OrderGenerator;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.WeekdaysDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekdaysDiscountTest {
    private OrderGenerator orderGenerator;

    @BeforeEach
    void set() {
        orderGenerator = new OrderGenerator();
    }

    @ParameterizedTest
    @DisplayName("평일이 아니면 평일 할인도 없는지 테스트")
    @CsvSource(value = {"바비큐립-2,샴페인-1,초코케이크-2:4046:3", "아이스크림-2,초코케이크-1:6069:13",
            "샴페인-2,초코케이크-1:0:1", "바비큐립-2,샴페인-1,초코케이크-2:0:30"},
            delimiter = ':')
    void date_Is_Weekdats(String menu, int discountAmount, String date) {
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        VisitDate visitDate = new VisitDate(date);
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, order);

        int actual = weekdaysDiscount.getAmount();
        int expected = discountAmount;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("평일이어도 디저트개수가 없으면 할인도 없는지 테스트")
    @CsvSource(value = {"바비큐립-2,샴페인-1,초코케이크-2:4046:3", "아이스크림-2,초코케이크-1:6069:13", "샴페인-2,타파스-1:0:28"},
            delimiter = ':')
    void count_DESSERT_menu(String menu, int discountAmount, String date) {
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        VisitDate visitDate = new VisitDate(date);
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, order);

        int actual = weekdaysDiscount.getAmount();
        int expected = discountAmount;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
