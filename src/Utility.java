import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Utility {
    public static Optional<Member> searchHardDrive(String pattern, String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String personalInfo = scanner.nextLine();
                String clearanceDateInfo = scanner.nextLine();

                String[] personalInfoSplitted = personalInfo.split(", ");

                if (personalInfoSplitted[0].matches(pattern) || personalInfoSplitted[1].matches(pattern)) {
                    String personalId = personalInfoSplitted[0];
                    String name = personalInfoSplitted[1];

                    LocalDate clearanceDate = LocalDate.parse(clearanceDateInfo);

                    return Optional.of(new Member(name, personalId, clearanceDate));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static String determineCategory(String pattern, String path) {
        Optional<Member> result = Utility.searchHardDrive(pattern, path);

        if (result.isEmpty()) {
            return "Is not a member.";
        } else {
            Member member = result.get();

            if (member.getClearanceDate().isAfter(LocalDate.now().minusYears(1))) {
                return "Is current member.";
            } else {
                return "Is previous member.";
            }
        }
    }

    public static void clockIn(String pattern, String savefile, String searchfile) {
        Optional<Member> result = Utility.searchHardDrive(pattern, searchfile);

        if (result.isPresent()) {
            if (determineCategory(pattern, searchfile).equals("Is current member.")) {
                Member member = result.get();
                LocalDate date = LocalDate.now();

                Timestamp timestamp = new Timestamp(date, member.getPersonalIdentificationNumber(), member.getName());

                SerializationManager<Timestamp> sm = new SerializationManager<>();

                List<Timestamp> list = new ArrayList<>();
                list.add(timestamp);

                sm.append(list, savefile);

                System.out.println("Clocked in.");
            } else {
                System.out.println(("Not a current member."));
            }
        } else {
            System.out.println("No member found.");
        }
    }

    public static void printTimeStamps() {
        SerializationManager<Timestamp> sm = new SerializationManager<>();

        try {
            List<Timestamp> timestamps = sm.deserialize("src/timestamps.ser");
            System.out.println("All timestamps in system: ");
            timestamps.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
