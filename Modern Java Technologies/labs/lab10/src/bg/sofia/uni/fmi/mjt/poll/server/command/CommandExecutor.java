package bg.sofia.uni.fmi.mjt.poll.server.command;

import bg.sofia.uni.fmi.mjt.poll.server.command.commands.Command;
import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidArgumentsException;
import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidCommandTypeException;
import bg.sofia.uni.fmi.mjt.poll.server.exceptions.NoActivePollsException;
import bg.sofia.uni.fmi.mjt.poll.server.output.parser.OutputParser;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;

public class CommandExecutor {

    private PollRepository pollRepository;
    private OutputParser outputParser;

    public CommandExecutor(PollRepository pollRepository, OutputParser outputParser) {
        this.pollRepository = pollRepository;
        this.outputParser = outputParser;
    }

    public String executeCommand(String input) {
        try {
            Command command = CommandFactory.createCommand(input);
            return outputParser.createResponse("OK", command.execute(pollRepository));
        } catch (InvalidCommandTypeException | InvalidArgumentsException | NoActivePollsException e) {
            return outputParser.createResponse("ERROR", e.getMessage());
        } catch (Exception e) {
            return outputParser.createResponse("General Error", e.getMessage());
        }
    }
    
}
