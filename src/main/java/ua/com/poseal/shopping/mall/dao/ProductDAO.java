package ua.com.poseal.shopping.mall.dao;

import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils.logger;

public class ProductDAO {
    private final Properties properties;
    private final ConnectionUtils connectionUtils;
    private static final String SQL_INSERT = "INSERT INTO shop.products (name, price, category_id) VALUES(?,?,?)";

    public ProductDAO(Properties properties) {
        this.properties = properties;
        this.connectionUtils = new PostgresConnectionUtils();
    }

    public void insert(Product product) {
        try (Connection connection = connectionUtils.getConnection(properties);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setLong(3, product.getCategoryId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error saving product");
        }
    }
}
