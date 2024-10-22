import java.io.Serializable;
import java.time.LocalDate;

public class Member extends Person implements Serializable {
    private LocalDate clearanceDate;

    public Member() {}

    public Member(String name, String personalIdentificationNumber, LocalDate clearanceDate) {
        super(name, personalIdentificationNumber);
        this.clearanceDate = clearanceDate;
    }

    public LocalDate getClearanceDate() {
        return clearanceDate;
    }

    public void setClearanceDate(LocalDate clearanceDate) {
        if (clearanceDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Clearance date cannot be after current date.");
        }

        this.clearanceDate = clearanceDate;
    }



    @Override
    public String toString() {
        return "Member{" + super.toString() +
                "clearanceDate=" + clearanceDate +
                "}";
    }
}
