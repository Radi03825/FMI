package bg.sofia.uni.fmi.mjt.olympics.comparator;

import bg.sofia.uni.fmi.mjt.olympics.MJTOlympics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class NationMedalComparatorTest {

    private MJTOlympics olympicsMock = Mockito.mock(MJTOlympics.class);
    private NationMedalComparator nationMedalComparator = new NationMedalComparator(olympicsMock);

    @Test
    void testCompareWithEqualMedalsCount() {
        when(olympicsMock.getTotalMedals("Bulgaria")).thenReturn(5);
        when(olympicsMock.getTotalMedals("France")).thenReturn(5);

        assertTrue(nationMedalComparator.compare("Bulgaria", "France") < 0,
                "The comparator should return value less than 0 when the medals count is equal and the first nation in lexicographically");
    }

    @Test
    void testCompareWithDifferentMedalsCount() {
        when(olympicsMock.getTotalMedals("Bulgaria")).thenReturn(10);
        when(olympicsMock.getTotalMedals("France")).thenReturn(5);

        assertTrue(nationMedalComparator.compare("France", "Bulgaria") > 0,
                "The comparator should return value greater than 0 when the medals count is different");
    }

    @Test
    void testCompareWithEqualNamesAndMedalsCount() {
        when(olympicsMock.getTotalMedals("Bulgaria")).thenReturn(10);

        assertEquals(0, nationMedalComparator.compare("Bulgaria", "Bulgaria"),
                "The comparator should return 0 when the medals count is equal and the nations are equal");
    }
}
