package ua.com.poseal.shopping.mall.domain;

import java.util.List;

public class Store {
    private Long id;
    private String name;
    private Address address;
    private List<Product> productList;

    public Store() {
    }

    public Store(String name) {
        this.name = name;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        return id.equals(store.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
