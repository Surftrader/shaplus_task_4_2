package ua.com.poseal.shopping.mall.util;

import ua.com.poseal.shopping.mall.data.Data;
import ua.com.poseal.shopping.mall.domain.Category;
import ua.com.poseal.shopping.mall.domain.Product;
import ua.com.poseal.shopping.mall.domain.Store;

import java.math.BigDecimal;
import java.util.Random;

public class Generator {

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
                generateCategory(categories),
                generateStore(stores));
    }

    private Store generateStore(int stores) {
        int nextInt = random.nextInt(stores);
        int result = nextInt + 1;
        Store store = new Store();

        return null;
    }

    private Category generateCategory(int categories) {
        int nextInt = data.getCategories().size();
        int result = nextInt + 1;
        return null;
    }

    private BigDecimal generatePrice() {
        return null;
    }

    private String generateName() {
        return null;
    }
}
