package com.example.demo.Requests;

public class RequestProduct {
    private Long productId;
    private Integer count;
    private Integer price;
    public Integer getPrice() {
        return price;
    }
    public Long getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public RequestProduct() {
    }
}
