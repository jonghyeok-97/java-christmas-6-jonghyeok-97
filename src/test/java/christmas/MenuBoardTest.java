package christmas;

import christmas.model.MenuBoard;
import christmas.model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuBoardTest {

    @ParameterizedTest
    @DisplayName("입력값의 메뉴가 메뉴판에 없다면 예외 발생하는 테스트")
    @ValueSource(strings = {"양송이수프-1,밥-3", "제로코라-3,해산물파스타-3"})
    void contains_MenuBoard(String menu) {
        Assertions.assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("메뉴판에서 메뉴타입을 찾는 테스트")
    @CsvSource(value = {"양송이수프,APPETIZER", "바비큐립,MAIN", "초코케이크,DESSERT", "레드와인,BEVERAGE"})
    void find_menuType_in_MenuBoard(String menu, MenuBoard menuType) {
        MenuBoard actual = MenuBoard.findType(menu);
        MenuBoard expected = menuType;
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("음식을 찾으면 true를 반환하는 테스트")
    @CsvSource(value = {"크리스마스파스타,true", "아이스크림,true", "타파스,true", "티본스테이크,true", "제로콜라,false",
            "샴페인,false"})
    void find_Dish_in_Order(String menu, boolean isDish) {
        boolean actual = MenuBoard.findDish(menu);
        boolean expected = isDish;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주문한 메뉴에 맞는 가격을 반환하는지 테스트")
    @CsvSource(value = {"양송이수프,6000", "제로콜라,3000", "크리스마스파스타,25000", "티본스테이크,55000", "초코케이크,15000",
            "샴페인,25000"})
    void getPrice_by_menu(String menu, int price) {
        int actual = MenuBoard.getPrice(menu);
        int expected = price;
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
