package factories;


import managers.PersonManager;

public abstract class AbstractManagerFactory {

    public AbstractManagerFactory() {
    }

    public abstract PersonManager createPersonManager ();
}
