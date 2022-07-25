package com.example.demo.Models;

import javax.persistence.*;

@Entity
public class ProductInWarehouse {
    @Id
    @SequenceGenerator(name = "piw_seq",sequenceName = "piw_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "piw_seq")
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;
    private Integer count;

    public ProductInWarehouse() {
    }

    public ProductInWarehouse(Product product, Warehouse warehouse, Integer count) {
        this.product = product;
        this.warehouse = warehouse;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
