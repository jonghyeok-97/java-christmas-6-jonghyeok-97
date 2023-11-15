package christmas;

import christmas.model.December;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecemberTest {

    @ParameterizedTest
    @DisplayName("크리스마스디데이 날짜만 참인지 확인하는 테스트")
    @CsvSource(value = {"1,true", "10,true", "25,true", "26,false", "31,false"})
    void check_not_normalDate(int visitDate, boolean isNormalDate) {
        boolean actual = December.checkNormalDate(visitDate);
        boolean expected = isNormalDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("평일 날짜만 참인지 확인하는 테스트")
    @CsvSource(value = {"3,true", "10,true", "17,true", "15,false", "23,false"})
    void check_weekdaysDate(int visitDate, boolean isWeekdaysDate) {
        boolean actual = December.checkWeekdaysDate(visitDate);
        boolean expected = isWeekdaysDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주말 날짜만 참인지 확인하는 테스트")
    @CsvSource(value = {"1,true", "9,true", "17,false", "23,true", "31,false"})
    void check_weekendDate(int visitDate, boolean isWeekendDate) {
        boolean actual = December.checkWeekendDate(visitDate);
        boolean expected = isWeekendDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("특별 날짜만 참인지 확인하는 테스트")
    @CsvSource(value = {"3,true", "10,true", "25,true", "13,false", "26,false", "30,false"})
    void check_SPECIAL_Date(int visitDate, boolean isSpecialDate) {
        boolean actual = December.checkSpecialDate(visitDate);
        boolean expected = isSpecialDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("할인 타입에 따른 메시지가 반환되는지 테스트")
    @CsvSource(value = {"NORMAL,크리스마스 디데이 할인:", "WEEKDAY,평일 할인:", "WEEKEND,주말 할인:", "SPECIAL,특별 할인:"})
    void message_by_discount_type(December december, String message) {
        String actual = December.findMessageByType(december);
        String expected = message;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}