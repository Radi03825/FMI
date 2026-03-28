package bg.sofia.uni.fmi.mjt.poll.server.command.commands;

import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidArgumentsException;
import bg.sofia.uni.fmi.mjt.poll.server.output.messages.MessageConstants;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.OutputParser;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreatePollCommandTest {

    private static PollRepository pollRepository;
    private static OutputParser outputParser;
    private static String[] correctInput;
    private static String[] incorrectInput;

    @BeforeAll
    public static void setUp() {
        pollRepository = mock(PollRepository.class);
        outputParser = mock(OutputParser.class);
        correctInput = new String[] {"create-poll", "Question1", "Option1", "Option2", "Option3"};
        incorrectInput = new String[] {"create-poll", "Question1", "Option1"};
    }

    @Test
    public void testExecuteWithNullPollRepository() {
        assertThrows(IllegalArgumentException.class, () -> new CreatePollCommand(correctInput).execute(null), "CreatePollCommand should throw IllegalArgumentException when pollRepository is null");
    }

    @Test
    public void testExecuteWithInvalidArguments() {
        assertThrows(InvalidArgumentsException.class, () -> new CreatePollCommand(incorrectInput).execute(pollRepository), "CreatePollCommand should throw InvalidArgumentException when input is invalid");
    }

    @Test
    public void testExecuteWithValidInput() throws InvalidArgumentsException {
        when(pollRepository.addPoll(any())).thenReturn(1);

        String expected = String.format(MessageConstants.SUCCESSFULLY_CREATED_POLL, 1);
        String actual = new CreatePollCommand(correctInput).execute(pollRepository);

        assertEquals(expected, actual, "CreatePollCommand should return correct output when input is valid");
    }
    
}
