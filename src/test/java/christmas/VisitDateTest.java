package christmas;

import christmas.Model.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {

    @DisplayName("날짜가 숫자로만 이루어 지지 않으면 예외 발생 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "삼십일", "3일"})
    void not_number(String day) {
        Assertions.assertThatThrownBy(() -> new VisitDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
