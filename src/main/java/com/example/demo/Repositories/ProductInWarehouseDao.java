package com.example.demo.Repositories;

import com.example.demo.Models.ProductInWarehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductInWarehouseDao extends CrudRepository<ProductInWarehouse,Long> {
    @Query("select piw from ProductInWarehouse piw where piw.product.id=:product_id and piw.warehouse.id=:warehouse_id")
    Optional<ProductInWarehouse> checkProductIsExistOnWarehouse(
            @Param("warehouse_id") Long warehouse_id,
            @Param("product_id") Long product_id);
}
