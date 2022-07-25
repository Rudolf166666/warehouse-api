package com.example.demo.Controllers;

import com.example.demo.Requests.CreateWarehouseRequest;
import com.example.demo.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private WarehouseService warehouseService;
    public WarehouseController(@Autowired WarehouseService warehouseService) {
        this.warehouseService=warehouseService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> createWarehouse(@Validated @RequestBody CreateWarehouseRequest body){
        String message= warehouseService.createWarehouse(body.getName());
        Map<String,Object> res =new HashMap<>();
        res.put("message",message);
        return ResponseEntity.ok().body(res);
    }
    @GetMapping
    public  ResponseEntity<Map<String,Object>> getAllWarehouses(
            @RequestParam(required = false) String name,
            @RequestParam Integer page,
            @RequestParam Integer amount){

        if(!(name==null)){
           return warehouseService.getAllWarehouses(page,amount,name);
        }
        return warehouseService.getAllWarehouses(page,amount);
    }
    @GetMapping("/search")
    public ResponseEntity<Map<String,Object>> searchWarehouses(
            @RequestParam(required = false) String name){
        return warehouseService.searchWarehouses(name);
    }
}
