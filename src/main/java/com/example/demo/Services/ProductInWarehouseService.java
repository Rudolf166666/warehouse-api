package com.example.demo.Services;

import com.example.demo.Enums.DocumentType;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductInWarehouse;
import com.example.demo.Models.Warehouse;
import com.example.demo.Repositories.ProductInWarehouseDao;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductInWarehouseService {
    private ProductInWarehouseDao productInWarehouseDao;


    public ProductInWarehouseService( @Autowired ProductInWarehouseDao productInWarehouseDao) {
        this.productInWarehouseDao = productInWarehouseDao;
    }

    private Optional<ProductInWarehouse> checkProductIsExistOnWarehouse(Long warehouse_id, Long product_id){
        Optional<ProductInWarehouse> piw= productInWarehouseDao.checkProductIsExistOnWarehouse(warehouse_id,product_id);
        return piw;
    }
    private void addProductOnWarehouse(Product product,Warehouse warehouse, Integer count){
        Optional<ProductInWarehouse>piw= checkProductIsExistOnWarehouse(warehouse.getId(), product.getId());
        piw.ifPresentOrElse(productInWarehouse -> {
            productInWarehouse.setCount(productInWarehouse.getCount()+count);
            productInWarehouseDao.save(productInWarehouse);
        },()->{
            ProductInWarehouse newPiw=new ProductInWarehouse(product,warehouse,count);
            productInWarehouseDao.save(newPiw);
        });
    }
    private void removeProductFromWarehouse(Product product,Warehouse warehouse, Integer count){
        ProductInWarehouse piw= checkProductIsExistOnWarehouse(warehouse.getId(), product.getId())
                .orElseThrow(()-> new RuntimeException("The product is not in warehouse yet"));
        if(piw.getCount()<count){
            throw new RuntimeException("The quantity of goods in warehouse is less than the requested one");
        }
        if(piw.getCount()-count==0){
            productInWarehouseDao.delete(piw);
        }else{
            piw.setCount(piw.getCount()-count);
            productInWarehouseDao.save(piw);
        }

    }
    private void moveProductBetweenWarehouse(Product product,
                                             Warehouse initialWarehouse,
                                             Warehouse finalWarehouse,
                                             Integer count){
        removeProductFromWarehouse(product,initialWarehouse,count);
        addProductOnWarehouse(product,finalWarehouse,count);
    }
    public void changeProductPresence(DocumentType documentType, Product product , Warehouse warehouse, Integer count){
        if(documentType.equals(DocumentType.PROCEED)){
            addProductOnWarehouse(product,warehouse,count);
        }else {
            removeProductFromWarehouse(product,warehouse,count);
        }
    }
    public void changeProductPresence(Product product , Warehouse initialWarehouse,Warehouse finalWarehouse, Integer count){
        moveProductBetweenWarehouse(product,initialWarehouse,finalWarehouse,count);
    }
}
