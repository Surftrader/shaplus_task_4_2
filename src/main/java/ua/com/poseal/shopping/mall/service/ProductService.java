package ua.com.poseal.shopping.mall.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import ua.com.poseal.shopping.mall.dao.ProductDAO;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.util.Generator;

import java.util.Properties;
import java.util.Set;

public class ProductService {
    private final ProductDAO productDAO;
    private final Generator generator;
    private final Validator validator;

    public ProductService(Properties properties) {
        this.productDAO = new ProductDAO(properties);
        this.generator = new Generator();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public boolean save() {
        Product product = generator.generateProduct();
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        if (validate.isEmpty()) {
            productDAO.insert(product);
            return true;
        }
        return false;
    }
}
