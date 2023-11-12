package christmas;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DecemberTest {

    @ParameterizedTest
    @DisplayName("크리스마스디데이 날짜만 참인지 확인하는 테스트")
    @CsvSource(value = {"1,true", "10,true", "25,true", "26,false", "31,false"})
    void check_not_normalDate(int visitDate, boolean isNormalDate) {
        boolean actual = December.checkNormalDate(visitDate);
        boolean expected = isNormalDate;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
