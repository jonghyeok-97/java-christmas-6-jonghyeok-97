package christmas;

import christmas.model.OrderCalculator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderCalculatorTest {
    private OrderCalculator orderCalculator;

    @BeforeEach
    void set() {
        orderCalculator = new OrderCalculator();
    }

    @ParameterizedTest
    @DisplayName("주문구분자(,)로 입력값이 나눠지는지 테스트")
    @CsvSource(value = {"바비큐립-3,제로콜라-4", "타파스-3,양송이수프-1,샴페인-3", "초코케이크-10,제로콜라-10"},
            delimiter = ':')
    void split_With_Menu_Delimeter(String menu) {
        List<String> actual = orderCalculator.splitWithOrderDelimeter(menu);
        List<String> expected = List.of(menu.split(","));

        Assertions.assertThat(actual).isEqualTo(expected);
    }


}
