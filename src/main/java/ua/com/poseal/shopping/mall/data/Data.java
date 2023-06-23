package ua.com.poseal.shopping.mall.data;

import ua.com.poseal.shopping.mall.domain.Address;
import ua.com.poseal.shopping.mall.domain.City;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private final Map<Integer, City> cities;

    private final Map<Integer, Address> addresses;

    private final Map<Integer, String> stores;
    private final Map<Integer, String> categories;



    public Data() {
        this.cities = initCities();
        this.addresses = initAddresses();
        this.stores = initStores();
        this.categories = initCategory();

    }

    private Map<Integer, Address> initAddresses() {
        Map<Integer, Address> map = new HashMap<>();



        return map;
    }

    private Map<Integer, City> initCities() {
        Map<Integer, City> map = new HashMap<>();
        map.put(1, new City(1L,"Київ"));
        map.put(2, new City(2L,"Дніпро"));
        map.put(3, new City(3L,"Винниця"));
        map.put(4, new City(4L,"Львів"));
        map.put(5, new City(5L,"Одеса"));
        map.put(6, new City(6L,"Полтава"));
        map.put(7, new City(7L,"Харків"));
        return map;
    }

    private Map<Integer, String> initStores() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "ТЦ ЕПІЦЕНТР-1");
        map.put(2, "ТЦ ЕПІЦЕНТР-2");
        map.put(3, "ТЦ ЕПІЦЕНТР-3");
        map.put(4, "ТЦ ЕПІЦЕНТР-4");
        map.put(5, "ТЦ ЕПІЦЕНТР-5");
        map.put(6, "ТЦ ЕПІЦЕНТР-6");
        map.put(7, "ТЦ ЕПІЦЕНТР-7");
        map.put(8, "ТЦ ЕПІЦЕНТР-8");
        map.put(9, "ТЦ ЕПІЦЕНТР-9");
        map.put(10, "ТЦ ЕПІЦЕНТР-10");
        map.put(11, "ТЦ ЕПІЦЕНТР-11");
        map.put(12, "ТЦ ЕПІЦЕНТР-12");
        map.put(13, "ТЦ ЕПІЦЕНТР-13");
        map.put(14, "ТЦ ЕПІЦЕНТР-14");
        map.put(15, "ТЦ ЕПІЦЕНТР-15");
        map.put(16, "ТЦ ЕПІЦЕНТР-16");


        return map;
    }

    private Map<Integer, String> initCategory() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Продукти");
        map.put(2, "Зоотовари");
        map.put(3, "Електроніка");
        map.put(4, "Спортивні товари");
        map.put(5, "Канцтовари");
        map.put(6, "Побутова техніка");
        map.put(7, "Інструменти");
        map.put(8, "Краса та здоров`я");
        map.put(9, "Одяг, Взуття, Аксесуари");
        map.put(10, "Аптека");
        return map;
    }

    public Map<Integer, String> getStores() {
        return stores;
    }

    public Map<Integer, String> getCategories() {
        return categories;
    }
}
