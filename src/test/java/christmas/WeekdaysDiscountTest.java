package christmas;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.WeekdaysDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekdaysDiscountTest {
    private VisitDate visitDate;
    @BeforeEach
    void set() {
        this.visitDate = new VisitDate("3");
    }

    @ParameterizedTest
    @DisplayName("디저트메뉴 개수 * 2023에 의해 평일 할인이 잘 계산되는지 테스트")
    @CsvSource(value = {"바비큐립-2,샴페인-1,초코케이크-2:4046", "아이스크림-2,초코케이크-1:6069", "샴페인-2,타파스-1:0",
            "아이스크림-1:0"}, delimiter = ':')
    void count_DESSERT_menu(String menu, int discountBenefits) {
        WeekdaysDiscount weekdaysDiscount = new WeekdaysDiscount(visitDate, new Order(menu));

        int actual = weekdaysDiscount.getPrice();
        int expected = discountBenefits;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
