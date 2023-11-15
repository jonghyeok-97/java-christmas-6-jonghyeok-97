package christmas;

import christmas.model.VisitDateValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateValidatorTest {
    private VisitDateValidator visitDateValidator;

    @BeforeEach
    void set() {
        visitDateValidator = new VisitDateValidator();
    }

    @ParameterizedTest
    @DisplayName("입력된 날짜가 숫자가 아니면 예외가 발생하는 테스트")
    @ValueSource(strings = {"a", " ", "삼십일", "3일"})
    void invalid_DateInput_Throws_Exception(String invalidDate) {
        Assertions.assertThatThrownBy(() -> visitDateValidator.validateDate(invalidDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("날짜가 1~31일 사이면 통과 테스트")
    @ValueSource(strings = {"1", "31", "10", "3"})
    void valid_DateInput(String validDate) {
        Assertions.assertThatCode(() -> visitDateValidator.validateDate(validDate))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("날짜가 1~31일 사이가 아니면 예외 발생 테스트")
    @ValueSource(strings = {"0", "32", "50", "-1"})
    void invalid_DateInput(String invalidDate) {
        Assertions.assertThatThrownBy(() -> visitDateValidator.validateDate(invalidDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}