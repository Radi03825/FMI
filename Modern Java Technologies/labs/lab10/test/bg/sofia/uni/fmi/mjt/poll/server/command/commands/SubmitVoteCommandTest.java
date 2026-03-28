package bg.sofia.uni.fmi.mjt.poll.server.command.commands;

import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidArgumentsException;
import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;
import bg.sofia.uni.fmi.mjt.poll.server.output.messages.MessageConstants;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.OutputParser;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubmitVoteCommandTest {

    private static PollRepository pollRepository;
    private static OutputParser outputParser;
    private static String[] correctInput;

    @BeforeAll
    public static void setUp() {
        pollRepository = mock(PollRepository.class);
        outputParser = mock(OutputParser.class);
        correctInput = new String[] {"submit-vote", "1", "Option1"};
    }

    @Test
    public void testExecuteWithNullRepository() {
        assertThrows(IllegalArgumentException.class, () -> new SubmitVoteCommand(correctInput).execute(null), "Poll repository cannot be null");
    }

    @Test
    public void testExecuteWithIncorrectInputArgumentsCount() {
        assertThrows(InvalidArgumentsException.class, () -> new SubmitVoteCommand(new String[] {"submit-vote", "1"}).execute(pollRepository), "Invalid arguments");
    }

    @Test
    public void testExecuteWithIncorrectPollId() {
        assertThrows(InvalidArgumentsException.class, () -> new SubmitVoteCommand(new String[] {"submit-vote", "INVALID", "Option1"}).execute(pollRepository), "Invalid poll id");
    }

    @Test
    public void testExecuteWithIDReturnNull() {
        when(pollRepository.getPoll(1)).thenReturn(null);

        assertThrows(InvalidArgumentsException.class, () -> new SubmitVoteCommand(new String[] {"submit-vote", "1", "Option1"}).execute(pollRepository), "Poll does not exist");
    }

    @Test
    public void testExecuteWithInvalidOption() {
        when(pollRepository.getPoll(1)).thenReturn(new Poll("Question1", Map.of()));

        assertThrows(InvalidArgumentsException.class, () -> new SubmitVoteCommand(new String[] {"submit-vote", "1", "Option1"}).execute(pollRepository), "Option does not exist");
    }

    @Test
    public void testExecuteWithCorrectInput() throws InvalidArgumentsException {
        Poll poll = new Poll("Question1", new HashMap<>(Map.of("Option1", 0)));
        when(pollRepository.getPoll(1)).thenReturn(poll);

        String expectedOutput = String.format(MessageConstants.SUCCESSFULLY_SUBMITTED_VOTE, "Option1");
        String actualOutput = new SubmitVoteCommand(correctInput).execute(pollRepository);

        assertEquals(expectedOutput, actualOutput, "Successfully submitted vote");
    }
    
}
