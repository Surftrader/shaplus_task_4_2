package ua.com.poseal.shopping.mall.dto;

public class LeftoverDTO {

    private final String city;
    private final String address;
    private final int amount;

    public LeftoverDTO(String city, String address, int amount) {
        this.city = city;
        this.address = address;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return city + ", " + address + ", amount=" + amount;
    }
}
