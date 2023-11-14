package christmas;

import christmas.model.Benefits;
import christmas.model.GiftEvent;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.dateDiscount.NormalDiscount;
import christmas.model.dateDiscount.SpecialDiscount;
import christmas.model.dateDiscount.WeekdaysDiscount;
import christmas.model.dateDiscount.WeekendDiscount;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BenefitsTest {

    @BeforeEach
    void set() {

    }

    @ParameterizedTest
    @DisplayName("총주문금액이 최소금액을 넘지 않으면 할인혜택들이 적용 안되어야 하는 테스트")
    @ValueSource(strings = {"아이스크림-1", "양송이수프-1,제로콜라-1", "타파스-1"})
    void sum_discount_price_by_date(String menu) {
        VisitDate visitDate = new VisitDate("3");
        Order order = new Order(menu);
        Benefits benefit = new Benefits(order, new GiftEvent(order), new NormalDiscount(visitDate, order), new WeekendDiscount(visitDate, order),
                new WeekdaysDiscount(visitDate, order), new SpecialDiscount(visitDate, order));

        Map<String, Integer> actual = benefit.findHistory();
        Map<String, Integer> expected = new HashMap<>();

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
