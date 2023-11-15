package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.OrderGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderGeneratorTest {
    private OrderGenerator orderGenerator;

    @BeforeEach
    void set() {
        orderGenerator = new OrderGenerator();
    }

        @ParameterizedTest
    @DisplayName("주문한 메뉴의 개수가 0개 이하면 예외 테스트")
    @ValueSource(strings = {"바비큐립-0,제로콜라-1", "바비큐립-3,제로콜라-(-1)", "바비큐립-0"})
    void validate_Count_Of_Menu(String invalidInput) {
        assertThatThrownBy(() -> orderGenerator.createCountByOrdereMenu(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

        @ParameterizedTest
    @DisplayName("주문한 메뉴의 총 개수가 21개 이상이면 예외 테스트")
    @ValueSource(strings = {"고기-20,파스타-1", "고기-3,파스타-0", "고기-20,파스타-0"})
    void validate_Oer_Max_Count(String invalidInput) {
        assertThatThrownBy(() -> orderGenerator.createCountByOrdereMenu(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("주문한 메뉴가 메뉴판에 없으면 예외 테스트")
    @ValueSource(strings = {"고기-1,제로콜라-1", "바비큐립-3,사이다-3", "바비큐립-10,타파스-1,스테이크-2"})
    void validate_Exist_On_MenuBoard(String invalidInput) {
        assertThatThrownBy(() -> orderGenerator.createCountByOrdereMenu(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("메뉴가 중복되면 예외 발생하는 테스트")
    @ValueSource(strings = {"양송이수프-1,제로콜라-3,양송이수프-5", "제로콜라-3,해산물파스타-3,제로콜라-1"})
    void validate_Duplicated_Menu(String invalidMenu) {
        assertThatThrownBy(() -> orderGenerator.createCountByOrdereMenu(invalidMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("음료만 주문하면 예외 발생하는 테스트")
    @ValueSource(strings = {"제로콜라-3,레드와인-1", "샴페인-3"})
    void validate_Only_Beverage(String invalidMenu) {
        assertThatThrownBy(() -> orderGenerator.createCountByOrdereMenu(invalidMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}