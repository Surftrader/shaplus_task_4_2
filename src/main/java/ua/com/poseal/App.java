package ua.com.poseal;

import ua.com.poseal.shopping.mall.service.ProductService;
import ua.com.poseal.shopping.mall.util.Loader;
import ua.com.poseal.shopping.mall.util.SQLExecutor;

import java.util.Properties;

public class App {
    public static final String PRODUCTS = "products";

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        // Load properties
        Properties properties = new Loader().getFileProperties();

        // create tables via scripts
        SQLExecutor sqlExecutor = new SQLExecutor(properties);
        sqlExecutor.execute("db/V1__create_tables.sql");
        sqlExecutor.execute("db/V2__insert_data.sql");

        ProductService productService = new ProductService(properties);
        productService.saveAll(Long.parseLong(properties.getProperty(PRODUCTS)));

        // Fill table liftover
        sqlExecutor.execute("db/V4__insert_data.sql");

        // TWO VARIANTS
        // task query
        sqlExecutor.execute("db/V5__leftover.sql");
        // task query with ties
        sqlExecutor.execute("db/V6__leftover_with_ties.sql");
    }
}
