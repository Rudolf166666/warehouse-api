package com.example.demo.Requests;

import java.util.List;

public class SaleProductRequest {
    public static class Product extends RequestProduct{
        public Product() {
        }
    }
    private Long number;
    private Long warehouse_id;
    private List<Product> products;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(Long warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
