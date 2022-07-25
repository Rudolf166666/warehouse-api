package com.example.demo.Models;

import com.example.demo.Enums.DocumentType;

import javax.persistence.*;

@Entity
public class ProceedGoods extends Document {
    @Id
    @SequenceGenerator(name = "proceed_seq",sequenceName = "proceed_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "proceed_seq")
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    private Integer price;

    public ProceedGoods() {
        super();
    }

    public ProceedGoods(Long number, Product product, Integer countOfProducts, Warehouse warehouse, Integer price) {
        super(number, product, DocumentType.PROCEED, countOfProducts);
        this.warehouse = warehouse;
        this.price=price;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
