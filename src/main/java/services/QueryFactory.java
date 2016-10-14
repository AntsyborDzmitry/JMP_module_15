package services;


public class QueryFactory {
    public QueryFactory() {
    }
    public String getQuery (String queryName) throws IllegalArgumentException {
        PersonSQLQueriesEnum queryByName = PersonSQLQueriesEnum.valueOf(queryName);
        return queryByName.getQuery();
    }
}
