package services;

import beans.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class MongoTableInitService {

    public static void createAndFillTrackTable (MongoCollection dbColl, List<Track> tracks) {

        List<Document> data = new ArrayList<Document>();

        tracks.forEach(
                (track)->{
                    Document doc = new Document();

                    doc.put("title",track.getTitle());
                    doc.put("likes",track.getLikes());
                    data.add(doc);
                });
        dbColl.insertMany(data);
    }

    public static void createAndFillMovieTable (MongoCollection dbColl, List<Movie> movies) {

        List<Document> data = new ArrayList<Document>();

        movies.forEach(
                (movie)->{
                    Document doc = new Document();

                    doc.put("title",movie.getTitle());
                    doc.put("likes",movie.getLikes());
                    data.add(doc);
                });
        dbColl.insertMany(data);
    }

    public static void createAndFillUserTable (MongoCollection dbColl, List<User> users, List<Movie> movies, List<Track> tracks) {

        List<Document> data = new ArrayList<Document>();

        users.forEach(
                (user)->{
                    Document doc = new Document();
                    doc.put("name",user.getName());
                    doc.put("age",user.getAge());
                    doc.put("viewedMovies",user.getViewedMovies());
                    doc.put("listedTracks",user.getListedTracks());
                    doc.put("friendsCount",user.getFriendsCount());

                    Document friends = new Document();
                    List<FriendShip> myFriends = user.getFriends();
                    for (int x = 0; x < myFriends.size(); x++) {
                        Document friend = new Document();
                        friend.put("name", myFriends.get(x).getFriend() );
                        friend.put("date", java.sql.Date.valueOf((myFriends.get(x).getTimestamp())));

                        friends.put("friend_" + x, friend);
                    }
                    doc.put("friends" ,friends);

                    Document messages = new Document();
                    List<Message> myMessages = user.getMessages();
                    for (int x = 0; x < myMessages.size(); x++) {
                        Document message = new Document();
                        message.put("message", myMessages.get(x).getMessage());
                        message.put("date", java.sql.Date.valueOf((myMessages.get(x).getTimestamp())));
                        messages.put("message_" + x, message);
                    }

                    doc.put("messages",messages);

                    data.add(doc);
                });
        dbColl.insertMany(data);
    }

    public static void createAndFillMessageTable(MongoCollection<Document> messageColl, List<Message> messages) {
        List<Document> msg = new ArrayList<Document>();

        messages.forEach(
                        (message)->{
                            Document doc = new Document();

                            doc.put("message", message.getMessage());
                            doc.put("date", java.sql.Date.valueOf((message.getTimestamp())));
                            msg.add(doc);
                        });
        messageColl.insertMany(msg);
    }

    public static void createAndFillFriendShipTable(MongoCollection<Document> friendColl, List<FriendShip> friends) {
        List<Document> fr = new ArrayList<Document>();

        friends.forEach(
                        (friend)->{
                            Document doc = new Document();

                            doc.put("name", friend.getFriend());
                            doc.put("date", java.sql.Date.valueOf((friend.getTimestamp())));
                            fr.add(doc);
                        });
        friendColl.insertMany(fr);
    }
}
