import beans.*;
import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import services.CollectionInitService;
import services.DAO.DBConnectionDAO;
import services.DBDAOFactory;
import services.MongoTableInitService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        DBConnectionDAO connectionDAO = new DBDAOFactory().getDAO();
        MongoClient connection = connectionDAO.getConnection();
        MongoDatabase db = connection.getDatabase( "mySocialDB" );

        MongoCollection<Document> userColl = db.getCollection("user");
        MongoCollection<Document> movieColl = db.getCollection("movie");
        MongoCollection<Document> trackColl = db.getCollection("track");
        MongoCollection<Document> messageColl = db.getCollection("messages");
        MongoCollection<Document> friendShipColl = db.getCollection("friendShip");


        List<Track> tracks = CollectionInitService.initTrackCollection(new ArrayList<Track>(1_000), 15, 1_000);
        List<Movie> movies = CollectionInitService.initMovieCollection(new ArrayList<Movie>(1_000), 5, 1_000);
        List<User> users = CollectionInitService.initUserCollection(new ArrayList<User>(1_000), 7, 1_000);
        List<Message> messages = CollectionInitService.initMessageCollection(new ArrayList<Message>(10_000), users);
        List<FriendShip> friends = CollectionInitService.initFriendShipCollection(new ArrayList<FriendShip>(10_000), users);


        MongoTableInitService.createAndFillTrackTable(trackColl, tracks);
        MongoTableInitService.createAndFillMovieTable(movieColl, movies);
        MongoTableInitService.createAndFillUserTable(userColl, users, movies, tracks);
        MongoTableInitService.createAndFillMessageTable(messageColl, messages);
        MongoTableInitService.createAndFillFriendShipTable(friendShipColl, friends);

            Document d = new Document("_id", new Document("dayOfWeek", new Document("$dayOfWeek", "$date")));
            d.put("countOfMessages", new Document( "$sum", 1));


        Document s = new Document("dayOfWeek", 1);
        AggregateIterable<Document> output = messageColl.aggregate(Arrays.asList(
            new Document("$group", d),
                new Document("$sort", s)
        ));

        for (Document doc : output) {
           System.out.println(doc);
        }

        Document dd = new Document("_id", new Document("month", new Document("$month", "$date")));
        dd.put("countOfFriendShips",new Document( "$sum", 1));
        //dd.put("min",new Document("$min", "$month"));

        Document range =new Document("$gt", java.sql.Date.valueOf(LocalDate.of(2016, 7, 1)));
        range.put("$lt", java.sql.Date.valueOf(LocalDate.of(2016, 8, 25)));
        Document mm =new Document("date", range);

        AggregateIterable<Document> output1 = friendShipColl.aggregate(Arrays.asList(
                    new Document("$match",mm),
                    new Document("$group", dd)
                ));

                for (Document doc : output1) {
                    System.out.println(doc);
                }



        Document ddd = new Document("_id",1);
        ddd.put("min",new Document("$min", "$viewedMovies"));

        Document range1 =new Document("$gt", 10 );
        Document mmm =new Document("friendsCount", range1);


        AggregateIterable<Document> output2 = userColl.aggregate(Arrays.asList(
                          new Document("$match",mmm),
                          new Document("$group", ddd)
                      ));

                      for (Document doc : output2) {
                          System.out.println(doc);
                      }
    }



}
