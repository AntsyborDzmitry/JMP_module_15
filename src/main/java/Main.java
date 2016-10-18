import beans.*;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import services.DAO.DBConnectionDAO;
import services.DBDAOFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random r = new Random();

    public static void main(String[] args) {

        DBConnectionDAO connectionDAO = new DBDAOFactory().getDAO();
        MongoClient connection = connectionDAO.getConnection();
        MongoDatabase db = connection.getDatabase( "mySocialDB" );

        MongoCollection<Document> userColl = db.getCollection("user");
        MongoCollection<Document> movieColl = db.getCollection("movie");
        MongoCollection<Document> trackColl = db.getCollection("track");
        MongoCollection<Document> friendShipColl = db.getCollection("friendShip");


        int index = r.nextInt(1_000_000);

        List<Track> tracks = initTrackCollection(new ArrayList<Track>(1_000), 15, 1_000);
        List<Movie> movies = initMovieCollection(new ArrayList<Movie>(1_000), 5, 1_000);
        List<User> users = initUserCollection(new ArrayList<User>(1_000), 7, 1_000);

        createAndFillTrackTable(trackColl, tracks);
        createAndFillMovieTable(movieColl, movies);
        createAndFillUserTable(userColl, users, movies, tracks);

        /*names.forEach(
                (name)->{
                    System.out.println("name -> " + name);
                }
                );*/


/*
        Document doc = new Document ();

        doc.put("name", RandomStringUtils.randomAlphanumeric(10));
        doc.put("friends", "database123");
        doc.put("age", age);

        userColl.insertOne(doc);

        System.out.println("size -> " + names.size());

    FindIterable<Document> iterable = db.getCollection("user").find();

        iterable.forEach((Block<? super Document>) (Document document) -> {
                        System.out.println(
                                document.get("name") + "  " +
                                document.get("friends")+ "   " +
                                document.get("age")
                        );
                    }
        );*/

    }

    private static List initUserCollection (List<User> coll, int wordLength, int capacity) {
        User u ;
        for (int i=0 ; i <= capacity; i++){
            int age = r.nextInt(100);
            u = new User(RandomStringUtils.randomAlphanumeric(wordLength),age);
            coll.add(u);
        }

        initFriendShipForUserCollection(coll, capacity);
        initMessagesForUserCollection(coll);

        return coll;
    }

    private static void initFriendShipForUserCollection (List<User> users, int capacity) {
        int maxNumberOfFriends = r.nextInt(15);

        users.forEach((user)->{
            List<Integer> alreadyAddedFriends = new ArrayList<Integer>();
            List<FriendShip> friends = new ArrayList<FriendShip>();

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
                    user.setFriends(friends);
                }
            }
        });
    }

    private static void initMessagesForUserCollection (List<User> users) {
        int maxNumberOfMessages = r.nextInt(25);

        users.forEach((user)->{

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
        });
    }

    private static List initMovieCollection (List<Movie> coll, int wordLength, long capacity) {
        Movie m ;
        for (int i=0 ; i <= capacity; i++){
            m = new Movie(RandomStringUtils.randomAlphanumeric(wordLength), r.nextInt(100));
            coll.add(m);
        }
        return coll;
    }

    private static List initTrackCollection (List<Track> coll, int wordLength, long capacity) {
        Track t ;
        for (int i=0 ; i <= capacity; i++){
            t = new Track(RandomStringUtils.randomAlphanumeric(wordLength), r.nextInt(100));
            coll.add(t);
        }
        return coll;
    }

    private static void createAndFillTrackTable (MongoCollection dbColl, List<Track> tracks) {

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

    private static void createAndFillMovieTable (MongoCollection dbColl, List<Movie> movies) {

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

    private static void createAndFillUserTable (MongoCollection dbColl, List<User> users,  List<Movie> movies, List<Track> tracks) {

        List<Document> data = new ArrayList<Document>();

        users.forEach(
                (user)->{
                    Document doc = new Document();
                    doc.put("name",user.getName());
                    doc.put("age",user.getAge());

                    Document friends = new Document();
                    List<FriendShip> myFriends = user.getFriends();
                    for (int x = 0; x <= myFriends.size(); x++) {
                        friends.put("name", myFriends.get(x).getFriend() );
                        friends.put("date", myFriends.get(x).getTimestamp() );

                    }
                    doc.put("friends" ,friends);

                    Document messages = new Document(user.getMessages());
                    messages.put("messages",user.getMessages());

                    doc.put("messages",messages);

                    data.add(doc);
                });
        dbColl.insertMany(data);
    }
}
