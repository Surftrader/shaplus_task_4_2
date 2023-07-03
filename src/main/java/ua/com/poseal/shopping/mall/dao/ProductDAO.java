package ua.com.poseal.shopping.mall.dao;

import ua.com.poseal.shopping.mall.domain.Product;

import java.util.List;

public interface ProductDAO {

    void insertProducts(List<Product> products);
}
