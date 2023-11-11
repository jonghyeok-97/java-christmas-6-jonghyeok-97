package christmas;

import christmas.Model.MenuBoard;
import christmas.Model.Order;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuBoardTest {

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,밥-3", "제로코라-3,해산물파스타-3"})
    @DisplayName("입력값의 메뉴가 메뉴판에 없다면 예외 발생하는 테스트")
    void not_menu_in_MenuBoard(String menu) {
        Assertions.assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"양송이수프,6000", "바비큐립,54000", "초코케이크,15000"})
    @DisplayName("메뉴판에서 메뉴를 찾는 테스트")
    void find_menu_in_MenuBoard(String menu, int price) {
        Map<String, Integer> actual = MenuBoard.find(menu);

        Assertions.assertThat(actual.get(menu)).isEqualTo(price);
    }

    @ParameterizedTest
    @ValueSource(strings = {"크리스마스파스타", "아이스크림", "타파스"})
    @DisplayName("음식을 찾으면 true를 반환하는 테스트")
    void findDish_in_Order(String menu) {
        boolean actual = MenuBoard.findDish(menu);

        Assertions.assertThat(actual).isEqualTo(true);
    }
}
