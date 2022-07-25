package com.example.demo.Services;

import com.example.demo.Dto.WarehousesDto;
import com.example.demo.Exeptions.ApiException;
import com.example.demo.Models.Warehouse;
import com.example.demo.Repositories.WarehouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WarehouseService {
    private WarehouseDao warehouseDao;
    @Autowired
    public WarehouseService( WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }
   public String createWarehouse(String name){
        Optional<Warehouse> isExist=warehouseDao.findByName(name);
        if(isExist.isPresent()){
            throw  ApiException.generate409Exception("Warehouse with this name already exist");
        }
        Warehouse newUser=warehouseDao.save(new Warehouse(name));
        return "The warehouse was successfully created";
   }
   public Warehouse findWarehouse(Long id){
        Warehouse warehouse= warehouseDao.findById(id)
                .orElseThrow(()->ApiException.generate404Exception("The specified warehouse does not exist"));
        return warehouse;
   }

   public ResponseEntity<Map<String,Object>> searchWarehouses(String name){
       Map<String,Object>response=new HashMap<>();
       Pageable pageWithSort = PageRequest.of(0, 20);
       Page<Warehouse> warehouses=warehouseDao.searchWarehouses(name,pageWithSort);
       response.put("warehouses",warehouses.get());
       return ResponseEntity.ok().body(response);
   }

   public ResponseEntity<Map<String,Object>> getAllWarehouses(Integer page,Integer amount){
        Map<String,Object>response=new HashMap<>();
       Pageable pageWithSort = PageRequest.of(page-1, amount);
       Page<WarehousesDto> warehouses=warehouseDao.getAllWarehouses(pageWithSort);
       Long total= warehouseDao.count();
       response.put("warehouses",warehouses.get());
       response.put("page",page);
       response.put("total",total);
       response.put("pagesCount",Math.ceil((double)total/amount));
       return ResponseEntity.ok().body(response);
   }
    public ResponseEntity<Map<String,Object>> getAllWarehouses(Integer page, Integer amount, String name){
        Map<String,Object>response=new HashMap<>();
        Pageable pageWithSort = PageRequest.of(page-1, amount);
        Page<WarehousesDto>warehouses=warehouseDao.getAllWarehouses(name,pageWithSort);
        Long total =warehouseDao.getCountOfWarehousesByName(name);
        response.put("warehouses",warehouses.get());
        response.put("page",page);
        response.put("total",total);
        response.put("pagesCount",Math.ceil((double)total/amount));

        return ResponseEntity.ok().body(response);
    }
}
