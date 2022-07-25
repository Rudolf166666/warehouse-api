package com.example.demo.Models;

import com.example.demo.Enums.DocumentType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public class Document {

    private Long number;
    @NotNull
    @Enumerated
    private DocumentType documentType;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    @NotNull
    @Min(0)
    @Column(name = "count_of_products")
    private Integer countOfProducts;
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    public DocumentType getDocumentType() {
        return documentType;
    }

    public Integer getCountOfProducts() {
        return countOfProducts;
    }

    public Document() {
    }

    public Document(Long number, Product product,DocumentType documentType, Integer countOfProducts) {
        this.number = number;
        this.product = product;
        this.countOfProducts = countOfProducts;
        this.documentType=documentType;
    }


    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product products) {
        this.product = products;
    }
}
