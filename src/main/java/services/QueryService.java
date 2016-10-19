package services;


import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.time.LocalDate;
import java.util.Arrays;

public class QueryService {
    public static AggregateIterable<Document> getMessageQuantityByDayOfWeek (MongoCollection<Document> messageColl){

        Document groupFields = new Document("_id", new Document("dayOfWeek", new Document("$dayOfWeek", "$date")));
                 groupFields.put("countOfMessages", new Document( "$sum", 1));
        Document groupBy = new Document("$group", groupFields);


        Document sortFields = new Document("_id.dayOfWeek", 1);
        Document sortBy = new Document("$sort", sortFields);

        AggregateIterable<Document> output = messageColl.aggregate(Arrays.asList(groupBy, sortBy));

        return output;
    }

    public static AggregateIterable<Document> getListMaxNumberOfFriendShipsByMonthRange (MongoCollection<Document> friendShipColl, LocalDate from , LocalDate to ){


        Document range =new Document("$gt", java.sql.Date.valueOf(from));
                 range.put("$lt", java.sql.Date.valueOf(to));

        Document matchFields =new Document("date", range);
        Document matchBy = new Document("$match", matchFields);

        Document groupFields = new Document("_id", new Document("month", new Document("$month", "$date")));
                 groupFields.put("countOfFriendShips",new Document( "$sum", 1));

        Document groupBy = new Document("$group", groupFields);

        Document sortFields = new Document("_id.month", 1);
        Document sortBy = new Document("$sort", sortFields);

        AggregateIterable<Document> output = friendShipColl.aggregate(Arrays.asList( matchBy,groupBy,sortBy ));

        return output;
    }

    public static AggregateIterable<Document> getMaxNumberOfFriendShipsByMonthRange (MongoCollection<Document> friendShipColl, LocalDate from , LocalDate to ){

        Document range =new Document("$gt", java.sql.Date.valueOf(from));
        range.put("$lt", java.sql.Date.valueOf(to));

        Document matchFields =new Document("date", range);
        Document matchBy = new Document("$match", matchFields);

        Document groupFields_1 = new Document("_id", new Document("month", new Document("$month", "$date")));
        groupFields_1.put("countOfFriendShips",new Document( "$sum", 1));

        Document groupBy_1 = new Document("$group", groupFields_1);

        Document sortFields = new Document("countOfFriendShips", 1);
        Document sortBy = new Document("$sort", sortFields);


        Document groupFields_2 = new Document("_id","max_friendships");
        groupFields_2.put("max",new Document("$max", "$countOfFriendShips"));

        Document groupBy_2 = new Document("$group", groupFields_2);



        AggregateIterable<Document> output = friendShipColl.aggregate(Arrays.asList( matchBy, groupBy_1, sortBy , groupBy_2 ));

        return output;
    }

    public static AggregateIterable<Document> getMinNumberViewedMoviesForUserWithNUmberOfFriends (MongoCollection<Document> userColl, int minFriends){

        Document limit = new Document("$gt", minFriends );
        Document matchFields =new Document("friendsCount", limit);

        Document matchBy = new Document("$match", matchFields);

        Document groupFields = new Document("_id","min_viewed_movies");
        groupFields.put("min",new Document("$min", "$viewedMovies"));

        Document groupBy = new Document("$group", groupFields);

        AggregateIterable<Document> output = userColl.aggregate(Arrays.asList( matchBy, groupBy));

        return output;
    }
}
