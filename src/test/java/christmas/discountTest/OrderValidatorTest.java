package christmas.discountTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.OrderGenerator;
import christmas.model.OrderValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderValidatorTest {
    private OrderValidator orderValidator;

    @BeforeEach
    void set() {
        orderValidator = new OrderValidator();
    }

    @ParameterizedTest
    @DisplayName("주문을 주문 구분자(,) 로 나눴을 때, 값이 없거나 주문 구분자가 끝에오면 오류 발생하는 테스트")
    @ValueSource(strings = {",바비큐립-3,제로콜라-1", "바비큐립-3,,제로콜라-2", "바비큐립-2,제로콜라-1,"})
    void validate_ORDER_DELIMTER(String invalidInput) {
        assertThatThrownBy(() -> orderValidator.validateOrder(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("구분자(-)의 위치를 못찾으면 예외 발생하는 테스트")
    @ValueSource(strings = {"해산물파스타3", "바비큐립1", "타파스3"})
    void validate_Location_Of_MENU_DELIMETER(String menu) {
        int location = menu.indexOf("-");

        assertThatThrownBy(() -> orderValidator.validateFound(location))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
