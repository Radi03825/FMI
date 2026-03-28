package bg.sofia.uni.fmi.mjt.poll.server.command;

import bg.sofia.uni.fmi.mjt.poll.server.command.commands.Command;
import bg.sofia.uni.fmi.mjt.poll.server.command.commands.CreatePollCommand;
import bg.sofia.uni.fmi.mjt.poll.server.command.commands.ListPollsCommand;
import bg.sofia.uni.fmi.mjt.poll.server.command.commands.SubmitVoteCommand;
import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidCommandTypeException;

public class CommandFactory {

    private static final String CREATE_POLL_COMMAND = "create-poll";
    private static final String LIST_POLLS_COMMAND = "list-polls";
    private static final String SUBMIT_VOTE_COMMAND = "submit-vote";

    public static Command createCommand(String input) throws InvalidCommandTypeException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandTypeException("Invalid command type");
        }

        String[] commandParts = input.split("\\s+");
        String commandType = commandParts[0];

        return switch (commandType) {
            case CREATE_POLL_COMMAND -> new CreatePollCommand(commandParts);
            case LIST_POLLS_COMMAND -> {
                if (commandParts.length != 1) {
                    throw new InvalidCommandTypeException("Invalid command type: " + commandType);
                }

                yield new ListPollsCommand();
            }
            case SUBMIT_VOTE_COMMAND -> new SubmitVoteCommand(commandParts);
            default -> throw new InvalidCommandTypeException("Invalid command type: " + commandType);
        };
    }
    
}
