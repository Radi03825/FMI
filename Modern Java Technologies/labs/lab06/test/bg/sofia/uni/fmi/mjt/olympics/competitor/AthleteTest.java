package bg.sofia.uni.fmi.mjt.olympics.competitor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AthleteTest {

    private static Athlete athlete;

    @BeforeAll
    public static void setUp() {
        athlete = new Athlete("1", "Radoslav", "Bulgaria");
    }

    @Test
    void testGetMedalsReturnUnmodifiableCollection() {
       assertThrows(UnsupportedOperationException.class, () -> athlete.getMedals().add(Medal.GOLD),
                "The collection returned by getMedals should be unmodifiable");
    }

    @Test
    void testAddMedalWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> athlete.addMedal(null),
                "The method addMedal should throw IllegalArgumentException when the medal is null");
    }

    @Test
    void testAddMedalCorrectInput() {
        athlete.addMedal(Medal.GOLD);

        assertTrue(athlete.getMedals().contains(Medal.GOLD),
                "The method addMedal should add the correct medal to the collection of medals");
    }

    @Test
    void testAddMedalSameTypeTwice() {
        athlete.addMedal(Medal.GOLD);
        athlete.addMedal(Medal.GOLD);

        assertEquals(2, athlete.getMedals().size(),
                "The method addMedal should add the medal two times");
    }
}
