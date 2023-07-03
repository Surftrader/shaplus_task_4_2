package ua.com.poseal.shopping.mall.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.util.Loader;
import ua.com.poseal.shopping.mall.util.ProductGenerator;
import ua.com.poseal.shopping.mall.util.RowCounter;
import ua.com.poseal.shopping.mall.util.SQLExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ua.com.poseal.App.PRODUCTS;

class ProductDAOImplTest {

    private ProductDAO productDAO;
    private ProductGenerator generator;
    private static Properties properties;

    @BeforeAll
    static void setUp() {
        properties = new Loader().getFileProperties();
        SQLExecutor sqlExecutor = new SQLExecutor(properties);
        sqlExecutor.execute("db" + File.separator + "V1__create_schema.sql");
        sqlExecutor.execute("db" + File.separator + "V2__create_tables.sql");
        sqlExecutor.execute("db" + File.separator + "V3__insert_data.sql");
    }

    @BeforeEach
    void init() {
        productDAO = new ProductDAOImpl(properties);
        generator = new ProductGenerator();
    }

    @Test
    void insertProducts() {
        int expected = 3;
        properties.setProperty(PRODUCTS, String.valueOf(expected));
        List<Product> products = initListProduct(expected);

        productDAO.insertProducts(products);
        long actual = new RowCounter(properties).countRows("shop.products");

        assertEquals(expected, actual);
    }

    private List<Product> initListProduct(int expected) {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < expected; i++) {
            Product product = generator.generateProduct();
            list.add(product);
        }
        return list;
    }


    @AfterAll
    static void tearDown() {
        Properties properties = new Loader().getFileProperties();
        SQLExecutor sqlExecutor = new SQLExecutor(properties);
        sqlExecutor.execute("db" + File.separator + "V1__create_schema.sql");
    }
}