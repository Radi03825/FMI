package bg.sofia.uni.fmi.mjt.poll.server.output.messages;

public class MessageConstants {

    public static final String SUCCESSFULLY_CREATED_POLL = "Poll with id %d was successfully created";
    public static final String CREATE_POLL_INVALID_ARGUMENTS
        = "Usage: create-poll <question> <option-1> <option-2> [... <option-N]>";
    public static final String SUBMIT_VOTE_INVALID_ARGUMENTS = "Usage: submit-vote <poll-id> <option>";
    public static final String SUBMIT_VOTE_INVALID_POLL_ID = "Poll id should be a number";
    public static final String POLL_DOES_NOT_EXIST = "Poll with id %d does not exist";
    public static final String OPTION_DOES_NOT_EXIST = "Option %s does not exist";
    public static final String SUCCESSFULLY_SUBMITTED_VOTE = "Vote submitted successfully for option: %s";
    public static final String NO_ACTIVE_POLLS = "No active polls available.";

}
