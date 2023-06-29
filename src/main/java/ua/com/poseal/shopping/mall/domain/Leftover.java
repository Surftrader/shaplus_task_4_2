package ua.com.poseal.shopping.mall.domain;

public class Leftover {

    private Long storeId;
    private Long productId;
    private Long amountId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAmountId() {
        return amountId;
    }

    public void setAmountId(Long amountId) {
        this.amountId = amountId;
    }
}
