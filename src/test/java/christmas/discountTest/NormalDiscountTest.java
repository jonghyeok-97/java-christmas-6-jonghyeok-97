package christmas.discountTest;

import christmas.model.VisitDate;
import christmas.model.VisitDateGenerator;
import christmas.model.dateDiscount.NormalDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NormalDiscountTest {
    private VisitDateGenerator visitDateGenerator;

    @BeforeEach
    void set() {
        visitDateGenerator = new VisitDateGenerator();
    }

    @ParameterizedTest
    @DisplayName("1일-1000원,2일-1100원,3일-1200원 와 같이 25일-3400원 까지 100원씩 증가하는지 테스트")
    @CsvSource(value = {"3:1200", "9:1800", "10:1900", "25:3400", "26:0"}, delimiter = ':')
    void check_NORMAL_discount_by_Date(String date, int discountAmount) {
        VisitDate visitDate = visitDateGenerator.createDate(date);
        NormalDiscount normalDiscount = new NormalDiscount(visitDate);

        int actual = normalDiscount.getAmount();
        int expected = discountAmount;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}