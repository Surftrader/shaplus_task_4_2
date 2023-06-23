package ua.com.poseal;

import org.apache.commons.lang3.time.StopWatch;
import ua.com.poseal.shopping.mall.service.ProductService;
import ua.com.poseal.shopping.mall.util.Loader;

import java.util.Optional;
import java.util.Properties;

import static ua.com.poseal.shopping.mall.util.PostgresConnectionUtils.logger;

public class App {
    public static final String PRODUCTS = "products";
    private static final String CATEGORY = "category";
    private static final String DEFAULT_CATEGORY = "Продукти";

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        // Load properties
        Properties properties = new Loader().getFileProperties();
        String category = Optional.ofNullable(System.getProperty(CATEGORY))
                .orElse(DEFAULT_CATEGORY);
        properties.setProperty(CATEGORY, category);

//        // create tables via scripts
//        // TODO:



        // Generate 3 billions rows in any tables
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ProductService productService = new ProductService(properties);
        long totalProducts = Long.parseLong(properties.getProperty(PRODUCTS));
        long validProductCount = 0;
        long invalidProductCount = 0;
        while (validProductCount <= totalProducts) {
            // before insert to validate DTO via hibernate-validator
            if (productService.save()) {
                validProductCount++;
            } else {
                invalidProductCount++;
            }
        }
        logger.info("{} products were saved in {}", validProductCount, stopWatch.getTime());
        logger.info("{} invalid products were generated", invalidProductCount);
//        stopWatch.stop();

        // Show shop address where the most products some type (parameter in console) is


    }
}