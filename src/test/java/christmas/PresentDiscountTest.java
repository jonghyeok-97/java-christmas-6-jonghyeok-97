package christmas;

import christmas.Model.Order;
import christmas.Model.PresentDiscount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PresentDiscountTest {

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-15,레드와인-1", "바비큐립-3", "양송이수프-1,바비큐립-1,레드와인-1"})
    @DisplayName("총주문금액이 12만원이 넘어가면 증정하는 테스트")
    void is_over_present_price(String order) {
        PresentDiscount presentDiscount = new PresentDiscount(new Order(order));

        Assertions.assertThat(presentDiscount.getPresent()).isEqualTo(true);
    }
}
