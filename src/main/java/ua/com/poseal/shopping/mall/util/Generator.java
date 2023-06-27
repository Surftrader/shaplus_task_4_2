package ua.com.poseal.shopping.mall.util;

import ua.com.poseal.shopping.mall.data.Data;
import ua.com.poseal.shopping.mall.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Generator {

    private static final double MIN_PRICE = 10;
    private static final double MAX_PRICE = 10000;
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 10;
    private final Data data;
    private final Random random;

    public Generator() {
        this.data = new Data();
        this.random = new Random();
    }

    public Product generateProduct() {
        int stores = data.getStores().size();
        int categories = data.getCategories().size();
        return new Product(
                generateName(),
                generatePrice(),
                generateId(categories),
                generateId(stores));
    }

    private Long generateId(int size) {
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
            char randomChar = (char) (random.nextInt(26) + 'a');
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
