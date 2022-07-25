package com.example.demo.Dto;

import com.example.demo.Models.Product;
import com.example.demo.Models.Warehouse;

public class MoveDto {
    private Long id;
    private String finalWarehouseName;
    private String initialWarehouseName;
    private String productName;
    private Integer count;
    private Long number;

    public MoveDto() {
    }

    public MoveDto(Long id, String finalWarehouseName, String initialWarehouseName, String productName, Integer count, Long number) {
        this.id = id;
        this.finalWarehouseName = finalWarehouseName;
        this.initialWarehouseName = initialWarehouseName;
        this.productName = productName;
        this.count = count;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getFinalWarehouseName() {
        return finalWarehouseName;
    }

    public String getInitialWarehouseName() {
        return initialWarehouseName;
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
}
