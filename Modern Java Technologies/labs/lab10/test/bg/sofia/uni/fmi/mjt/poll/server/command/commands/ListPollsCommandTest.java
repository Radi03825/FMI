package bg.sofia.uni.fmi.mjt.poll.server.command.commands;

import bg.sofia.uni.fmi.mjt.poll.server.exceptions.NoActivePollsException;
import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.OutputParser;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListPollsCommandTest {

    private static PollRepository pollRepository;
    private static OutputParser outputParser;

    @BeforeAll
    public static void setUp() {
        pollRepository = mock(PollRepository.class);
        outputParser = mock(OutputParser.class);
    }

    @Test
    public void testExecuteWithNullRepository() {
        assertThrows(IllegalArgumentException.class, () -> new ListPollsCommand().execute(null), "Executing ListPollsCommand with null repository should throw an exception");
    }

    @Test
    public void testExecuteEmptyPolls() {
        when(pollRepository.getAllPolls()).thenReturn(Map.of());

        assertThrows(NoActivePollsException.class, () -> new ListPollsCommand().execute(pollRepository), "Executing ListPollsCommand with empty polls should throw an exception");
    }

    @Test
    public void testExecuteCorrectInput() throws NoActivePollsException {
        when(pollRepository.getAllPolls()).thenReturn(Map.of(1, new Poll("Question1", new TreeMap<>(Map.of("Option1", 2, "Option2", 1)))));

        String expected = "Polls: {{\"1\":{\"question\":\"Question1\",\"options\":{\"Option1\":2,\"Option2\":1}}}}";
        String actual = new ListPollsCommand().execute(pollRepository);

        assertEquals(expected, actual, "Executing ListPollsCommand with correct input should return correct output");
    }
    
}
