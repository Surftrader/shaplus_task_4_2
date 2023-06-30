package ua.com.poseal.shopping.mall.dao;

import org.apache.commons.lang3.time.StopWatch;
import ua.com.poseal.shopping.mall.connection.ConnectionUtils;
import ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils;
import ua.com.poseal.shopping.mall.dto.LeftoverDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ua.com.poseal.App.logger;
import static ua.com.poseal.shopping.mall.util.Loader.CATEGORY;

public class LeftoverDAO {
    private static final int MIN_AMOUNT = 5;
    private static final int MAX_AMOUNT = 1000;
    private final Properties properties;
    private final ConnectionUtils connectionUtils;

    private static final String SQL_INSERT = "" +
            "WITH l AS (SELECT * FROM shop.leftover)" +
            "INSERT INTO shop.leftover (store_id, product_id, amount) " +
            "SELECT s.id, p.id, floor(random() * (? - ? + 1) + ?)::int " +
            "FROM shop.stores s " +
            "CROSS JOIN shop.products p " +
            "WHERE NOT EXISTS( " +
            "SELECT 1 " +
            "FROM l " +
            "WHERE l.store_id = s.id AND l.product_id = p.id" +
            ")";

    private static final String SQL_FIND_MAX = "" +
            "SELECT ct.name AS city, a.name AS address, SUM(l.amount) AS amount " +
            "FROM shop.leftover AS l " +
            "         JOIN shop.stores AS s ON l.store_id = s.id " +
            "         JOIN shop.addresses AS a ON s.address_id = a.id " +
            "         JOIN shop.cities AS ct ON ct.id = a.city_id " +
            "         JOIN shop.products AS p ON p.id = l.product_id " +
            "         JOIN shop.categories AS c ON c.id = p.category_id " +
            "WHERE c.name = ? " +
            "GROUP BY city, address " +
            "ORDER BY amount DESC " +
            "FETCH FIRST 1 ROW ONLY";

    private static final String SQL_FIND_MAX_WITH_TIES = "" +
            "SELECT ct.name AS city, a.name AS address, SUM(l.amount) AS amount " +
            "FROM shop.leftover AS l " +
            "         JOIN shop.stores AS s ON l.store_id = s.id " +
            "         JOIN shop.addresses AS a ON s.address_id = a.id " +
            "         JOIN shop.cities AS ct ON ct.id = a.city_id " +
            "         JOIN shop.products AS p ON p.id = l.product_id " +
            "         JOIN shop.categories AS c ON c.id = p.category_id " +
            "WHERE c.name = ? " +
            "GROUP BY city, address " +
            "ORDER BY amount DESC " +
            "FETCH FIRST 1 ROW WITH TIES";

    public LeftoverDAO(Properties properties) {
        this.properties = properties;
        this.connectionUtils = new PostgresConnectionUtils();
    }

    public void insertDataIntoLeftover() {
        logger.debug("Entered insertDataIntoLeftover() method");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try (Connection connection = connectionUtils.getConnection(properties);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setInt(1, MAX_AMOUNT);
            statement.setInt(2, MIN_AMOUNT);
            statement.setInt(3, MIN_AMOUNT);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL error saving product");
        }

        stopWatch.stop();
        logger.info("3000000 rows were inserted into table shop.liftover in {} ms", stopWatch.getTime());
        logger.info("RPS = {} rows in ms", 3000000 / stopWatch.getTime());
        logger.debug("Exited insertDataIntoLeftover() method");
    }

    public List<LeftoverDTO> getMaxLeftover() {
        logger.debug("Entered getMaxLeftover() method");
        List<LeftoverDTO> dtos = getLeftoverDTOS(SQL_FIND_MAX, "");
        logger.debug("Exited getMaxLeftover() method");

        return dtos;
    }

    public List<LeftoverDTO> getMaxLeftoverWithTies() {
        logger.debug("Entered getMaxLeftoverWithTies() method");
        List<LeftoverDTO> dtos = getLeftoverDTOS(SQL_FIND_MAX_WITH_TIES, "with ties");
        logger.debug("Exited getMaxLeftoverWithTies() method");

        return dtos;
    }

    private List<LeftoverDTO> getLeftoverDTOS(String query, String way) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<LeftoverDTO> dtos = new ArrayList<>();
        try (Connection connection = connectionUtils.getConnection(properties);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, properties.getProperty(CATEGORY));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LeftoverDTO dto = new LeftoverDTO(
                        resultSet.getString("city"),
                        resultSet.getString("address"),
                        resultSet.getInt("amount"));
                dtos.add(dto);
            }

        } catch (SQLException e) {
            logger.error("SQL error finding product");
        }

        stopWatch.stop();
        logger.info("Get max leftover {} in {} ms", way, stopWatch.getTime());
        return dtos;
    }
}
