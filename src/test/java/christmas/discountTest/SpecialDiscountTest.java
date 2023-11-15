package christmas.discountTest;

import christmas.model.VisitDate;
import christmas.model.VisitDateGenerator;
import christmas.model.dateDiscount.SpecialDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpecialDiscountTest {
    private VisitDateGenerator visitDateGenerator;

    @BeforeEach
    void set() {
        visitDateGenerator = new VisitDateGenerator();
    }

    @ParameterizedTest
    @DisplayName("특별할인 날짜에 1000원이 할인되는지 확인하는 테스트")
    @CsvSource(value = {"3:1000", "9:0", "10:1000"}, delimiter = ':')
    void check_SPECIAL_discount_by_Date(String date, int discountBenefit) {
        VisitDate visitDate = visitDateGenerator.createDate(date);
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate);

        int actual = specialDiscount.getAmount();
        int expected = discountBenefit;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}