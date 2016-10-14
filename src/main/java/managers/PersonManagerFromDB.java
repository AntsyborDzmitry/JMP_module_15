package managers;

import beans.User;
import services.DAO.DBConnectionDAO;
import services.DBDAOFactory;
import services.QueryFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonManagerFromDB implements PersonManager {
    private static final String GET_PERSON = "GET_PERSON";
    private static final String SAVE_PERSON = "SAVE_PERSON";
    private static final String GET_PERSON_BY_NAME = "GET_PERSON_BY_NAME";

    private DBConnectionDAO connectionDAO = new DBDAOFactory().getDAO();;
    private QueryFactory qf = new QueryFactory();

    public PersonManagerFromDB() {
    }

    public void writePerson(User user) {

        Object lock = new Object();
        String query = qf.getQuery(SAVE_PERSON);

        try ( Connection connection = connectionDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                synchronized (lock) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setInt(2, user.getAge());
                    preparedStatement.executeUpdate();
                }
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }

    public User readPerson() {
        String query = qf.getQuery(GET_PERSON);

        User user = null;

        try ( Connection connection = connectionDAO.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    user = createPerson(resultSet);
                }
            }
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public User readPerson(String name) {
        String query = qf.getQuery(GET_PERSON_BY_NAME);

        User user = null;

        try ( Connection connection = connectionDAO.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    user = createPerson(resultSet);
                }
            }
        } catch (SQLException | IllegalArgumentException | ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private User createPerson (ResultSet rs) throws SQLException {
        User user = new User();

        user.setName(rs.getString("NAME"));
        user.setAge(rs.getInt("AGE"));

        return user;
    }
}
