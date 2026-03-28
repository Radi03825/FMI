package bg.sofia.uni.fmi.mjt.poll.server.command.commands;

import bg.sofia.uni.fmi.mjt.poll.server.exceptions.InvalidArgumentsException;
import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;
import bg.sofia.uni.fmi.mjt.poll.server.output.messages.MessageConstants;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;

public class SubmitVoteCommand implements Command {

    private static final int ARGUMENTS_COUNT = 3;
    private static final int POLL_ID_INDEX = 1;
    private static final int OPTION_INDEX = 2;

    private final String[] arguments;

    public SubmitVoteCommand(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(PollRepository pollRepository) throws InvalidArgumentsException {
        if (pollRepository == null) {
            throw new IllegalArgumentException("Poll repository cannot be null");
        }

        if (arguments.length != ARGUMENTS_COUNT) {
            throw new InvalidArgumentsException(MessageConstants.SUBMIT_VOTE_INVALID_ARGUMENTS);
        }

        int pollId = getPollId();
        String option = this.arguments[OPTION_INDEX];

        Poll poll = pollRepository.getPoll(pollId);
        if (poll == null) {
            throw new InvalidArgumentsException(String.format(MessageConstants.POLL_DOES_NOT_EXIST, pollId));
        } else if (!poll.options().containsKey(option)) {
            throw new InvalidArgumentsException(String.format(MessageConstants.OPTION_DOES_NOT_EXIST, option));
        }

        Integer currentScore = poll.options().get(option);
        poll.options().put(option, currentScore + 1);

        return String.format(MessageConstants.SUCCESSFULLY_SUBMITTED_VOTE, option);
    }

    private int getPollId() throws InvalidArgumentsException {
        try {
            return Integer.parseInt(this.arguments[POLL_ID_INDEX]);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.SUBMIT_VOTE_INVALID_POLL_ID);
        }
    }

}
