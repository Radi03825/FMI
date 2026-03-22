package bg.sofia.uni.fmi.mjt.olympics;

import bg.sofia.uni.fmi.mjt.olympics.competition.Competition;
import bg.sofia.uni.fmi.mjt.olympics.competition.CompetitionResultFetcher;
import bg.sofia.uni.fmi.mjt.olympics.competitor.Athlete;
import bg.sofia.uni.fmi.mjt.olympics.competitor.Competitor;
import bg.sofia.uni.fmi.mjt.olympics.competitor.Medal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MJTOlympicsTest {

    private static Athlete firstAthlete;
    private static Athlete secondAthlete;
    private static Set<Competitor> registeredCompetitors;

    private CompetitionResultFetcher competitionResultFetcherMock;
    private MJTOlympics olympics;

    @BeforeAll
    public static void setUp() {
        firstAthlete = new Athlete("1", "Name", "Nationality");
        secondAthlete = new Athlete("2", "Name2", "Nationality2");
        registeredCompetitors = Set.of(firstAthlete, secondAthlete);
    }

    @BeforeEach
    public void setUpEach() {
        competitionResultFetcherMock = Mockito.mock(CompetitionResultFetcher.class);
        olympics = new MJTOlympics(registeredCompetitors, competitionResultFetcherMock);
    }

    // updateMedalStatistics tests
    @Test
    void testUpdateMedalStatisticsWithValidInputIsAdded() {
       when(competitionResultFetcherMock.getResult(new Competition("CompetitionName", "Discipline", registeredCompetitors)))
                .thenReturn(new TreeSet<>(registeredCompetitors));

        olympics.updateMedalStatistics(new Competition("CompetitionName", "Discipline", registeredCompetitors));

        assertTrue(firstAthlete.getMedals().contains(Medal.GOLD),
                "Updating medal statistics with valid competition should return true");
    }

    @Test
    void testUpdateMedalStatisticsWithNullCompetition() {
        assertThrows(IllegalArgumentException.class, () -> olympics.updateMedalStatistics(null),
                "Updating medal statistics with null competition should throw IllegalArgumentException");

        verify(competitionResultFetcherMock, never()).getResult(null);
    }

    @Test
    void testUpdateMedalStatisticsWithNotRegisteredCompetitor() {
        Athlete notRegisteredAthlete = new Athlete("2", "NotRegistered", "Nationality");

        assertThrows(IllegalArgumentException.class,
                () -> olympics.updateMedalStatistics(new Competition("CompetitionName", "Discipline", Set.of(notRegisteredAthlete))),
                "Updating medal statistics with competition containing not registered competitor should throw IllegalArgumentException");

        verify(competitionResultFetcherMock, never()).getResult(any());
    }

    // getNationsRankList tests
    @Test
    void testGetNationsRankListIsNotEmpty() {
        assertFalse(olympics.getNationsRankList().isEmpty(), "Getting nations rank list with no competitions should return not empty set");
    }

    @Test
    void testGetNationsRankListWithEqualNumbersOfMedals() {
        Competition firstCompetition = new Competition("Competition2", "D2", Set.of(firstAthlete));
        Competition secondCompetition = new Competition("CompetitionName", "Discipline", Set.of(secondAthlete));

        when(competitionResultFetcherMock.getResult(firstCompetition))
                .thenReturn(new TreeSet<>(Set.of(firstAthlete)));
        when(competitionResultFetcherMock.getResult(secondCompetition))
                .thenReturn(new TreeSet<>(Set.of(secondAthlete)));

        olympics.updateMedalStatistics(firstCompetition);
        olympics.updateMedalStatistics(secondCompetition);

        assertEquals(firstAthlete.getNationality(), olympics.getNationsRankList().getFirst(),
                "Getting nations rank list with equal numbers of medals should return the first nation lexicographically");
    }

    @Test
    void testGetNationsRankListWithDifferentNumbersOfMedals() {
        Competition firstCompetition = new Competition("Competition2", "D2", registeredCompetitors);
        Competition secondCompetition = new Competition("CompetitionName", "Discipline", Set.of(secondAthlete));

        when(competitionResultFetcherMock.getResult(firstCompetition))
                .thenReturn(new TreeSet<>(registeredCompetitors));
        when(competitionResultFetcherMock.getResult(secondCompetition))
                .thenReturn(new TreeSet<>(Set.of(secondAthlete)));

        olympics.updateMedalStatistics(firstCompetition);
        olympics.updateMedalStatistics(secondCompetition);

        assertEquals(secondAthlete.getNationality(), olympics.getNationsRankList().getFirst(),
                "Getting nations rank list with different numbers of medals should return the nation with more medals");
    }

    // getTotalMedals tests
    @Test
    void testGetTotalMedalsWithNullNationality() {
        assertThrows(IllegalArgumentException.class, () -> olympics.getTotalMedals(null),
                "Getting total medals with null should throw IllegalArgumentException");
    }

    @Test
    void testGetTotalMedalsWithNotRegisteredNationality() {
        assertThrows(IllegalArgumentException.class, () -> olympics.getTotalMedals("NotRegistered"),
                "Getting total medals with not registered nationality should throw IllegalArgumentException");
    }

    @Test
    void testGetTotalMedalsWithRegisteredNationalityWithoutMedals() {
        assertEquals(0, olympics.getTotalMedals(firstAthlete.getNationality()),
                "Getting total medals with registered nationalities without medals should return 0");
    }

    @Test
    void testGetTotalMedalsWithRegisteredNationalityWithMedals() {
        Competition competition = new Competition("CompetitionName", "Discipline", registeredCompetitors);

        when(competitionResultFetcherMock.getResult(competition))
                .thenReturn(new TreeSet<>(registeredCompetitors));

        olympics.updateMedalStatistics(competition);

        assertEquals(1, olympics.getTotalMedals(firstAthlete.getNationality()),
                "Getting total medals with registered nationalities should return the correct number of medals");
    }

    @Test
    void testGetTotalMedalsWithNationalityWithMoreThanOneMedal() {
        Competition firstCompetition = new Competition("Competition2", "D2", registeredCompetitors);
        Competition secondCompetition = new Competition("CompetitionName", "Discipline", Set.of(secondAthlete));

        when(competitionResultFetcherMock.getResult(firstCompetition))
                .thenReturn(new TreeSet<>(registeredCompetitors));
        when(competitionResultFetcherMock.getResult(secondCompetition))
                .thenReturn(new TreeSet<>(Set.of(secondAthlete)));

        olympics.updateMedalStatistics(firstCompetition);
        olympics.updateMedalStatistics(secondCompetition);

        assertEquals(2, olympics.getTotalMedals(secondAthlete.getNationality()),
                "Getting total medals with registered nationality with more than one medal should return the correct number of medals");
    }
}
