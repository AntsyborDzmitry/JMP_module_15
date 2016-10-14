package services;


public enum PersonSQLQueriesEnum {

    GET_PERSON {
        @Override
        public String getQuery() {
            return "SELECT * FROM person  where idPerson=(SELECT MAX(idPerson) FROM person)";
        }
    },
    SAVE_PERSON {
        @Override
        public String getQuery() {
            return " INSERT INTO person (name,age) VALUES (?,?)";
        }
    },
    GET_PERSON_BY_NAME {
        @Override
        public String getQuery() {
            return "SELECT * FROM person WHERE NAME = ?";
        }
    },
    GET_PERSON_BY_AGE {
        @Override
        public String getQuery() {
            return "SELECT * FROM person WHERE AGE = ?";
        }
    };

    public abstract String getQuery ();
}