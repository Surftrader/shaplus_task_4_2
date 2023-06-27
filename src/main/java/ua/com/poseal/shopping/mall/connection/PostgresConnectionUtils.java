package ua.com.poseal.shopping.mall.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnectionUtils implements ConnectionUtils {

    public static final Logger logger = LoggerFactory.getLogger("LOGGER");
    public static final String URL = "url";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";
    public static final String DRIVER = "driver";

    public static final PostgresConnectionUtils instance = getInstance();

    private static synchronized PostgresConnectionUtils getInstance() {
        if (instance == null) {
            return new PostgresConnectionUtils();
        }
        return instance;
    }

    @Override
    public Connection getConnection(Properties properties) {
        Connection connection = null;
        logger.debug("Creating connection to database");
        try {
            Class.forName(properties.getProperty(DRIVER));
            connection = DriverManager.getConnection(
                    properties.getProperty(URL),
                    properties.getProperty(USERNAME),
                    properties.getProperty(PASSWORD));
            logger.debug("Connection opened");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error while creating connection to database", e);
        }
        return connection;
    }
}
