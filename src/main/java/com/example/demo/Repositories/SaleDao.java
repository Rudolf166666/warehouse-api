package com.example.demo.Repositories;

import com.example.demo.Dto.SaleDto;
import com.example.demo.Models.SaleGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SaleDao extends CrudRepository<SaleGoods,Long> {
    @Query("select new com.example.demo.Dto.SaleDto(s.id, " +
            "w.name, " +
            "p.name, " +
            "s.countOfProducts, " +
            "s.number, " +
            "s.price, " +
            "(s.price * s.countOfProducts) as income) " +
            "from SaleGoods s " +
            "left join Warehouse w on w.id = s.warehouse.id " +
            "left join Product p on s.product.id =p.id " +
            "group by s.id, p.id, w.id")
    Page<SaleDto> getSaleStatistic(Pageable p);
}
