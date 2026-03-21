package bg.sofia.uni.fmi.mjt.socialnetwork;

import bg.sofia.uni.fmi.mjt.socialnetwork.exception.UserRegistrationException;
import bg.sofia.uni.fmi.mjt.socialnetwork.post.Post;
import bg.sofia.uni.fmi.mjt.socialnetwork.post.SocialFeedPost;
import bg.sofia.uni.fmi.mjt.socialnetwork.profile.Interest;
import bg.sofia.uni.fmi.mjt.socialnetwork.profile.UserProfile;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SocialNetworkImpl implements SocialNetwork {

    private Set<UserProfile> users;
    private Set<Post> posts;

    public SocialNetworkImpl() {
        this.users = new HashSet<>();
        this.posts = new HashSet<>();
    }

    @Override
    public void registerUser(UserProfile userProfile) throws UserRegistrationException {
        checkIfUserProfileIsNull(userProfile);

        if (this.users.contains(userProfile)) {
            throw new UserRegistrationException("User is already registered");
        }

        this.users.add(userProfile);
    }

    @Override
    public Set<UserProfile> getAllUsers() {
        return Collections.unmodifiableSet(this.users);
    }

    @Override
    public Post post(UserProfile userProfile, String content) throws UserRegistrationException {
        checkIfUserProfileIsNull(userProfile);
        checkIfUserProfileIsNotRegistered(userProfile);

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content can not be null");
        }

        Post newPost = new SocialFeedPost(userProfile, content);
        this.posts.add(newPost);

        return newPost;
    }

    @Override
    public Collection<Post> getPosts() {
        return Collections.unmodifiableSet(this.posts);
    }

    @Override
    public Set<UserProfile> getReachedUsers(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post can not be null");
        }

        UserProfile postAuthor = post.getAuthor();
        Set<Interest> authorInterests = new HashSet<>(postAuthor.getInterests());
        Set<UserProfile> authorNetworkOfFriends = findNetworkOfFriends(postAuthor);

        Set<UserProfile> reachedUsers = new HashSet<>();
        Set<Interest> currentUserInterests;

        for (UserProfile user : authorNetworkOfFriends) {
            currentUserInterests = new HashSet<>(user.getInterests());
            currentUserInterests.retainAll(authorInterests);

            if (!user.equals(postAuthor) && !currentUserInterests.isEmpty()) {
                reachedUsers.add(user);
            }
        }

        return reachedUsers;
    }

    @Override
    public Set<UserProfile> getMutualFriends(UserProfile userProfile1, UserProfile userProfile2)
            throws UserRegistrationException {

        checkIfUserProfileIsNull(userProfile1);
        checkIfUserProfileIsNull(userProfile2);

        checkIfUserProfileIsNotRegistered(userProfile1);
        checkIfUserProfileIsNotRegistered(userProfile2);

        Set<UserProfile> mutualFriends = new HashSet<>(userProfile1.getFriends());
        mutualFriends.retainAll(userProfile2.getFriends());

        return mutualFriends;
    }

    @Override
    public SortedSet<UserProfile> getAllProfilesSortedByFriendsCount() {
        return new TreeSet<>(this.users);
    }

    private Set<UserProfile> findNetworkOfFriends(UserProfile userProfile) {
        Set<UserProfile> networkOfFriends = new HashSet<>();
        Deque<UserProfile> queue = new ArrayDeque<>();

        queue.push(userProfile);

        while (!queue.isEmpty()) {
            UserProfile currentUser = queue.poll();
            if (!networkOfFriends.contains(currentUser)) {
                networkOfFriends.add(currentUser);
                queue.addAll(currentUser.getFriends());
            }
        }

        return networkOfFriends;
    }

    private static void checkIfUserProfileIsNull(UserProfile userProfile) {
        if (userProfile == null) {
            throw new IllegalArgumentException("UserProfile can not be null");
        }
    }

    private void checkIfUserProfileIsNotRegistered(UserProfile userProfile) throws UserRegistrationException {
        if (!this.users.contains(userProfile)) {
            throw new UserRegistrationException("User is not registered");
        }
    }
    
}
