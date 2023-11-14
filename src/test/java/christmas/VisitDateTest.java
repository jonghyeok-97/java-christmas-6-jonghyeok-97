package christmas;

import christmas.model.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {
    @ParameterizedTest
    @DisplayName("입력된 날짜가 숫자가 아니면 예외가 발생하는 테스트")
    @ValueSource(strings = {"a", " ", "삼십일", "3일"})
    void invalid_DateInput_Throws_Exception(String invalidDate) {
        Assertions.assertThatThrownBy(() -> new VisitDate(invalidDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("날짜가 1~31일 사이면 통과 테스트")
    @ValueSource(strings = {"1", "31", "10", "3"})
    void valid_DateInput(String validDate) {
        Assertions.assertThatCode(() -> new VisitDate(validDate))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("날짜가 1~31일 사이가 아니면 예외 발생 테스트")
    @ValueSource(strings = {"0", "32", "50", "-1"})
    void invalid_DateInput(String invalidDate) {
        Assertions.assertThatThrownBy(() -> new VisitDate(invalidDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("1~25일 일때, 크리스마스디데이할인인지 확인하는 테스트")
    @CsvSource(value = {"1,true", "2,true", "25,true", "26,false", "31,false"})
    void isNormalDate(String normalDate, boolean isNormalDate) {
        VisitDate visitDate = new VisitDate(normalDate);

        boolean actual = visitDate.isNormalDate();
        boolean expected = isNormalDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("날짜가 WEEKDAY면 참을 반환하는 테스트")
    @CsvSource(value = {"3,true", "10,true", "15,false", "30,false"})
    void isWeekdaysDate(String weekdaysDate, boolean isWeekdaysDate) {
        VisitDate visitDate = new VisitDate(weekdaysDate);

        boolean actual = visitDate.isWeekdaysDate();
        boolean expected = isWeekdaysDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
