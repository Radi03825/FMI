package bg.sofia.uni.fmi.mjt.poll.server.command;

import bg.sofia.uni.fmi.mjt.poll.server.command.commands.Command;
import bg.sofia.uni.fmi.mjt.poll.server.command.commands.CreatePollCommand;
import bg.sofia.uni.fmi.mjt.poll.server.command.commands.ListPollsCommand;
import bg.sofia.uni.fmi.mjt.poll.server.command.commands.SubmitVoteCommand;
import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidCommandTypeException;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.JsonParser;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.OutputParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandFactoryTest {

    private static OutputParser outputParser;

    @BeforeAll
    public static void setUp() {
        outputParser = new JsonParser();
    }

    @Test
    public void testCreateCommandNullInput() {
        assertThrows(InvalidCommandTypeException.class, () -> CommandFactory.createCommand(null));
    }

    @Test
    public void testCreateCommandEmptyInput() {
        assertThrows(InvalidCommandTypeException.class, () -> CommandFactory.createCommand(""));
    }

    @Test
    public void testCreateCommandInvalidInput() {
        assertThrows(InvalidCommandTypeException.class, () -> CommandFactory.createCommand("invalid input"));
    }

    @Test
    public void testCreateCommandCreatePollCommand() throws InvalidCommandTypeException {
        Command command = CommandFactory.createCommand("create-poll question option1 option2");

        assertInstanceOf(CreatePollCommand.class, command);
    }

    @Test
    public void testCreateCommandListPollsCommand() throws InvalidCommandTypeException {
        Command command = CommandFactory.createCommand("list-polls");

        assertInstanceOf(ListPollsCommand.class, command);
    }

    @Test
    public void testCreateCommandListPollsCommandInvalidInput() {
        assertThrows(InvalidCommandTypeException.class, () -> CommandFactory.createCommand("list-polls invalid input"));
    }

    @Test
    public void testCreateCommandSubmitVoteCommand() throws InvalidCommandTypeException {
        Command command = CommandFactory.createCommand("submit-vote 1 option1");

        assertInstanceOf(SubmitVoteCommand.class, command);
    }
    
}
