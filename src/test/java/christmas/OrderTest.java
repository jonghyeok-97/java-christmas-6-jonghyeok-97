package christmas;

import christmas.Model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = {",고기-3,닭-1,파스타-3", "고기-3,,파스타2"})
    @DisplayName("쉼표가 연속되면 오류 발생하는 테스트")
    void create_Order(String input) {
        Assertions.assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
