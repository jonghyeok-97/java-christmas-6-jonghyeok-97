package christmas;

import christmas.Model.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {

    @DisplayName("날짜가 숫자로만 이루어 지지 않으면 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "삼십일", "3일"})
    void not_number(String day) {
        Assertions.assertThatThrownBy(() -> new VisitDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜가 1~31일 사이면 통과 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "10", "3"})
    void not_range(String validDay) {
        Assertions.assertThatCode(() -> new VisitDate(validDay))
                .doesNotThrowAnyException();
    }

    @DisplayName("날짜가 1~31일 사이가 아니면 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "50"})
    void not_range_2(String invalidDay) {
        Assertions.assertThatThrownBy(() -> new VisitDate(invalidDay))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @DisplayName("1~25일 일때, 크리스마스디데이할인인지 확인하는 테스트")
    @ValueSource(strings = {"1", "25", "10"})
    void isNormalDate(String visitNormalDate) {
        VisitDate visitDate = new VisitDate(visitNormalDate);
        boolean actual = visitDate.isNormalDate();

        Assertions.assertThat(actual).isEqualTo(true);
    }

    @ParameterizedTest
    @DisplayName("1일-1000원,2일-1100원...25일-3400원처럼 1일당 100원씩 증가하는지 테스트")
    @CsvSource(value = {"1,1000", "2,1100", "24,3300", "25,3400"})
    void calculate_Normal_Discount(String visitNormalDate, int discount) {
        VisitDate visitDate = new VisitDate(visitNormalDate);
        int actual = visitDate.calculateNormalDiscount();

        Assertions.assertThat(actual).isEqualTo(discount);
    }
}
