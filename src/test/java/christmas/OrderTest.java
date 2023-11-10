package christmas;

import christmas.Model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = {",고기-3,닭-1,파스타-3", "고기-3,,파스타2", "고기-2,파스타-1,", "고기-2, ,파스타-3"})
    @DisplayName("구분자(,) 사이에 값이 없거나 구분자가 끝에오면 오류 발생하는 테스트")
    void create_Order(String input) {
        Assertions.assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"고기- 3,닭-1,파스타-3", "-3,파스타-2", "고기2,파스타-1", "고기-,파스타-2"})
    @DisplayName("구분자(-) 로 분리한 값이 올바르지 않으면 오류 발생하는 테스트")
    void create_Order2(String input) {
        Assertions.assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"고기-20,파스타-1"})
    @DisplayName("메뉴 개수가 21개 이상이면 예외 테스트")
    void create_Order3(String input) {
        Assertions.assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
