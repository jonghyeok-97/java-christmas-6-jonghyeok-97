package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DdayDiscountTest {
    private DdayDiscount d_dayDiscount;

    @Test
    @BeforeEach
    void set() {
        d_dayDiscount = new DdayDiscount();
    }

    @Test
    @DisplayName("날짜가 1~25일 일때, true인지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10, 24, 25})
    void isDiscount(int day) {
        boolean actual = d_dayDiscount.isDday(day);
        boolean expected = true;

        assertThat(actual).isEqualTo(expected);
    }
}
