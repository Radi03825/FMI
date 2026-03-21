package bg.sofia.uni.fmi.mjt.socialnetwork.profile;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DefaultUserProfile implements UserProfile, Comparable<UserProfile> {

    private String username;
    private Set<Interest> interests;
    private Set<UserProfile> friends;

    public DefaultUserProfile(String username) {
        setUsername(username);
        this.interests = EnumSet.noneOf(Interest.class);
        this.friends = new HashSet<>();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<Interest> getInterests() {
        return Collections.unmodifiableSet(this.interests);
    }

    @Override
    public boolean addInterest(Interest interest) {
        checkIfInterestIsNull(interest);

        return this.interests.add(interest);
    }

    @Override
    public boolean removeInterest(Interest interest) {
        checkIfInterestIsNull(interest);

        return this.interests.remove(interest);
    }

    @Override
    public Collection<UserProfile> getFriends() {
        return Collections.unmodifiableSet(this.friends);
    }

    @Override
    public boolean addFriend(UserProfile userProfile) {
        checkIfUserProfileIsNull(userProfile);
        checkIfUserProfileIsTheSame(userProfile);

        if (this.isFriend(userProfile)) {
            return false;
        }

        this.friends.add(userProfile);
        userProfile.addFriend(this);

        return true;
    }

    @Override
    public boolean unfriend(UserProfile userProfile) {
        checkIfUserProfileIsNull(userProfile);

        if (!this.isFriend(userProfile)) {
            return false;
        }

        this.friends.remove(userProfile);
        userProfile.unfriend(this);

        return true;
    }

    @Override
    public boolean isFriend(UserProfile userProfile) {
        checkIfUserProfileIsNull(userProfile);

        return this.friends.contains(userProfile);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private static void checkIfUserProfileIsNull(UserProfile userProfile) {
        if (userProfile == null) {
            throw new IllegalArgumentException("UserProfile cannot be null");
        }
    }

    private static void checkIfInterestIsNull(Interest interest) {
        if (interest == null) {
            throw new IllegalArgumentException("Interest cannot be null");
        }
    }

    private void checkIfUserProfileIsTheSame(UserProfile userProfile) {
        if (userProfile.equals(this)) {
            throw new IllegalArgumentException("UserProfile cannot be the same");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultUserProfile that = (DefaultUserProfile) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public int compareTo(UserProfile o) {
        if (this.equals(o)) {
            return 0;
        } else if (this.friends.size() == o.getFriends().size()) {
            return this.username.compareTo(o.getUsername());
        }

        return Integer.compare(o.getFriends().size(), this.friends.size());
    }

    @Override
    public String toString() {
        return String.format("Username: %s, friends count: %d", this.username, this.friends.size());
    }
    
}
