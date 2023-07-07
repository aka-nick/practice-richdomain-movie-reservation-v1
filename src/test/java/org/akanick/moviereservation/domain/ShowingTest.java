package org.akanick.moviereservation.domain;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShowingTest {

    @Test
    void dateBetween() {

        LocalDateTime before = LocalDateTime.of(2023, 7, 7, 15, 0, 0);
        LocalDateTime now = LocalDateTime.of(2023, 7, 7, 15, 0, 0);
        LocalDateTime after = LocalDateTime.of(2023, 7, 7, 16, 0, 0);

        Assertions.assertThat(before.isEqual(now) || before.isBefore(now)).isTrue();
        Assertions.assertThat(after.isAfter(now)).isTrue();
    }
}