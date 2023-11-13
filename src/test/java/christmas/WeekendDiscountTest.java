package christmas;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.WeekendDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekendDiscountTest {
    private VisitDate visitDate;

    @BeforeEach
    void set() {
        visitDate = new VisitDate("1");
    }

    @ParameterizedTest
    @DisplayName("주말에 메인메뉴개수*2023을 계산하는 식을 테스트")
    @CsvSource(value = {"티본스테이크-2,제로콜라-1:4046", "아이스크림-1:0", "샴페인-1,초코케이크-1,양송이수프-1:0"}, delimiter = ':')
    void count_MAIN_menu(String menu, int discountBenefit) {
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, new Order(menu));

        int actual = weekendDiscount.getPrice();
        int expected = discountBenefit;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
