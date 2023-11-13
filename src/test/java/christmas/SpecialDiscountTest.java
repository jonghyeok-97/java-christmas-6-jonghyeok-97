package christmas;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.SpecialDiscount;
import christmas.model.dateDiscount.WeekdaysDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpecialDiscountTest {

    @ParameterizedTest
    @DisplayName("특별할인 날짜에 1000원이 할인되는지 확인하는 테스트")
    @CsvSource(value = {"바비큐립-2,샴페인-1,초코케이크-2:3:1000", "아이스크림-1:3:0", "바비큐립-1:9:0",
            "바비큐립-1:10:1000"}, delimiter = ':')
    void check_SPECIAL_discount_by_Date(String menu, String date, int discountBenefit) {
        SpecialDiscount specialDiscount = new SpecialDiscount(new VisitDate(date), new Order(menu));

        int actual = specialDiscount.getPrice();
        int expected = discountBenefit;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
