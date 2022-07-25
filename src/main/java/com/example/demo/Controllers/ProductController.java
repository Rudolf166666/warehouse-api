package com.example.demo.Controllers;

import com.example.demo.Models.Product;
import com.example.demo.Requests.ProceedProductRequest;
import com.example.demo.Services.DocumentService;
import com.example.demo.Services.ProductService;
import com.example.demo.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
        public ResponseEntity<Map<String,Object>> createProduct(@RequestBody Product product ){
        String message=productService.createProduct(product);
        Map<String,Object> res =new HashMap<>();
        res.put("message",message);
        return ResponseEntity.ok().body(res);
        }
    @GetMapping("/search")
    public ResponseEntity<Map<String,Object>> searchWarehouses(
            @RequestParam(required = false) String name){
        return productService.searchProducts(name);
    }
    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllProducts(
            @RequestParam Integer page,
            @RequestParam Integer amount
    ){
        return productService.getAllProducts(page,amount);
    }
}
