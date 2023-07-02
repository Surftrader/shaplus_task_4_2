package ua.com.poseal.shopping.mall.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.poseal.shopping.mall.util.Loader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PostgresConnectionUtilsTest {

    Properties properties;

    @BeforeEach
    void init() {
        properties = new Loader().getFileProperties();
    }

    @Test
    void getConnection() throws SQLException {

        PostgresConnectionUtils postgresConnectionUtils = new PostgresConnectionUtils();
        Connection connection = postgresConnectionUtils.getConnection(properties);

        assertNotNull(connection);
        connection.close();
    }
}
