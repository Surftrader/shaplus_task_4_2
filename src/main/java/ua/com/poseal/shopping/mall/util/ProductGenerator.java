package ua.com.poseal.shopping.mall.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.commons.lang3.time.StopWatch;
import ua.com.poseal.shopping.mall.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static ua.com.poseal.App.logger;

public class ProductGenerator {

    private static final double MIN_PRICE = 10;
    private static final double MAX_PRICE = 10000;
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 10;
    private static final int CATEGORIES = 10;
    private static final int STORES = 16;
    private static final int LETTERS_IN_ALPHABET = 26;
    private final Random random;
    private final Validator validator;

    public ProductGenerator() {
        this.random = new Random();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Product generateProduct() {
        return new Product(
                generateName(),
                generatePrice(),
                generateId(CATEGORIES),
                generateId(STORES));
    }

    private Long generateId(Integer size) {
        int nextInt = random.nextInt(size);
        return (long) nextInt + 1;
    }

    private BigDecimal generatePrice() {
        double randomValue = MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble();
        return BigDecimal.valueOf(randomValue).setScale(2, RoundingMode.HALF_UP);
    }

    private String generateName() {
        int length = random.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(LETTERS_IN_ALPHABET) + 'a');
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public List<Product> generateProducts(long numbers) {
        logger.debug("Entered generateProducts() method with parameter numbers={}", numbers);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Product> products = new LinkedList<>();
        long validProductCount = 0;
        long invalidProductCount = 0;
        while (validProductCount < numbers) {
            Product product = generateProduct();
            Set<ConstraintViolation<Product>> validate = validator.validate(product);
            if (validate.isEmpty()) {
                products.add(product);
                validProductCount++;
            } else {
                invalidProductCount++;
            }
        }

        stopWatch.stop();
        logger.info("{} products were generated in {} ms", products.size(), stopWatch.getTime());
        logger.info("{} invalid products were generated", invalidProductCount);
        logger.debug("Exited generateProducts() method");

        return products;
    }
}
