package christmas;

import christmas.model.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {

//    @ParameterizedTest
//    @DisplayName("1~25일 일때, 크리스마스디데이할인인지 확인하는 테스트")
//    @CsvSource(value = {"1,true", "2,true", "25,true", "26,false", "31,false"})
//    void isNormalDate(String normalDate, boolean isNormalDate) {
//        VisitDate visitDate = new VisitDate(normalDate);
//
//        boolean actual = visitDate.isNormalDate();
//        boolean expected = isNormalDate;
//
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
//
//    @ParameterizedTest
//    @DisplayName("날짜가 WEEKDAY면 참을 반환하는 테스트")
//    @CsvSource(value = {"3,true", "10,true", "15,false", "30,false"})
//    void isWeekdaysDate(String weekdaysDate, boolean isWeekdaysDate) {
//        VisitDate visitDate = new VisitDate(weekdaysDate);
//
//        boolean actual = visitDate.isWeekdaysDate();
//        boolean expected = isWeekdaysDate;
//
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
//
//    @ParameterizedTest
//    @DisplayName("날짜가 WEEKEND면 참을 반환하는 테스트")
//    @CsvSource(value = {"1,true", "2,true", "15,true", "25,false"})
//    void isWeekendDate(String weekendDate, boolean isWeekendDate) {
//        VisitDate visitDate = new VisitDate(weekendDate);
//
//        boolean actual = visitDate.isWeekendDate();
//        boolean expected = isWeekendDate;
//
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
//
//    @ParameterizedTest
//    @DisplayName("날짜가 SPECIAL이면 참을 반환하는 테스트")
//    @CsvSource(value = {"3,true", "10,true", "17,true", "25,true"})
//    void isSpecialDate(String specialDate, boolean isSpecialDate) {
//        VisitDate visitDate = new VisitDate(specialDate);
//
//        boolean actual = visitDate.isSpecialDate();
//        boolean expected = isSpecialDate;
//
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
}