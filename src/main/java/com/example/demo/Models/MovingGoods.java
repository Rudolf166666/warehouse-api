package com.example.demo.Models;

import com.example.demo.Enums.DocumentType;

import javax.persistence.*;
import java.util.List;

@Entity
public class MovingGoods extends Document {
    @Id
    @SequenceGenerator(name = "moving_seq",sequenceName = "moving_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "moving_seq")
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse initialWarehouse;

    @ManyToOne
    @JoinColumn(name = "final_warehouse_id")
    private Warehouse finalWarehouse;

    public MovingGoods() {
        super();
    }

    public MovingGoods(Long number, Product product, Integer count, Warehouse initialWarehouse, Warehouse finalWarehouse) {
        super(number, product, DocumentType.MOVING, count);
        this.initialWarehouse = initialWarehouse;
        this.finalWarehouse = finalWarehouse;
    }

    public Long getId() {
        return id;
    }

    public Warehouse getFinalWarehouse() {
        return finalWarehouse;
    }

    public void setFinalWarehouse(Warehouse finalWarehouse) {
        this.finalWarehouse = finalWarehouse;
    }

    public Warehouse getInitialWarehouse() {
        return initialWarehouse;
    }

    public void setInitialWarehouse(Warehouse initialWarehouse) {
        this.initialWarehouse = initialWarehouse;
    }
}
