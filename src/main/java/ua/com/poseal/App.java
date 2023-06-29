package ua.com.poseal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.poseal.shopping.mall.dto.LeftoverDTO;
import ua.com.poseal.shopping.mall.service.LeftoverService;
import ua.com.poseal.shopping.mall.service.ProductService;
import ua.com.poseal.shopping.mall.util.Loader;
import ua.com.poseal.shopping.mall.util.SQLExecutor;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class App {

    public static final Logger logger = LoggerFactory.getLogger("LOGGER");
    public static final String DB_FOLDER = "db";
    public static final String SQL_CREATE_TABLES = "V1__create_tables.sql";
    public static final String SQL_INSERT_DATA = "V2__insert_data.sql";
    public static final String PRODUCTS = "products";

    public static void main(String[] args) {
        try {
            App app = new App();
            app.run();
        } catch (Exception e) {
            logger.error("Error running program", e);
        }
    }

    private void run() {
        // Load properties
        Properties properties = new Loader().getFileProperties();

        // create tables via scripts
        SQLExecutor sqlExecutor = new SQLExecutor(properties);
        sqlExecutor.execute(DB_FOLDER + File.separator + SQL_CREATE_TABLES);
        sqlExecutor.execute(DB_FOLDER + File.separator + SQL_INSERT_DATA);

        // Generate products and insert them into a table
        ProductService productService = new ProductService(properties);
        productService.saveProducts(Long.parseLong(properties.getProperty(PRODUCTS)));

        // Fill leftover table
        LeftoverService leftoverService = new LeftoverService(properties);
        leftoverService.saveDataIntoLeftover();

        // query task
        List<LeftoverDTO> maxLeftover = leftoverService.findMaxLeftover();
        maxLeftover.forEach(s -> logger.info(String.valueOf(s)));
        // query task with ties
        maxLeftover = leftoverService.findMaxLeftoverWithTies();
        maxLeftover.forEach(s -> logger.info(String.valueOf(s)));
    }
}
