package christmas;

import christmas.Model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuBoardTest {

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,밥-3", "제로코라-3,해산물파스타-3"})
    @DisplayName("입력값의 메뉴가 메뉴판에 없다면 예외 발생하는 테스트")
    void not_menu_in_MenuBoard(String menu) {
        Assertions.assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
