package services;

import com.mongodb.MongoClient;

public class DBConnectionMongoImpl{

    private static MongoClient instance;
    private static final String HOST = "localhost";
    private static final int PORT = 27017 ;

    public DBConnectionMongoImpl() {
    }

    public static MongoClient getConnection(){
        if(instance == null){
            synchronized (DBConnectionMongoImpl.class) {
                if(instance == null){
                    instance = new MongoClient( HOST, PORT );;
                }
            }
        }
        return instance;
    }
}
