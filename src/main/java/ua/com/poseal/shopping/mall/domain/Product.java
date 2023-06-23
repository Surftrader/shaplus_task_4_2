package ua.com.poseal.shopping.mall.domain;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class Product {
    private Long id;

    @Length(min = 4, max = 9)
    private String name;
    private BigDecimal price;
    private Category category;
    private Store store;

    public Product() {

    }

    public Product(String name, BigDecimal price, Category category, Store store) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.store = store;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
