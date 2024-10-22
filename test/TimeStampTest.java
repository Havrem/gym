import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TimeStampTest {
    @Test
    void TimeStamp_FutureDate_Throws() {
        assertThrows(IllegalArgumentException.class, () -> new Timestamp(LocalDate.now().plusDays(1), "1234567890", "Alice"));
    }

    @Test
    void TimeStamp_InvalidMemberId_Throws() {
        assertThrows(IllegalArgumentException.class, () -> new Timestamp(LocalDate.now(), "1234-56-7890", "Alice"));
    }
}
