package com.example.demo.Repositories;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductDao extends JpaRepository<Product,Long> {
    Optional<Product> findByNameOrArticle(String name, String article);
    @Query("select p from Product p where id in :ids")
    List<Product> findAllById(@Param("ids") Set<Long> ids);
    @Query("select p from Product p where p.name like %:name%")
    Page<Product> searchProducts(@Param("name")String name, Pageable p);
    @Query("select new com.example.demo.Dto.ProductDto(p.id, \n " +
            "       p.name, \n" +
            "       p.article, \n "+
            "       p.lastPurchasePrice, \n " +
            "       p.lastSalePrice, \n " +
            "       sum(pw.count) as countOfProducts,\n " +
            "       count(w.id) as countOfWarehouse,\n " +
            "       sum(sg.price * sg.countOfProducts) as income,\n " +
            "       sum(pg.price * pg.countOfProducts) as costs)\n " +
            "from Product p\n " +
            "left join ProductInWarehouse pw on p.id = pw.product.id\n " +
            "left join Warehouse w on pw.warehouse = w.id\n " +
            "left join ProceedGoods pg on p.id = pg.product.id\n " +
            "left join SaleGoods sg on p.id = sg.product.id\n " +
            "group by p.id")
    Page<ProductDto> getAllProducts(Pageable p);
}
