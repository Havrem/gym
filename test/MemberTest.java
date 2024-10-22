import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {
    @Test
    void SetClearanceDate_FutureDate_Throws() {
        Member member = new Member();

        assertThrows(IllegalArgumentException.class, () -> member.setClearanceDate(LocalDate.now().plusDays(1)));
    }
}
