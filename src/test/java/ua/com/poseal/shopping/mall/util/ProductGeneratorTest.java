package ua.com.poseal.shopping.mall.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.poseal.shopping.mall.domain.Product;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductGeneratorTest {

    Properties properties;
    ProductGenerator generator;

    @BeforeEach
    void init() {
        properties = new Loader().getFileProperties();
        generator = new ProductGenerator();
    }

    @Test
    void generateId() {
        int categories = 10;
        Long actual = generator.generateId(categories);

        assertTrue(actual >= 1 & actual <= categories);
    }

    @Test
    void generatePrice() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field min_price = generator.getClass().getDeclaredField("MIN_PRICE");
        min_price.setAccessible(true);
        double minPrice = min_price.getDouble(generator);
        Field max_price = generator.getClass().getDeclaredField("MAX_PRICE");
        max_price.setAccessible(true);
        double maxPrice = max_price.getDouble(generator);

        Method method = ProductGenerator.class.getDeclaredMethod("generatePrice");
        method.setAccessible(true);

        BigDecimal actual = (BigDecimal) method.invoke(generator);

        assertTrue(new BigDecimal(minPrice).compareTo(actual) <= 0);
        assertTrue(new BigDecimal(maxPrice).compareTo(actual) >= 0);

    }

    @Test
    void generateName() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field min_length = generator.getClass().getDeclaredField("MIN_LENGTH");
        min_length.setAccessible(true);
        int minLength = min_length.getInt(generator);
        Field max_length = generator.getClass().getDeclaredField("MAX_LENGTH");
        max_length.setAccessible(true);
        int maxLength = max_length.getInt(generator);

        Method method = ProductGenerator.class.getDeclaredMethod("generateName");
        method.setAccessible(true);
        String actual = (String) method.invoke(generator);

        assertTrue(actual.length() >= minLength && actual.length() <= maxLength);
    }

    @Test
    void generateProducts() {
        long expected = 10;
        long categories = 15;
        List<Product> products = generator.generateProducts(expected, (int) categories);

        assertEquals(products.size(), expected);
    }
}