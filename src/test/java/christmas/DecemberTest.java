package christmas;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class DecemberTest {

    @Test
    void 날짜에_따른_할인유형_테스트_1() {
        List<December> actual = December.checkDiscountType(7);
        List<December> expected = List.of(December.D_DAY, December.WEEKDAY);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 날짜에_따른_할인유형_테스트_2() {
        List<December> actual = December.checkDiscountType(22);
        List<December> expected = List.of(December.D_DAY, December.WEEKEND);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
