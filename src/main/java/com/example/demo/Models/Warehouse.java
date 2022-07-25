package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Warehouse {
    @Id
    @SequenceGenerator(name = "warehouse_seq",sequenceName = "warehouse_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "warehouse_seq")
    private Long id;
    @NotEmpty(message = "The warehouse must have a name")
    @NotNull(message = "The warehouse must have a name")
    @Column(unique = true,nullable = false)
    private String name;

    public Warehouse() {
    }

    public Warehouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
