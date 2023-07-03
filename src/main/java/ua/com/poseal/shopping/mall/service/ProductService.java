package ua.com.poseal.shopping.mall.service;

import ua.com.poseal.shopping.mall.dao.ProductDAO;
import ua.com.poseal.shopping.mall.dao.ProductDAOImpl;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.util.ProductGenerator;
import ua.com.poseal.shopping.mall.util.RowCounter;

import java.util.List;
import java.util.Properties;

import static ua.com.poseal.App.logger;

public class ProductService {

    private Properties properties;
    private final ProductDAO productDAO;
    private final ProductGenerator productGenerator;

    public ProductService(Properties properties) {
        this.properties = properties;
        this.productDAO = new ProductDAOImpl(properties);
        this.productGenerator = new ProductGenerator();
    }

    public void saveProducts(long numbers) {
        logger.debug("Entered saveProducts() method with parameter {}", numbers);
        long categories = new RowCounter(properties).countRows("shop.categories");
        List<Product> products = productGenerator.generateProducts(numbers, (int) categories);
        productDAO.insertProducts(products);
        logger.debug("Exited saveProducts() method");
    }
}
