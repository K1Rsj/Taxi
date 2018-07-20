package ua.finalproject.model.dao.connection.pool;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.constants.db.DbConfig;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.util.PropertyManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolHolder {

    /**
     * @see LogManager
     */
    private static final Logger logger = LogManager.getLogger
            (ConnectionPoolHolder.class);
    /**
     * @see DataSource
     */
    private static volatile DataSource jdbcConnectionPool;

    /**
     * @return DataSource
     * @throws ClassNotFoundException if can't load the driver
     */
    private static DataSource getConnectionPool() throws
            ClassNotFoundException {
        if (jdbcConnectionPool == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (jdbcConnectionPool == null) {
                    Class.forName(PropertyManager.CONFIG.getString
                            (DbConfig.DB_DRIVER));
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(PropertyManager.CONFIG.getString(DbConfig
                            .DB_URL));
                    ds.setUsername(PropertyManager.CONFIG.getString
                            (DbConfig.DB_USER));
                    ds.setPassword(PropertyManager.CONFIG.getString
                            (DbConfig.DB_PASSWORD));
                    ds.setMinIdle(Integer.valueOf(PropertyManager.CONFIG
                            .getString(DbConfig
                                    .DB_MIN_IDLE)));
                    ds.setMaxIdle(Integer.valueOf(PropertyManager.CONFIG
                            .getString(DbConfig
                                    .DB_MAX_IDLE)));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf
                            (PropertyManager.CONFIG
                                    .getString(DbConfig.DB_MAX_OPEN_PS)));
                    jdbcConnectionPool = ds;
                }
            }
        }
        return jdbcConnectionPool;
    }

    /**
     * @return connection from data source
     * @see Connection
     */
    public static Connection getConnection() {
        try {
            return getConnectionPool().getConnection();
        } catch (SQLException e) {
            logger.error(LogMessages.DB_CONNECTION_ERROR, e.getMessage());
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            logger.error(LogMessages.LOAD_DRIVER_CLASS_ERROR, e
                    .getMessage());
            throw new RuntimeException(e);

        }
    }
}
