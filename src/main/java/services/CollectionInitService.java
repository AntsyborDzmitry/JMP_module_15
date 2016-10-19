package services;

import beans.*;
import org.apache.commons.lang.RandomStringUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionInitService {
    private static Random r = new Random();

    public static List<User> initUserCollection (List<User> coll, int wordLength, int capacity) {
       User u ;
       for (int i=0 ; i <= capacity; i++){
           int age = r.nextInt(100);
           int listenTracks = r.nextInt(3000) + 10;
           int viewedMovies = r.nextInt(500) + 5;
           u = new User(RandomStringUtils.randomAlphanumeric(wordLength),age, viewedMovies, listenTracks);
           coll.add(u);
       }

       initFriendShipForUserCollection(coll, capacity);
       initMessagesForUserCollection(coll);

       return coll;
    }

    public static void initFriendShipForUserCollection (List<User> users, int capacity) {
       users.forEach((user)->{
           List<Integer> alreadyAddedFriends = new ArrayList<Integer>();
           List<FriendShip> friends = new ArrayList<FriendShip>();
           int maxNumberOfFriends = r.nextInt(20);

           for(int i = 0; maxNumberOfFriends >= i; i++){

               int friendKey = r.nextInt(capacity);
               LocalDate date = null;
               int month = 1;
               int day = 1;
               if(alreadyAddedFriends.contains(friendKey)){
                 i--;
               }else{

                   month = r.nextInt(12) + 1;
                   day = r.nextInt(28) + 1;
                   date = LocalDate.of(2016, month, day);


                   alreadyAddedFriends.add(friendKey);
                   User newFriend = users.get(friendKey);
                   String name = newFriend.getName();

                   friends.add(new FriendShip(name, date));
               }
           }
           user.setFriends(friends);
       });
    }

    public static void initMessagesForUserCollection (List<User> users) {
        users.forEach((user)->{
           int maxNumberOfMessages = r.nextInt(25);
           List<Message> messages = new ArrayList<Message>();
           LocalDate date = null;
           String msg = "";
           int month = 1;
           int day = 1;

           for(int i = 0; maxNumberOfMessages >= i; i++){
               month = r.nextInt(12) + 1;
               day = r.nextInt(28) + 1;
               date = LocalDate.of(2016, month, day);

               msg = RandomStringUtils.randomAlphanumeric(r.nextInt(100));

               messages.add(new Message(msg, date));
           }
           user.setMessages(messages);
        });
    }

    public static List<Movie> initMovieCollection (List<Movie> coll, int wordLength, long capacity) {
       Movie m ;
       for (int i=0 ; i <= capacity; i++){
           int likes = r.nextInt(100);
           m = new Movie(RandomStringUtils.randomAlphanumeric(wordLength), likes);
           coll.add(m);
       }
       return coll;
    }

    public static List<Track> initTrackCollection (List<Track> coll, int wordLength, long capacity) {
       Track t ;
       for (int i=0 ; i <= capacity; i++){
           int likes = r.nextInt(100);
           t = new Track(RandomStringUtils.randomAlphanumeric(wordLength),likes );
           coll.add(t);
       }
       return coll;
    }

    public static List<Message> initMessageCollection(List<Message> messages, List<User> users) {
        users.forEach(
                (user)->{
                    messages.addAll(user.getMessages());
                }

        );
        return messages;
    }
    public static List<FriendShip> initFriendShipCollection(List<FriendShip> friends, List<User> users) {
        users.forEach(
                (user)->{
                    friends.addAll(user.getFriends());
                }

        );
        return friends;
    }
}
