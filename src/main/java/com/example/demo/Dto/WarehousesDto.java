package com.example.demo.Dto;

public class WarehousesDto {
    private String name;
    private Long id;
    private Long count;
    private Long sum;

    public WarehousesDto() {
    }

    public WarehousesDto(String name, Long id, Long count, Long sum) {
        this.name = name;
        this.id = id;
        this.count = count;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
