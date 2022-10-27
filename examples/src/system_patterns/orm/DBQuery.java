package system_patterns.orm;

public enum DBQuery {
    INSERT("insert into users (id, login, password) values (?, ?, ?)"),
    UPDATE("update users set login = ?, password = ? where id = ?"),
    DELETE("delete from users where id = ?");

    String query;

    DBQuery(String query) {
        this.query = query;
    }

}
