package com.example.demo.Repositories;

import com.example.demo.Dto.ProceedDto;
import com.example.demo.Models.ProceedGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProceedDao extends CrudRepository<ProceedGoods,Long> {
    @Query("select new com.example.demo.Dto.ProceedDto(" +
            "pg.id, " +
            "w.name, " +
            "p.name, " +
            "pg.countOfProducts, " +
            "pg.number, " +
            "pg.price, " +
            "(pg.price * pg.countOfProducts) as costs ) " +
            "from ProceedGoods pg " +
            "left join Warehouse w on w.id = pg.warehouse.id " +
            "left join Product p on pg.product.id =p.id " +
            "group by pg.id, p.id, w.id")
    Page<ProceedDto> getProceedStatistic(Pageable p);
}
