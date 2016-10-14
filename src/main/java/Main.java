
import beans.User;
import factories.AbstractManagerFactory;
import factories.PersonManagerFromDBFactory;
import managers.PersonManager;

public class Main {
    public static void main(String[] args) {
        /*
         * Work with Derby DB
         */

        AbstractManagerFactory dbManager = new PersonManagerFromDBFactory();
        PersonManager pmDB = dbManager.createPersonManager();
        User p = pmDB.readPerson();

        System.out.println("read last from DB ->    "+p.getName()+" , " + p.getAge());

        p = pmDB.readPerson("Vitalya");
        System.out.println("read by name from DB ->   "+p.getName()+" , " + p.getAge());

        pmDB.writePerson(new User("Toma",30));

        String name = "Toma";
        p = pmDB.readPerson(name);
        if (p!= null) {
            System.out.println("Write and read written by name from DB ->   "+p.getName()+" , " + p.getAge());
        }else {
            System.out.println("Entries with name " + name + " not found in file storage.");
        }

        System.out.println("*************************************************************************************");

    }
}
