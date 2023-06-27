package ua.com.poseal.shopping.mall.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.commons.lang3.time.StopWatch;
import ua.com.poseal.shopping.mall.dao.ProductDAO;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.util.Generator;
import ua.com.poseal.shopping.mall.util.SQLExecutor;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static ua.com.poseal.shopping.mall.connection.PostgresConnectionUtils.logger;

public class ProductService {
    private final ProductDAO productDAO;
    private final Generator generator;
    private final Validator validator;

    public ProductService(Properties properties) {
        this.productDAO = new ProductDAO(properties);
        this.generator = new Generator();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private boolean save() {
        Product product = generator.generateProduct();
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        if (validate.isEmpty()) {
            productDAO.insert(product);
            return true;
        }
        return false;
    }

    public void saveAll(long numbers) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long validProductCount = 0;
        long invalidProductCount = 0;
        while (validProductCount < numbers) {
            if (save()) {
                validProductCount++;
            } else {
                invalidProductCount++;
            }
        }

        stopWatch.stop();
        logger.info("{} valid products were saved in {} sec", validProductCount, stopWatch.getTime(TimeUnit.SECONDS));
        logger.info("{} invalid products were generated", invalidProductCount);

    }
}
