package com.example.demo.Services;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Dto.WarehousesDto;
import com.example.demo.Exeptions.ApiException;
import com.example.demo.Models.Product;
import com.example.demo.Repositories.ProductDao;
import com.example.demo.Requests.ProceedProductRequest;
import com.example.demo.Requests.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private ProductDao productDao;

    public ProductService(@Autowired ProductDao productDao) {
        this.productDao = productDao;
    }


    public void checkProductRequest(List<Product>products,
                                                  List<? extends RequestProduct> specifiedProducts){
        boolean isValid=specifiedProducts
                .stream()
                .allMatch(e->e.getPrice()>0&&e.getCount()>0);
        if(!isValid)throw ApiException.generate409Exception("The specified product has no price or quantity");
        specifiedProducts.stream().forEach(p->{
            products.stream()
                    .filter(e->p.getProductId().equals(e.getId()))
                    .findFirst()
                    .orElseThrow(()-> {
                        throw ApiException.generate404Exception("Specified product does not exist");
                    });
        });
    }
    public void checkProductRequestForMoving(List<Product>products,
                                    List<?extends RequestProduct> specifiedProducts){
        boolean isValid=specifiedProducts.stream().allMatch(e->e.getCount()>0);
        if(!isValid)throw ApiException.generate409Exception("The specified product has no quantity");
        specifiedProducts.stream().forEach(p->{
            products.stream()
                    .filter(e->p.getProductId().equals(e.getId()))
                    .findFirst()
                    .orElseThrow(()-> {
                        throw ApiException.generate404Exception("Specified product does not exist");
                    });
        });
    }

    public String createProduct(Product product){
        productDao.findByNameOrArticle(product.getName(),product.getArticle())
                .ifPresent((p)->{
            throw ApiException.generate409Exception("Product with this name or article already exist");
        });
        productDao.save(product);
        return "Your product has been successfully created";
    }

    public void updateProductLastPurchasePrice(Product product,Integer price){
        product.setLastPurchasePrice(price);
        productDao.save(product);
    };
    public void updateProductLastSalePrice(Product product,Integer price){
        product.setLastSalePrice(price);
        productDao.save(product);
    };
    public List<Product> findAllProducts(List<Long> ids) {
        return productDao.findAllById(ids);
    }

    public ResponseEntity<Map<String,Object>> searchProducts(String name){
        Map<String,Object>response=new HashMap<>();
        Pageable pageWithSort = PageRequest.of(0, 20);
        Page<Product> products=productDao.searchProducts(name,pageWithSort);
        response.put("products",products.get());
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Map<String, Object>> getAllProducts(Integer page, Integer amount) {
        Map<String,Object>response=new HashMap<>();
        Pageable pageWithSort = PageRequest.of(page-1, amount);
        Page<ProductDto> products=productDao.getAllProducts(pageWithSort);
        Long total= productDao.count();
        response.put("products",products.get());
        response.put("page",page);
        response.put("total",total);
        response.put("pagesCount",Math.ceil((double)total/amount));
        return ResponseEntity.ok().body(response);
    }
}
