package managers;

import beans.User;

public interface PersonManager {
    void writePerson(User user);
    User readPerson();
    User readPerson(String name);

}
