package com.example.demo.Requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateWarehouseRequest {
    @NotNull
    @NotEmpty()
    private String name;

    public CreateWarehouseRequest() {
    }

    public CreateWarehouseRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
