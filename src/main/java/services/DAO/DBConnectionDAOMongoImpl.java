package services.DAO;

import com.mongodb.MongoClient;

public class DBConnectionDAOMongoImpl implements DBConnectionDAO{
    private final String HOST = "localhost";
    private final int PORT = 27017 ;

    public DBConnectionDAOMongoImpl() {
    }

    public MongoClient getConnection()  {
        return  new MongoClient( HOST, PORT );

    }
}
