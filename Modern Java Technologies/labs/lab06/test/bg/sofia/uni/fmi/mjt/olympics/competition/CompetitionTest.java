package bg.sofia.uni.fmi.mjt.olympics.competition;

import bg.sofia.uni.fmi.mjt.olympics.competitor.Athlete;
import bg.sofia.uni.fmi.mjt.olympics.competitor.Competitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompetitionTest {

    private static Set<Competitor> competitors;

    @BeforeAll
    static void setUp() {
        competitors = Set.of(new Athlete("1", "Name", "Nationality"));
    }

    // Competition constructor tests
    @Test
    void testCreateCompetitionWithValidInput() {
        Competition competition = new Competition("Name", "Discipline", competitors);

        assertEquals("Name", competition.name(), "Creating a competition with valid name should set it correctly");
        assertEquals("Discipline", competition.discipline(), "Creating a competition with valid discipline should set it correctly");
        assertEquals(competitors, competition.competitors(), "Creating a competition with valid competitors should set them correctly");
    }

    @Test
    void testCreateCompetitionWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Competition(null, "Discipline", competitors),
                "Creating a competition with null name should throw IllegalArgumentException");
    }

    @Test
    void testCreateCompetitionWithBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Competition("", "Discipline", competitors),
                "Creating a competition with blank name should throw IllegalArgumentException");
    }

    @Test
    void testCreateCompetitionWithNullDiscipline() {
        assertThrows(IllegalArgumentException.class, () -> new Competition("Name", null, competitors),
                "Creating a competition with null discipline should throw IllegalArgumentException");
    }

    @Test
    void testCreateCompetitionWithBlankDiscipline() {
        assertThrows(IllegalArgumentException.class, () -> new Competition("Name", "", competitors),
                "Creating a competition with blank discipline should throw IllegalArgumentException");
    }

    @Test
    void testCreateCompetitionWithNullCompetitors() {
        assertThrows(IllegalArgumentException.class, () -> new Competition("Name", "Discipline", null),
                "Creating a competition with null competitors should throw IllegalArgumentException");
    }

    @Test
    void testCreateCompetitionWithEmptyCompetitors() {
        assertThrows(IllegalArgumentException.class, () -> new Competition("Name", "Discipline", new HashSet<>()),
                "Creating a competition with empty competitors should throw IllegalArgumentException");
    }

    @Test
    void testGetCompetitorsReturnUnmodifiableCollection() {
        Competition competition = new Competition("Name", "Discipline", competitors);

        assertThrows(UnsupportedOperationException.class, () -> competition.competitors().add(new Athlete("2", "Name2", "Nationality2")),
                "The collection returned by getCompetitors should be unmodifiable");
    }

    // equals tests
    @Test
    void testEqualsWithSameCompetitions() {
        Competition competition = new Competition("Name", "Discipline", competitors);

        assertEquals(competition, competition, "The equals method should return true for the same object");
    }

    @Test
    void testEqualsWithDifferentCompetitions() {
        Competition competition1 = new Competition("Name1", "Discipline", competitors);
        Competition competition2 = new Competition("Name2", "Discipline", competitors);

        assertNotEquals(competition1, competition2, "The equals method should return true for two equal competitions");
    }

}
