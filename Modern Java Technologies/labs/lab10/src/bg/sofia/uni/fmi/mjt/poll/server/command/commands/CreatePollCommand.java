package bg.sofia.uni.fmi.mjt.poll.server.command.commands;

import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidArgumentsException;
import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;
import bg.sofia.uni.fmi.mjt.poll.server.output.messages.MessageConstants;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;

import java.util.HashMap;

public class CreatePollCommand implements Command {

    private static final int MINIMUM_ARGUMENTS_COUNT = 4;
    private static final int QUESTION_INDEX = 1;

    private final String[] arguments;

    public CreatePollCommand(String... arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(PollRepository pollRepository) throws InvalidArgumentsException {
        if (pollRepository == null) {
            throw new IllegalArgumentException("PollRepository cannot be null");
        }

        Poll poll = createPoll();
        int pollId = pollRepository.addPoll(poll);

        return String.format(MessageConstants.SUCCESSFULLY_CREATED_POLL, pollId);
    }

    private Poll createPoll() throws InvalidArgumentsException {
        if (this.arguments.length < MINIMUM_ARGUMENTS_COUNT) {
            throw new InvalidArgumentsException(MessageConstants.CREATE_POLL_INVALID_ARGUMENTS);
        }

        String question = this.arguments[QUESTION_INDEX];

        HashMap<String, Integer> options = new HashMap<>();
        for (int i = QUESTION_INDEX + 1; i < this.arguments.length; i++) {
            options.put(this.arguments[i], 0);
        }

        return new Poll(question, options);
    }
    
}
