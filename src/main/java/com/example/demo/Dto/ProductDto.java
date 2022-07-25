package com.example.demo.Dto;

import java.util.List;

public class ProductDto {
    private Long id;
    private String name;
    private String article;
    private Integer lastPurchasePrice;
    private Integer lastSalePrice;
    private Long sum;
    private Long count;
    private Long income;
    private Long costs;

    public void setIncome(Long income) {
        this.income = income;
    }

    public void setCosts(Long costs) {
        this.costs = costs;
    }

    public Long getIncome() {
        return income;
    }

    public Long getCosts() {
        return costs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setLastPurchasePrice(Integer lastPurchasePrice) {
        this.lastPurchasePrice = lastPurchasePrice;
    }

    public void setLastSalePrice(Integer lastSalePrice) {
        this.lastSalePrice = lastSalePrice;
    }

    public void setCount(Long count) {
        this.count = count;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public Integer getLastPurchasePrice() {
        return lastPurchasePrice;
    }

    public Integer getLastSalePrice() {
        return lastSalePrice;
    }

    public Long getCount() {
        return count;
    }

    public ProductDto(Long id,
                      String name,
                      String article,
                      Integer lastPurchasePrice,
                      Integer lastSalePrice,
                      Long sum,
                      Long count,
                      Long income,
                      Long costs) {
        this.id = id;
        this.name = name;
        this.article = article;
        this.lastPurchasePrice = lastPurchasePrice;
        this.lastSalePrice = lastSalePrice;
        this.count = count;
        this.sum=sum;
        this.income=income;
        this.costs=costs;
    }
}
