import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String personalIdentificationNumber;

    public Person(){};

    public Person(String name, String personalIdentificationNumber) {
        if (!personalIdentificationNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid personal identification number.");
        } else {
            this.name = name;
            this.personalIdentificationNumber = personalIdentificationNumber;
        }
    }

    public String getName() {
        return name;
    }

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", personalIdentificationNumber='" + personalIdentificationNumber + '\'' +
                '}';
    }
}
