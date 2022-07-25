package com.example.demo.Repositories;

import com.example.demo.Dto.WarehousesDto;
import com.example.demo.Models.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseDao extends CrudRepository<Warehouse,Long> {
    Optional<Warehouse> findByName(String name);
    @Query("select new com.example.demo.Dto.WarehousesDto(w.name,\n" +
            "       w.id,\n" +
            "       count(p.id) as type_of_products_count,\n" +
            "       sum(piw.count) as  products_count) \n" +
            "    from Warehouse w\n" +
            "    left join ProductInWarehouse piw on w.id = piw.warehouse.id\n" +
            "    left join Product p on piw.product.id =p.id\n" +
            "    group by w.id\n" +
            "    order by  products_count ASC \n")
    Page<WarehousesDto> getAllWarehouses(Pageable p);
    @Query("select new com.example.demo.Dto.WarehousesDto(w.name,\n" +
            "       w.id,\n" +
            "       count(p.id) as type_of_products_count,\n" +
            "       sum(piw.count) as  products_count) \n" +
            "    from Warehouse w\n" +
            "    left join ProductInWarehouse piw on w.id = piw.warehouse.id\n" +
            "    left join Product p on piw.product.id =p.id\n" +
            "    where w.name like %:name%\n "+
            "    group by w.id\n" +
            "    order by  products_count ASC \n")
    Page<WarehousesDto> getAllWarehouses(@Param("name") String name, Pageable p);
    @Query("select w from Warehouse w where w.name like %:name%")
    Page<Warehouse> searchWarehouses(@Param("name")String name,Pageable p);
    @Query("select count(w.id)\n " +
            "    from Warehouse w\n " +
            "    where w.name=:name")
    Long getCountOfWarehousesByName(@Param("name")String name);

}
