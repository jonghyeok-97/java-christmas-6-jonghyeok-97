package christmas.discountTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.OrderCalculator;
import christmas.model.OrderValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderValidatorTest {
    private OrderValidator orderValidator;
    private OrderCalculator orderCalculator;

    @BeforeEach
    void set() {
        orderValidator = new OrderValidator();
        orderCalculator = new OrderCalculator();
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
    @ValueSource(strings = {"해산물파스타3,", "바비큐립1", "타파스19"})
    void validate_Location_Of_MENU_DELIMETER(String menu) {
        assertThatThrownBy(() -> orderValidator.validateFound(orderCalculator.findLocationOfMenuDelimeter(menu)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("메뉴이름을 찾지 못하면 예외 발생하는 테스트")
    @CsvSource(value = {"-3,0", "-19,0"})
    void validate_Menu(String menu, int locationOfMenuDelimeter) {
        assertThatThrownBy(() ->
                orderValidator.validateEmpty(orderCalculator.extractMenu(menu, locationOfMenuDelimeter)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("개수가 숫자가 아니면 예외 발생하는 테스트")
    @CsvSource(value = {"바비큐립-a,4", "타파스-!,3", "초코케이크-a,5", "타파스-,3", "제로콜라- ,4"})
    void validate_Count_Is_Number(String menu, int count) {
        assertThatThrownBy(() ->
                orderValidator.validateCountIsNumber(orderCalculator.extractCount(menu, count)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("개수가 0보다 작으면 예외 발생하는 테스트")
    @CsvSource(value = {"바비큐립-0,4", "타파스--1,3", "초코케이크--2,5"})
    void validate_Exist(String menu, int count) {
        assertThatThrownBy(() ->
                orderValidator.validateCountIsNumber(orderCalculator.extractCount(menu, count)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
