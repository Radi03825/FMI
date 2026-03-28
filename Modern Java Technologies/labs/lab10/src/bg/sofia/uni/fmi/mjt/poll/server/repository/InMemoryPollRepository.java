package bg.sofia.uni.fmi.mjt.poll.server.repository;

import bg.sofia.uni.fmi.mjt.poll.server.model.Poll;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPollRepository implements PollRepository {

    private static final int INITIAL_POLL_ID = 1;

    private Map<Integer, Poll> polls;
    private int pollId;

    public InMemoryPollRepository() {
        this.polls = new HashMap<>();
        this.pollId = INITIAL_POLL_ID;
    }

    @Override
    public int addPoll(Poll poll) {
        if (poll == null) {
            throw new IllegalArgumentException("Poll cannot be null");
        }

        int currentPollId = pollId++;
        polls.put(currentPollId, poll);

        return currentPollId;
    }

    @Override
    public Poll getPoll(int pollId) {
        if (!this.polls.containsKey(pollId)) {
            return null;
        }

        return this.polls.get(pollId);
    }

    @Override
    public Map<Integer, Poll> getAllPolls() {
        return Collections.unmodifiableMap(this.polls);
    }

    @Override
    public void clearAllPolls() {
        this.polls.clear();
        this.pollId = INITIAL_POLL_ID;
    }
    
}
