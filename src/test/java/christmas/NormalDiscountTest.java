package christmas;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.NormalDiscount;
import christmas.model.dateDiscount.SpecialDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NormalDiscountTest {

    @ParameterizedTest
    @DisplayName("1일-1000원,2일-1100원,3일-1200원 처럼 1일 증가마다 100원씩 증가하는지 테스트")
    @CsvSource(value = {"3:1200", "9:1800", "10:1900", "26:0"}, delimiter = ':')
    void check_NORMAL_discount_by_Date(String date, int discountBenefit) {
        NormalDiscount normalDiscount = new NormalDiscount(new VisitDate(date));

        int actual = normalDiscount.getPrice();
        int expected = discountBenefit;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}