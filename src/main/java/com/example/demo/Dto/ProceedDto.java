package com.example.demo.Dto;

import com.example.demo.Models.Product;
import com.example.demo.Models.Warehouse;

public class ProceedDto {
    private Long id;
    private String warehouseName;
    private String productName;
    private Integer count;
    private Long number;
    private Integer price;
    private Integer costs;

    public ProceedDto() {
    }

    public ProceedDto(Long id,
                      String warehouseName,
                      String productName,
                      Integer count,
                      Long number,
                      Integer price,
                      Integer costs) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.productName = productName;
        this.count = count;
        this.number = number;
        this.price=price;
        this.costs=costs;
    }

    public Long getId() {
        return id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCount() {
        return count;
    }

    public Long getNumber() {
        return number;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getCosts() {
        return costs;
    }
}
