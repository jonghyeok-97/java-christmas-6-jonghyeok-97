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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BenefitsTest {

    @ParameterizedTest
    @DisplayName("총주문금액이 최소금액을 넘지 않으면 할인혜택들이 적용 안되어야 하는 테스트")
    @ValueSource(strings = {"아이스크림-1", "양송이수프-1,제로콜라-1", "타파스-1"})
    void underMinOrderedAmount(String menu) {
        VisitDate visitDate = new VisitDate("3");
        Order order = new Order(menu);
        Benefits benefit = new Benefits(new GiftEvent(order), order, new NormalDiscount(visitDate),
                new WeekendDiscount(visitDate, order), new WeekdaysDiscount(visitDate, order),
                new SpecialDiscount(visitDate));

        Map<String, Integer> actual = benefit.findHistory();
        Map<String, Integer> expected = new HashMap<>();

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("크리스마스디데이,평일,특별할인이 적용되는 테스트")
    @ValueSource(strings = {"아이스크림-1,바비큐립-2"})
    void apply_Normal_Weekdays_Special_Discount(String menu) {
        VisitDate visitDate = new VisitDate("3");
        Order order = new Order(menu);
        Benefits benefit = new Benefits(new GiftEvent(order), order, new NormalDiscount(visitDate),
                new WeekendDiscount(visitDate, order), new WeekdaysDiscount(visitDate, order),
                new SpecialDiscount(visitDate));

        Map<String, Integer> actual = benefit.findHistory();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("크리스마스 디데이 할인:", 1200);
        expected.put("평일 할인:", 2023);
        expected.put("특별 할인:", 1000);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("크리스마스디데이,주말할인이 적용되는 테스트")
    @ValueSource(strings = {"아이스크림-1,바비큐립-2"})
    void apply_Normal_Weekend(String menu) {
        VisitDate visitDate = new VisitDate("15");
        Order order = new Order(menu);
        Benefits benefit = new Benefits(new GiftEvent(order), order, new NormalDiscount(visitDate),
                new WeekendDiscount(visitDate, order), new WeekdaysDiscount(visitDate, order),
                new SpecialDiscount(visitDate));

        Map<String, Integer> actual = benefit.findHistory();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("크리스마스 디데이 할인:", 2400);
        expected.put("주말 할인:", 4046);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}