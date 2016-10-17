package services.DAO;

import com.mongodb.MongoClient;

public interface DBConnectionDAO {
    MongoClient getConnection() ;
}
