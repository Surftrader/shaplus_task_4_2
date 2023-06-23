package ua.com.poseal.shopping.mall.domain;

import java.util.List;

public class Accounting {
    private final List<Store> stores;

    public Accounting(List<Store> stores) {
        this.stores = stores;
    }

    public List<Store> getStores() {
        return stores;
    }
}
