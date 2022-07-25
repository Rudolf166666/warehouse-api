package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @SequenceGenerator(name = "product_seq",sequenceName = "product_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "product_seq")
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String article;

    @NotNull
    private String name;

    @Column(insertable = false)
    @Min(message = "The product cannot be purchased for free", value = 0)
    private Integer lastPurchasePrice;
    @Column(insertable = false)
    @Min(message = "The product cannot be sold without payment", value = 0)
    private Integer lastSalePrice;

    public Product() {
    }

    public Product(String article, String name, Integer lastPurchasePrice) {
        this.article = article;
        this.name = name;
        this.lastPurchasePrice = lastPurchasePrice;
    }

    public Integer getLastSalePrice() {
        return lastSalePrice;
    }

    public Integer getLastPurchasePrice() {
        return lastPurchasePrice;
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public Long getId() {
        return id;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastPurchasePrice(Integer lastPurchasePrice) {
        this.lastPurchasePrice = lastPurchasePrice;
    }

    public void setLastSalePrice(Integer lastSalePrice) {
        this.lastSalePrice = lastSalePrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", name='" + name + '\'' +
                ", lastPurchasePrice=" + lastPurchasePrice +
                ", lastSalePrice=" + lastSalePrice +
                '}';
    }
}
