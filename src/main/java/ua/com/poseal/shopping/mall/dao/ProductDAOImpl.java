package ua.com.poseal.shopping.mall.dao;

import org.apache.commons.lang3.time.StopWatch;
import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.util.RowCounter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static ua.com.poseal.App.logger;

public class ProductDAOImpl implements ProductDAO{

    private static final String TABLE = "shop.products";
    private final Properties properties;
    private final ConnectionUtils connectionUtils;
    private static final String SQL_INSERT = "INSERT INTO shop.products (name, price, category_id) VALUES(?,?,?)";

    public ProductDAOImpl(Properties properties) {
        this.properties = properties;
        this.connectionUtils = new PostgresConnectionUtils();
    }

    @Override
    public void insertProducts(List<Product> products) {
        logger.debug("Entered insertProducts() method with product list = {} num", products.size());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int batchSize = 500;
        int totalProducts = products.size();
        int batches = (int) Math.ceil((double) totalProducts / batchSize);

        try (Connection connection = this.connectionUtils.getConnection(properties);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            connection.setAutoCommit(false);
            for (int i = 0; i < batches; i++) {
                int fromIndex = i * batchSize;
                int toIndex = Math.min(fromIndex + batchSize, totalProducts);
                List<Product> batch = products.subList(fromIndex, toIndex); //  odd
                for (Product product : batch) {
                    statement.setString(1, product.getName());
                    statement.setBigDecimal(2, product.getPrice());
                    statement.setLong(3, product.getCategoryId());
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("SQL error saving product", e);
        }
        stopWatch.stop();

        RowCounter rowCounter = new RowCounter(properties);
        long rows = rowCounter.countRows(TABLE);

        logger.info("{} valid products were inserted into table {}", rows, TABLE);
        logger.info("RPS = {}", 1000.0 * rows / stopWatch.getTime());
        logger.debug("Exited insertProducts() method");
    }
}
