package ua.com.poseal.shopping.mall.service;

import ua.com.poseal.shopping.mall.dao.ProductDAO;
import ua.com.poseal.shopping.mall.dao.ProductDAOImpl;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.util.ProductGenerator;

import java.util.List;
import java.util.Properties;

import static ua.com.poseal.App.logger;

public class ProductService {
    private final ProductDAO productDAO;
    private final ProductGenerator productGenerator;

    public ProductService(Properties properties) {
        this.productDAO = new ProductDAOImpl(properties);
        this.productGenerator = new ProductGenerator();
    }

    public void saveProducts(long numbers) {
        logger.debug("Entered saveProducts() method with parameter {}", numbers);
        List<Product> products = productGenerator.generateProducts(numbers);
        productDAO.insertProducts(products);
        logger.debug("Exited saveProducts() method");
    }
}
