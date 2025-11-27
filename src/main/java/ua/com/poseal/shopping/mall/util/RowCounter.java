package ua.com.poseal.shopping.mall.util;

import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static ua.com.poseal.App.logger;

public class RowCounter {
    private final Properties properties;
    private final ConnectionUtils connectionUtils;

    public RowCounter(Properties properties) {
        this.properties = properties;
        this.connectionUtils = new PostgresConnectionUtils();
    }

    public Long countRows(String table) {
        logger.debug("Entered countRows() method with parameter table={}", table);
        Long result = null;
        String query = "SELECT COUNT(*) FROM " + table;
        logger.info("Query -> {}", query);
        try (Connection connection = connectionUtils.getConnection(properties);
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("SQL error saving product", e);
        }
        logger.info("Count from {} = {}", table, result);
        logger.debug("Exited countRows() method");
        return result;
    }
}
