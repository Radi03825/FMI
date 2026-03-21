package bg.sofia.uni.fmi.mjt.socialnetwork;

import bg.sofia.uni.fmi.mjt.socialnetwork.exception.UserRegistrationException;
import bg.sofia.uni.fmi.mjt.socialnetwork.post.ReactionType;
import bg.sofia.uni.fmi.mjt.socialnetwork.post.SocialFeedPost;
import bg.sofia.uni.fmi.mjt.socialnetwork.profile.DefaultUserProfile;
import bg.sofia.uni.fmi.mjt.socialnetwork.profile.Interest;
import bg.sofia.uni.fmi.mjt.socialnetwork.profile.UserProfile;

import java.util.*;

public class Main {
    
    public static void main(String[] args) throws UserRegistrationException {

        Set<Interest> test = EnumSet.noneOf(Interest.class);

        test.add(Interest.FOOD);

        System.out.println(test);

        UserProfile firstUser = new DefaultUserProfile("a");
        UserProfile secondUser = new DefaultUserProfile("b");
        UserProfile thirdUser = new DefaultUserProfile("c");
        UserProfile fourthUser = new DefaultUserProfile("d");

        SocialFeedPost socialFeedPost = new SocialFeedPost(firstUser, "content");

        System.out.println(socialFeedPost.addReaction(firstUser, ReactionType.LIKE));
        System.out.println(socialFeedPost.addReaction(secondUser, ReactionType.LIKE));
        System.out.println(socialFeedPost.addReaction(firstUser, ReactionType.LOVE));

        System.out.println(firstUser.addFriend(thirdUser));
        System.out.println(thirdUser.addFriend(firstUser));
//        System.out.println(firstUser.unfriend(thirdUser));
//        System.out.println(thirdUser.unfriend(secondUser));

       // secondUser.addFriend(thirdUser);
        fourthUser.addFriend(thirdUser);

        SocialNetwork socialNetwork = new SocialNetworkImpl();

        socialNetwork.registerUser(firstUser);
        socialNetwork.registerUser(secondUser);
        socialNetwork.registerUser(thirdUser);
        socialNetwork.registerUser(fourthUser);

        System.out.println(socialNetwork.getAllUsers());
        System.out.println(socialNetwork.getMutualFriends(firstUser, secondUser));
        System.out.println(socialNetwork.getAllProfilesSortedByFriendsCount());

        firstUser.addInterest(Interest.FOOD);
        thirdUser.addInterest(Interest.GAMES);
        fourthUser.addInterest(Interest.FOOD);
        //secondUser.addInterest(Interest.FOOD);
        //thirdUser.addInterest(Interest.GAMES);

        System.out.println(socialNetwork.getReachedUsers(socialFeedPost));
    }

}
