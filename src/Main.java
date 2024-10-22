import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        label:
        while (true) {
            System.out.println("--Main menu--");
            Scanner scanner = new Scanner(System.in);
            System.out.println("E:Exit");
            System.out.println("S:Search");
            System.out.println("C:Clock in");
            System.out.println("V:View all timestamps");

            System.out.print("Selection: ");
            String input = scanner.nextLine();


            switch (input) {
                case "E":
                    break label;
                case "S": {
                    System.out.print("Member identifier: ");
                    String pattern = scanner.nextLine();
                    String result = Utility.determineCategory(pattern, "src/hard_drive.txt");

                    System.out.println(result);
                    break;
                }
                case "C": {
                    System.out.print("Member identifier: ");
                    String pattern = scanner.nextLine();
                    Utility.clockIn(pattern, "src/timestamps.ser", "src/hard_drive.txt");
                    break;
                }
                case "V": {
                    Utility.printTimeStamps();
                    break;
                }
                default: {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
}