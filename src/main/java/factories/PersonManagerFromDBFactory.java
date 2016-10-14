package factories;

import managers.PersonManager;
import managers.PersonManagerFromDB;

public class PersonManagerFromDBFactory extends AbstractManagerFactory{
    public PersonManagerFromDBFactory() {
    }

    public PersonManager createPersonManager() {
        return new PersonManagerFromDB();
    }
}
