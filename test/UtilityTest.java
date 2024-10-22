import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    @TempDir
    Path tempDir;

    @Test
    void DetermineCategory_MemberClearanceDateLessThanOneYearAgo_IsCurrentMember() throws IOException {
        Path path = tempDir.resolve("hard_drive.txt");
        Files.createFile(path);
        Files.writeString(path, "7703021234, Alhambra Aromes\n" + LocalDate.now().minusDays(1)); //Signed up one day ago
        String expected = "Is current member.";

        String result = Utility.determineCategory("Alhambra Aromes", path.toString());

        assertEquals(expected, result);
    }

    @Test
    void DetermineCategory_MemberClearanceDateMoreThanOneYearAgo_IsPreviousMember() throws IOException {
        Path path = tempDir.resolve("hard_drive.txt");
        Files.createFile(path);
        Files.writeString(path, "7703021234, Alhambra Aromes\n" + LocalDate.now().minusYears(2)); //Signed up one day ago
        String expected = "Is previous member.";

        String result = Utility.determineCategory("Alhambra Aromes", path.toString());

        assertEquals(expected, result);
    }

    @Test
    void DetermineCategory_MemberNotFound_IsNotMember() throws IOException {
        Path path = tempDir.resolve("hard_drive.txt");
        Files.createFile(path);
        Files.writeString(path, "7703021234, Alhambra Aromes\n" + LocalDate.now().minusYears(2)); //Signed up one day ago
        String expected = "Is not a member.";

        String result = Utility.determineCategory("Alice Albrecht", path.toString());

        assertEquals(expected, result);
    }

    @Test
    void SearchHardDrive_GivenNonExistentMember_EmptyOption() throws IOException {
        Path path = tempDir.resolve("hard_drive.txt");
        Files.createFile(path);
        Files.writeString(path, "7703021234, Alhambra Aromes\n" + LocalDate.now().minusYears(2));
        Optional<Member> expected = Optional.empty();

        Optional<Member> result = Utility.searchHardDrive("Alice Albrecht", path.toString());

        assertEquals(expected, result);
    }

    @Test
    void SearchHardDrive_GivenExistingMember_Member() throws IOException {
        Path path = tempDir.resolve("hard_drive.txt");
        Files.createFile(path);
        Files.writeString(path, "7703021234, Alhambra Aromes\n" + "1980-02-01");
        Optional<Member> expected = Optional.of(new Member("Alhambra Aromes", "7703021234", LocalDate.parse("1980-02-01")));

        Member result = Utility.searchHardDrive("7703021234", path.toString()).get();

        assertAll(
                () -> assertEquals("Alhambra Aromes", result.getName()),
                () -> assertEquals(LocalDate.parse("1980-02-01"), result.getClearanceDate())
        );
    }
}
