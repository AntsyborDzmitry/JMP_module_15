package services;

import services.DAOImpl.DBConnectionDAOSQLImpl;
import services.DAO.DBConnectionDAO;


public class DBDAOFactory {
    public DBDAOFactory() {
    }
    public DBConnectionDAO getDAO () {
        return new DBConnectionDAOSQLImpl();
    }
}
