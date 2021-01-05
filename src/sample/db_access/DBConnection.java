package sample.db_access;

import java.sql.Connection;

public interface DBConnection {

    Connection openConnection();

    void closeConnection(Connection connection);
}
