package com.example.demo.Models;

import com.example.demo.Enums.DocumentType;

import javax.persistence.*;

@Entity
public class SaleGoods extends Document{
    @Id
    @SequenceGenerator(name = "sale_seq",sequenceName = "sale_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "sale_seq")
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @Column(nullable = false)
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public SaleGoods() {
    }

    public SaleGoods(Long number, Product product, Integer countOfProducts, Warehouse warehouse,Integer price) {
        super(number, product, DocumentType.SALE, countOfProducts);
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
