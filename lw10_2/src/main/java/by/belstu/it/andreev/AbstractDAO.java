package by.belstu.it.andreev;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractDAO<K, T> {
    //Logger logger = Logger.getLogger(String.valueOf(AbstractDAO.class));

    protected Connection connection;
    public void close() throws SQLException {
        connection.close();
    }
    public void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }
    public abstract List<T> findAll() throws SQLException;
    public abstract T       findEntityById(K id) throws SQLException;
    public abstract boolean Create(T entity) throws SQLException;

}

