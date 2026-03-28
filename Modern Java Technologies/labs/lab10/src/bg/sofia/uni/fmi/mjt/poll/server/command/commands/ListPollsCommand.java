package bg.sofia.uni.fmi.mjt.poll.server.command.commands;

import bg.sofia.uni.fmi.mjt.poll.server.exceptions.NoActivePollsException;
import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;
import bg.sofia.uni.fmi.mjt.poll.server.output.messages.MessageConstants;
import bg.sofia.uni.fmi.mjt.poll.server.repository.PollRepository;

import java.util.Map;

public class ListPollsCommand implements Command {

    @Override
    public String execute(PollRepository pollRepository) throws NoActivePollsException {
        if (pollRepository == null) {
            throw new IllegalArgumentException("Poll repository is null");
        }

        Map<Integer, Poll> allPolls = pollRepository.getAllPolls();

        if (allPolls.isEmpty()) {
            throw new NoActivePollsException(MessageConstants.NO_ACTIVE_POLLS);
        }

        return createResponse(allPolls);
    }

    private String createResponse(Map<Integer, Poll> allPolls) {
        StringBuilder response = new StringBuilder();
        response.append("Polls: {");

        for (Map.Entry<Integer, Poll> pollEntry : allPolls.entrySet()) {
            response.append(
                String.format(
                    "{\"%d\":{\"question\":\"%s\",\"options\":%s}}",
                    pollEntry.getKey(),
                    pollEntry.getValue().question(),
                    createOptionsResponse(pollEntry.getValue().options())
                )
            );
        }

        return response.append("}").toString();
    }

    private String createOptionsResponse(Map<String, Integer> options) {
        StringBuilder response = new StringBuilder();
        response.append("{");

        for (Map.Entry<String, Integer> optionEntry : options.entrySet()) {
            response.append(
                String.format(
                    "\"%s\":%d",
                    optionEntry.getKey(),
                    optionEntry.getValue()
                )
            );

            response.append(",");
        }

        response.deleteCharAt(response.length() - 1);
        return response.append("}").toString();
    }

}
