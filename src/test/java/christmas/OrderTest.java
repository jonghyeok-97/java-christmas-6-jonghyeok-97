package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import christmas.model.OrderGenerator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    private OrderGenerator orderGenerator;

    @BeforeEach
    void set() {
        orderGenerator = new OrderGenerator();
    }
    @ParameterizedTest
    @DisplayName("주문한 메뉴에 따라 총 주문금액이 잘 합산되는지 테스트")
    @CsvSource(value = {"바비큐립-2,양송이수프-1:114000", "티본스테이크-1,초코케이크-1,제로콜라-2:76000",
            "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000"}, delimiter = ':')
    void validate_totalAmount(String menus, int totalAmount) {
        Order order = orderGenerator.createCountByOrdereMenu(menus);

        int actual = order.getTotalAmount();
        int expected = totalAmount;

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴에 따라 개수가 잘 넣어지는지 테스트")
    @CsvSource(value = {"양송이수프-3,바비큐립-2:양송이수프,바비큐립:3,2",
            "초코케이크-5,타파스-1,제로콜라-2:초코케이크,타파스,제로콜라:5,1,2"}, delimiter = ':')
    void validate_countByOrderedMenu(String orderedMenu, String menus, String counts) {
        Order order = orderGenerator.createCountByOrdereMenu(orderedMenu);
        Map<String, Integer> countByOrderedMenu = order.getCountByOrderedMenu();

        Set<String> actualMenus = countByOrderedMenu.keySet();
        Set<String> expectedMenus = Stream.of(menus.split(",")).collect(Collectors.toSet());

        List<Integer> actualCounts = countByOrderedMenu.values().stream().sorted().toList();
        List<Integer> expectedCounts = Stream.of(counts.split(","))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        assertThat(actualMenus).isEqualTo(expectedMenus);
        assertThat(actualCounts).isEqualTo(expectedCounts);
    }

    @ParameterizedTest
    @DisplayName("주문한 메뉴 중 디저트메뉴의 개수를 구하는 테스트")
    @CsvSource(value = {"타파스-3,초코케이크-10,바비큐립-5:10", "아이스크림-3,제로콜라-1,샴페인-3:3"}, delimiter = ':')
    void count_Dessert_Menu(String menus, int countingDessertMenu) {
        Order order = orderGenerator.createCountByOrdereMenu(menus);

        int actual = order.countDessertMenu();
        int expected = countingDessertMenu;

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주문한 메뉴 중 메인메뉴의 개수를 구하는 테스트")
    @CsvSource(value = {"타파스-3,초코케이크-10,바비큐립-5:5", "아이스크림-3,해산물파스타-1,샴페인-3:1"}, delimiter = ':')
    void count_MAIN_Menu(String menus, int countingMainMenu) {
        Order order = orderGenerator.createCountByOrdereMenu(menus);

        int actual = order.countMainMenu();
        int expected = countingMainMenu;

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 만원을 넘지 않으면 false를 반환하는 테스트")
    @ValueSource(strings = {"양송이수프-1", "타파스-1,제로콜라-1", "아이스크림-1"})
    void check_MIN_TOTAL_AMOUNT_FOR_DISCOUNT(String menus) {
        Order order = orderGenerator.createCountByOrdereMenu(menus);

        boolean actual = order.isOverMinAmount();
        boolean expected = false;

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 12만원을 넘으면 true를 반환하는 테스트")
    @ValueSource(strings = {"양송이수프-1,티본스테이크-3", "타파스-1,샴페인-5", "티본스테이크-1,바비큐립-2"})
    void check_MIN_TOTAL_AMOUNT_FOR_GIFT(String menus) {
        Order order = orderGenerator.createCountByOrdereMenu(menus);

        boolean actual = order.isOverMinGiftAmount();
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }
}