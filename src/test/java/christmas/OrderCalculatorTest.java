package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.OrderCalculator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderCalculatorTest {
    private OrderCalculator orderCalculator;

    @BeforeEach
    void set() {
        orderCalculator = new OrderCalculator();
    }

    @ParameterizedTest
    @DisplayName("주문구분자(,)로 입력값이 나눠지는지 테스트")
    @ValueSource(strings = {"바비큐립-3,제로콜라-4", "타파스-3,양송이수프-1,샴페인-3", "초코케이크-10,제로콜라-10"})
    void split_With_Menu_Delimeter(String menu) {
        List<String> actual = orderCalculator.splitWithOrderDelimeter(menu);
        List<String> expected = List.of(menu.split(","));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴구분자(-)의 위치를 확인하는 테스트")
    @CsvSource(value = {"바비큐립-3,4", "크리스마스파스타-3,8", "초코케이크-10,5"})
    void find_Location_Of_Menu_Delimeter(String menu, int location) {
        int actual = orderCalculator.findLocationOfMenuDelimeter(menu);
        int expected = location;

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴만을 추출하는 테스트")
    @CsvSource(value = {"바비큐립-3,4,바비큐립", "크리스마스파스타-3,8,크리스마스파스타", "초코케이크-10,5,초코케이크"})
    void extract_Menu(String menuWithMenuDelimeter, int location, String menu) {
        String actual = orderCalculator.extractMenu(menuWithMenuDelimeter, location);
        String expected = menu;

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴의 개수만을 추출하는 테스트")
    @CsvSource(value = {"바비큐립-3,4,3", "크리스마스파스타-3,8,3", "초코케이크-10,5,10"})
    void extract_Count(String menuWithMenuDelimeter, int location, String count) {
        String actual = orderCalculator.extractCount(menuWithMenuDelimeter, location);
        String expected = count;

        assertThat(actual).isEqualTo(expected);
    }
}