package com.example.demo.Requests;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class MovingProductRequest {
    public static class Product extends RequestProduct{
        public Product() {
        }
    }
    @NotNull
    private Long number;
    @NotNull
    private Long initialWarehouseId;
    @NotNull
    private Long finalWarehouseId;
    @NotNull
    @Size(min = 1)
    private List<Product> products;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getInitialWarehouseId() {
        return initialWarehouseId;
    }

    public void setInitialWarehouseId(Long initialWarehouseId) {
        this.initialWarehouseId = initialWarehouseId;
    }

    public Long getFinalWarehouseId() {
        return finalWarehouseId;
    }

    public void setFinalWarehouseId(Long finalWarehouseId) {
        this.finalWarehouseId = finalWarehouseId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
