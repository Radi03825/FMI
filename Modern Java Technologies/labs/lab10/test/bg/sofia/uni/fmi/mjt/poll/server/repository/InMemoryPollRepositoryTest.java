package bg.sofia.uni.fmi.mjt.poll.server.repository;

import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryPollRepositoryTest {

    private PollRepository pollRepository;

    @BeforeEach
    public void setUp() {
        pollRepository = new InMemoryPollRepository();
    }

    @Test
    public void testAddPollNullInput() {
        assertThrows(IllegalArgumentException.class, () -> pollRepository.addPoll(null), "Poll should throw IllegalArgumentException when input is null");
    }

    @Test
    public void testAddPoll() {
        Map<String, Integer> options = Map.of("Option1", 0, "Option2", 0, "Option3", 0);

        Poll poll1 = new Poll("Question1", options);
        int pollId1 = pollRepository.addPoll(poll1);

        assertEquals(poll1, pollRepository.getPoll(pollId1), "Poll should be added successfully");
    }

    @Test
    public void testGetPollInvalidIndex() {
        assertEquals(null, pollRepository.getPoll(1), "Poll should return null when index is invalid");
    }

    @Test
    public void testGetPoll() {
        Map<String, Integer> options = Map.of("Option1", 0, "Option2", 0, "Option3", 0);

        Poll poll1 = new Poll("Question1", options);
        int pollId1 = 1;
        pollRepository.addPoll(poll1);

        assertEquals(poll1, pollRepository.getPoll(pollId1), "Poll should be retrieved successfully");
    }

    @Test
    public void testGetAllPolls() {
        Map<String, Integer> options1 = Map.of("Option1", 0, "Option2", 0, "Option3", 0);
        Map<String, Integer> options2 = Map.of("Option4", 0, "Option5", 0, "Option6", 0);

        Poll poll1 = new Poll("Question1", options1);
        Poll poll2 = new Poll("Question2", options2);

        int pollId1 = pollRepository.addPoll(poll1);
        int pollId2 = pollRepository.addPoll(poll2);

        Map<Integer, Poll> expected = Map.of(pollId1, poll1, pollId2, poll2);
        assertEquals(expected, pollRepository.getAllPolls(), "All polls should be retrieved successfully");
    }

    @Test
    public void testClearAllPosts() {
        Map<String, Integer> options = Map.of("Option1", 0, "Option2", 0, "Option3", 0);

        Poll poll1 = new Poll("Question1", options);
        Poll poll2 = new Poll("Question2", options);

        pollRepository.addPoll(poll1);
        pollRepository.addPoll(poll2);

        assertEquals(2, pollRepository.getAllPolls().size(), "All polls should be added successfully");

        pollRepository.clearAllPolls();

        assertEquals(0, pollRepository.getAllPolls().size(), "All polls should be cleared successfully");
    }
    
}
