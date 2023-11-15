package christmas;

import christmas.model.GiftEvent;
import christmas.model.Order;
import christmas.model.OrderGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GiftEventTest {
    private OrderGenerator orderGenerator;

    @BeforeEach
    void set() {
        orderGenerator = new OrderGenerator();

    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-15,레드와인-1", "바비큐립-3", "양송이수프-1,바비큐립-1,레드와인-1"})
    @DisplayName("총주문금액이 12만원이 넘어가면 증정하는 테스트")
    void is_over_present_price(String menus) {
        Order order = orderGenerator.createCountByOrdereMenu(menus);
        GiftEvent gift = new GiftEvent(order);

        boolean actual = gift.getFree();
        boolean expected = true;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}