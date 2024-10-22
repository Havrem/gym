import java.io.Serializable;
import java.time.LocalDate;

public record Timestamp(LocalDate date, String memberId, String name) implements Serializable {
    public Timestamp {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date is after now.");
        } else if (!memberId.matches("\\d{10}")) {
            throw new IllegalArgumentException("Member ID is invalid.");
        }
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "date=" + date +
                ", memberId=" + memberId + ", name=" + name +
                '}';
    }
}