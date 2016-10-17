import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import services.DAO.DBConnectionDAO;
import services.DBDAOFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        DBConnectionDAO connectionDAO = new DBDAOFactory().getDAO();
        MongoClient connection = connectionDAO.getConnection();
        MongoDatabase db = connection.getDatabase( "mySocialDB" );

        MongoCollection<Document> userColl = db.getCollection("user");
        MongoCollection<Document> messageColl = db.getCollection("message");
        MongoCollection<Document> movieColl = db.getCollection("movie");
        MongoCollection<Document> trackColl = db.getCollection("track");
        MongoCollection<Document> friendShipColl = db.getCollection("friendShip");

        Random r = new Random();
        int index = r.nextInt(1_000_000);
        List<String> names = new ArrayList<String>(1_000);
        List<String> messages = new ArrayList<String>(1_000);
        List<String> movies = new ArrayList<String>(1_000);
        List<String> tracks = new ArrayList<String>(1_000);

        for (int i=0 ; i <= 1_000; i++){
            names.add(RandomStringUtils.randomAlphanumeric(15));
            messages.add(RandomStringUtils.randomAlphanumeric(45));
            movies.add(RandomStringUtils.randomAlphanumeric(5));
            tracks.add(RandomStringUtils.randomAlphanumeric(7));
        }
        /*names.forEach(
                (name)->{
                    System.out.println("name -> " + name);
                }
                );*/


        int age = r.nextInt(100);
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
        );

    }
}
