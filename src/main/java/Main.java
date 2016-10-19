import beans.*;
import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import services.CollectionInitService;
import services.DBConnectionMongoImpl;
import services.MongoTableInitService;
import services.QueryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        MongoClient connection = DBConnectionMongoImpl.getConnection();
        MongoDatabase db = connection.getDatabase( "mySocialDB" );

        MongoCollection<Document> userColl = db.getCollection("user");
        MongoCollection<Document> movieColl = db.getCollection("movie");
        MongoCollection<Document> trackColl = db.getCollection("track");
        MongoCollection<Document> messageColl = db.getCollection("messages");
        MongoCollection<Document> friendShipColl = db.getCollection("friendShip");

        /**
         * preparing data for table
         **/
        int capacity = 1_000;
        int trackNameLength = 15;
        int movieNameLength = 7;
        int userNameLength = 5;

        List<Track> tracks = CollectionInitService.initTrackCollection(new ArrayList<Track>(capacity), trackNameLength, capacity);
        List<Movie> movies = CollectionInitService.initMovieCollection(new ArrayList<Movie>(capacity), movieNameLength, capacity);
        List<User> users = CollectionInitService.initUserCollection(new ArrayList<User>(capacity), userNameLength, capacity);
        List<Message> messages = CollectionInitService.initMessageCollection(new ArrayList<Message>(2_000), users);
        List<FriendShip> friends = CollectionInitService.initFriendShipCollection(new ArrayList<FriendShip>(2_000), users);

        /**
         * inserting data into table
         **/
        MongoTableInitService.createAndFillTrackTable(trackColl, tracks);
        MongoTableInitService.createAndFillMovieTable(movieColl, movies);
        MongoTableInitService.createAndFillUserTable(userColl, users, movies, tracks);
        MongoTableInitService.createAndFillMessageTable(messageColl, messages);
        MongoTableInitService.createAndFillFriendShipTable(friendShipColl, friends);



        System.out.println("********************************* task 1 *********************************");
        System.out.println("*               Average number of messages by day of week                *");
        System.out.println("**************************************************************************");
        System.out.println();
        System.out.println("------------- quantity of messages by day of weeks------------------------");

        AggregateIterable<Document> output1 = QueryService.getMessageQuantityByDayOfWeek(messageColl);

        for (Document doc : output1) {
           System.out.println(doc + "            |");
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("********************************* task 2 *********************************");
        System.out.println("*            Max number of new friendships from month to month           *");
        System.out.println("**************************************************************************");
        System.out.println();
        System.out.println("----- quantity of new friendships  by month from input range ------------");

        LocalDate from = LocalDate.of(2016, 5, 1);
        LocalDate to = LocalDate.of(2016, 8, 25);

        AggregateIterable<Document> output2 = QueryService.getListMaxNumberOfFriendShipsByMonthRange(friendShipColl, from, to);

        for (Document doc : output2) {
            System.out.println(doc + "             |");
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println();




        System.out.println("------------------------max friendships count-----------------------------");

        AggregateIterable<Document> output3 = QueryService.getMaxNumberOfFriendShipsByMonthRange(friendShipColl, from, to);


        for (Document doc : output3) {
            System.out.println(doc + "                                 |");
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();



        System.out.println("********************************* task 3 *********************************");
        System.out.println("*   Min number of watched movies by users with more than 100 friends     *");
        System.out.println("**************************************************************************");
        System.out.println();
        System.out.println("--- Min number of watched movies by users with more than 100 friends -----");

        int minNumberFriends = 10;

        AggregateIterable<Document> output4 = QueryService.getMinNumberViewedMoviesForUserWithNUmberOfFriends(userColl, minNumberFriends);


        for (Document doc : output4) {
          System.out.println(doc + "                                                 |");
        }
        System.out.println("--------------------------------------------------------------------------");
    }



}
