package christmas;

import christmas.model.MenuBoard;
import christmas.model.Order;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @ValueSource(strings = {"고기-20,파스타-1", "고기-3,파스타-0", "고기-20,파스타-0"})
    @DisplayName("메뉴 개수가 21개 이상이거나 주문메뉴개수가 0개면 예외 테스트")
    void create_Order3(String input) {
        Assertions.assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,제로콜라-3,양송이수프-5", "제로콜라-3,해산물파스타-3,제로콜라-1"})
    @DisplayName("메뉴가 중복되면 예외 발생하는 테스트")
    void not_menu_in_MenuBoard(String menu) {
        Assertions.assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-3,레드와인-1", "샴페인-3"})
    @DisplayName("음료만 주문하면 예외 발생하는 테스트")
    void only_Beverage_in_Order(String menu) {

        Assertions.assertThatThrownBy(() -> new Order(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"바비큐립-2,양송이수프-1:114000", "티본스테이크-1,초코케이크-1,제로콜라-2:76000", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000"}, delimiter = ':')
    @DisplayName("주문한 메뉴에 따라 총 주문금액이 잘 합산되는지 테스트")
    void check_totalPrice(String menus, int totalPrice) {
        Assertions.assertThat(new Order(menus).getTotalPrice()).isEqualTo(totalPrice);
    }

    @ParameterizedTest
    @DisplayName("메뉴에 따라 개수가 잘 넣어지는지 테스트")
    @CsvSource(value = {"양송이수프-3,바비큐립-2:양송이수프,바비큐립:3,2",
            "초코케이크-5,타파스-1,제로콜라-2:초코케이크,타파스,제로콜라:5,1,2"}, delimiter = ':')
    void check_countByOrderedMenu(String orderedMenu, String menus, String counts) {
        Order order = new Order(orderedMenu);
        Map<String, Integer> countByOrderedMenu = order.getCountByOrderedMenu();

        Set<String> actualMenus = countByOrderedMenu.keySet();
        Set<String> expectedMenus = Stream.of(menus.split(",")).collect(Collectors.toSet());

        List<Integer> actualCounts = countByOrderedMenu.values().stream().sorted().toList();
        List<Integer> expectedCounts = Stream.of(counts.split(","))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        Assertions.assertThat(actualMenus).isEqualTo(expectedMenus);
        Assertions.assertThat(actualCounts).isEqualTo(expectedCounts);
    }

    @ParameterizedTest
    @DisplayName("메뉴타입에 따라 메뉴가격이 잘 합산되는지 테스트")
    @CsvSource(value = {"양송이수프-1,타파스-2,시저샐러드-1:APPETIZER:25000", "티본스테이크-1,해산물파스타-1,제로콜라-3:MAIN:90000",
            "초코케이크-2,아이스크림-1,레드와인-2,바비큐립-1:DESSERT:35000"}, delimiter = ':')
    void check_priceByOrderedType(String inputMenu, MenuBoard MENUTYPE, int totalPriceByType) {
        Map<MenuBoard, Integer> priceByOrderedType = new HashMap<>();
        List<String> orderedMenus = Stream.of(inputMenu.split(","))
                .collect(Collectors.toList());
        for (String orderedMenu : orderedMenus) {
            int dashIndex = orderedMenu.indexOf("-");
            String menu = orderedMenu.substring(0, dashIndex);
            int countMenu = Integer.parseInt(orderedMenu.substring(dashIndex + 1));

            MenuBoard menuType = MenuBoard.findType(menu);
            int menuPrice = MenuBoard.getPrice(menu);

            priceByOrderedType.put(menuType, priceByOrderedType.getOrDefault(menuType, 0) + menuPrice * countMenu);
        }

        int actual = priceByOrderedType.get(MENUTYPE);
        int expected = totalPriceByType;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주문한 메뉴 중 디저트메뉴의 개수를 구하는 테스트")
    @CsvSource(value = {"타파스-3,초코케이크-10,바비큐립-5:10", "아이스크림-3,제로콜라-1,샴페인-3:3"}, delimiter = ':')
    void count_Dessert_Menu(String menus, int countingDessertMenu) {
        Order order = new Order(menus);

        int actual = order.countDessertMenu();
        int expected = countingDessertMenu;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 만원을 넘지 않으면 false를 반환하는 테스트")
    @ValueSource(strings = {"양송이수프-1", "타파스-1,제로콜라-1", "아이스크림-1"})
    void isOverMinOrderedPriceForDiscount(String menus) {
        Order order = new Order(menus);

        boolean actual = order.isOverMinDiscountPrice();
        boolean expected = false;

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주문한 메뉴 중 메인메뉴의 개수를 구하는 테스트")
    @CsvSource(value = {"타파스-3,초코케이크-10,바비큐립-5:5", "아이스크림-3,해산물파스타-1,샴페인-3:1"}, delimiter = ':')
    void count_MAIN_Menu(String menus, int countingMainMenu) {
        Order order = new Order(menus);

        int actual = order.countMainMenu();
        int expected = countingMainMenu;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
