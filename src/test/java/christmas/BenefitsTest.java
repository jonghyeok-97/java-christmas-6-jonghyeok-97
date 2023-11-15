package christmas;

import christmas.model.Benefits;
import christmas.model.Order;
import christmas.model.OrderGenerator;
import christmas.model.VisitDate;
import christmas.model.VisitDateGenerator;
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
    private OrderGenerator orderGenerator;
    private VisitDateGenerator visitDateGenerator;

    @BeforeEach
    void set() {
        orderGenerator = new OrderGenerator();
        visitDateGenerator = new VisitDateGenerator();
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 최소금액을 넘지 않으면 날짜마다 모든 할인혜택들이 적용 안되어야 하는 테스트")
    @CsvSource(value = {"3,아이스크림-1", "15,양송이수프-1,제로콜라-1", "25,타파스-1"})
    void underMinOrderedAmount(String date, String menu) {
        VisitDate visitDate = visitDateGenerator.createDate(date);
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        Benefits benefit = new Benefits(order, new NormalDiscount(visitDate),
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
        VisitDate visitDate = visitDateGenerator.createDate("3");
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        Benefits benefit = new Benefits(order, new NormalDiscount(visitDate),
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
        VisitDate visitDate = visitDateGenerator.createDate("3");
        Order order = orderGenerator.createCountByOrdereMenu(menu);
        Benefits benefit = new Benefits(order, new NormalDiscount(visitDate),
                new WeekendDiscount(visitDate, order), new WeekdaysDiscount(visitDate, order),
                new SpecialDiscount(visitDate));

        Map<String, Integer> actual = benefit.findHistory();
        Map<String, Integer> expected = new HashMap<>();
        expected.put("크리스마스 디데이 할인:", 2400);
        expected.put("주말 할인:", 4046);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}