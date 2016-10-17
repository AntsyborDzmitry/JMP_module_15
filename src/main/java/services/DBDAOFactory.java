package services;


import services.DAO.DBConnectionDAO;
import services.DAO.DBConnectionDAOMongoImpl;


public class DBDAOFactory {
    public DBDAOFactory() {
    }
    public DBConnectionDAO getDAO () {
        return new DBConnectionDAOMongoImpl();
    }
}
