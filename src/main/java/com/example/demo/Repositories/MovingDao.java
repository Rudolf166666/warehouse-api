package com.example.demo.Repositories;

import com.example.demo.Dto.MoveDto;
import com.example.demo.Dto.ProceedDto;
import com.example.demo.Models.MovingGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovingDao extends CrudRepository<MovingGoods,Long> {
    @Query("select new com.example.demo.Dto.MoveDto(m.id, fw.name, iw.name, p.name, m.countOfProducts, m.number) " +
            "from MovingGoods m " +
            "left join Warehouse fw on fw.id = m.finalWarehouse.id " +
            "left join Warehouse iw on iw.id = m.initialWarehouse.id " +
            "left join Product p on m.product.id =p.id " +
            "group by m.id, p.id, fw.id, iw.id")
    Page<MoveDto> getProceedStatistic(Pageable p);
}
