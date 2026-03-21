package bg.sofia.uni.fmi.mjt.socialnetwork.post;

import bg.sofia.uni.fmi.mjt.socialnetwork.profile.UserProfile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SocialFeedPost implements Post {

    private static int id = 0;

    private final int postId;
    private UserProfile author;
    private String content;
    private final LocalDateTime publishedOn;
    private Map<ReactionType, Set<UserProfile>> reactions;

    public SocialFeedPost(UserProfile author, String content) {
        this.postId = getNextId();
        setAuthor(author);
        setContent(content);
        this.publishedOn = LocalDateTime.now();
        this.reactions = new EnumMap<>(ReactionType.class);
    }

    @Override
    public String getUniqueId() {
        return String.valueOf(postId);
    }

    @Override
    public UserProfile getAuthor() {
        return this.author;
    }

    @Override
    public LocalDateTime getPublishedOn() {
        return this.publishedOn;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public boolean addReaction(UserProfile userProfile, ReactionType reactionType) {
        checkIfUserIsNull(userProfile);
        checkIfReactionTypeIsNull(reactionType);

        boolean isRemoved = this.removeReaction(userProfile);

        this.reactions.putIfAbsent(reactionType, new HashSet<>());
        this.reactions.get(reactionType).add(userProfile);

        return !isRemoved;
    }

    @Override
    public boolean removeReaction(UserProfile userProfile) {
        checkIfUserIsNull(userProfile);

        ReactionType currentUserReaction = getCurrentUserReaction(userProfile);

        if (currentUserReaction != null) {
            this.reactions.get(currentUserReaction).remove(userProfile);

            if (this.reactions.get(currentUserReaction).isEmpty()) {
                this.reactions.remove(currentUserReaction);
            }
        }

        return (currentUserReaction != null);
    }

    @Override
    public Map<ReactionType, Set<UserProfile>> getAllReactions() {
        return Collections.unmodifiableMap(this.reactions);
    }

    @Override
    public int getReactionCount(ReactionType reactionType) {
        checkIfReactionTypeIsNull(reactionType);

        Set<UserProfile> userProfilesByReactionType = this.reactions.get(reactionType);

        return userProfilesByReactionType == null ? 0 : userProfilesByReactionType.size();
    }

    @Override
    public int totalReactionsCount() {
        int totalCount = 0;

        for (ReactionType reactionType : this.reactions.keySet()) {
            totalCount += getReactionCount(reactionType);
        }

        return totalCount;
    }

    private ReactionType getCurrentUserReaction(UserProfile userProfile) {

        for (Map.Entry<ReactionType, Set<UserProfile>> entry : this.reactions.entrySet()) {
            if (entry.getValue().contains(userProfile)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private static int getNextId() {
        return id++;
    }

    private static void checkIfUserIsNull(UserProfile userProfile) {
        if (userProfile == null) {
            throw new IllegalArgumentException("UserProfile cannot be null");
        }
    }

    private static void checkIfReactionTypeIsNull(ReactionType reactionType) {
        if (reactionType == null) {
            throw new IllegalArgumentException("ReactionType cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialFeedPost that = (SocialFeedPost) o;
        return postId == that.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(postId);
    }
    
}
