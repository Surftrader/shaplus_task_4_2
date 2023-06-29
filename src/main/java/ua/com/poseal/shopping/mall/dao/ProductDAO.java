package ua.com.poseal.shopping.mall.dao;

import org.apache.commons.lang3.time.StopWatch;
import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;
import ua.com.poseal.shopping.mall.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static ua.com.poseal.App.logger;

public class ProductDAO {
    private final Properties properties;
    private final ConnectionUtils connectionUtils;
    private static final String SQL_INSERT = "INSERT INTO shop.products (name, price, category_id) VALUES(?,?,?)";

    public ProductDAO(Properties properties) {
        this.properties = properties;
        this.connectionUtils = new PostgresConnectionUtils();
    }

    public void insertProducts(List<Product> products) {
        logger.debug("Entered insertProducts() method with product list = {} num", products.size());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try (Connection connection = this.connectionUtils.getConnection(properties);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            for (Product product : products) {
                statement.setString(1, product.getName());
                statement.setBigDecimal(2, product.getPrice());
                statement.setLong(3, product.getCategoryId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error("SQL error saving product");
        }

        stopWatch.stop();
        logger.info("{} products were inserted into table shop.products in {} ms", products.size(), stopWatch.getTime());
        logger.info("RPS = {} rows in ms", products.size() / stopWatch.getTime());
        logger.debug("Exited insertProducts() method");
    }
}
