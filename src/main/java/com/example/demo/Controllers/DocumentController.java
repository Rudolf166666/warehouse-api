package com.example.demo.Controllers;

import com.example.demo.Enums.DocumentType;
import com.example.demo.Exeptions.ApiException;
import com.example.demo.Models.Product;
import com.example.demo.Models.Warehouse;
import com.example.demo.Requests.MovingProductRequest;
import com.example.demo.Requests.ProceedProductRequest;
import com.example.demo.Requests.SaleProductRequest;
import com.example.demo.Services.DocumentService;
import com.example.demo.Services.ProductInWarehouseService;
import com.example.demo.Services.ProductService;
import com.example.demo.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/document")
public class DocumentController {
    private WarehouseService warehouseService;
    private DocumentService documentService;
    private ProductService productService;
    private ProductInWarehouseService productInWarehouseService;

    @Autowired
    public DocumentController(WarehouseService warehouseService,
                              DocumentService documentService,
                              ProductService productService,
                              ProductInWarehouseService productInWarehouseService) {
        this.warehouseService = warehouseService;
        this.documentService = documentService;
        this.productService = productService;
        this.productInWarehouseService=productInWarehouseService;
    }

    @PostMapping("/proceed")
    public ResponseEntity<Map<String,Object>> proceedProducts( @Validated @RequestBody ProceedProductRequest body){
        Warehouse warehouse=warehouseService.findWarehouse(body.getWarehouse_id());
       List<Product> productList=productService.findAllProducts(
               body.getProducts().stream()
                       .map(e->e.getProductId()).collect(Collectors.toList()));
       productService.checkProductRequest(productList,body.getProducts());
       productList.stream().forEach(p->{

           ProceedProductRequest.Product productData=body.getProducts()
                   .stream()
                   .filter(e->e.getProductId()==p.getId())
                   .findFirst()
                   .get();
           productInWarehouseService.changeProductPresence(DocumentType.PROCEED,p,warehouse,productData.getCount());
           documentService.createProceedDocument(
                   body.getNumber(),
                   p,
                   warehouse,
                   productData.getCount(),
                   productData.getPrice());
           productService.updateProductLastPurchasePrice(p,productData.getPrice());

       });
        Map<String,Object> res =new HashMap<>();
        res.put("message","The products were successfully added to the warehouse");
        return ResponseEntity.ok().body(res);

    };
    @PostMapping("/sale")
    public ResponseEntity<Map<String,Object>> saleProducts(@Validated @RequestBody SaleProductRequest body){
        Warehouse warehouse=warehouseService.findWarehouse(body.getWarehouse_id());
        List<Product> productList=productService.findAllProducts(
                body.getProducts().stream()
                        .map(e->e.getProductId()).collect(Collectors.toList()));
        productService.checkProductRequest(productList,body.getProducts());
        productList.stream().forEach(p->{
            SaleProductRequest.Product productData=body.getProducts()
                    .stream()
                    .filter(e->e.getProductId()==p.getId())
                    .findFirst()
                    .get();
            productInWarehouseService.changeProductPresence(DocumentType.SALE,p,warehouse,productData.getCount());
            documentService.createSaleDocument(
                    body.getNumber(),
                    p,
                    warehouse,
                    productData.getCount(),
                    productData.getPrice());
            productService.updateProductLastSalePrice(p,productData.getPrice());
        });
        Map<String,Object> res =new HashMap<>();
        res.put("message","The products were successfully sale from warehouse");
        return ResponseEntity.ok().body(res);
    };
    @PostMapping("/move")
    public ResponseEntity<Map<String,Object>> moveProducts(@Validated @RequestBody MovingProductRequest body){
        Warehouse initialWarehouse=warehouseService.findWarehouse(body.getInitialWarehouseId());
        Warehouse finalWarehouse=warehouseService.findWarehouse(body.getFinalWarehouseId());
        List<Product> productList=productService.findAllProducts(
                body.getProducts().stream()
                        .map(e->e.getProductId()).collect(Collectors.toList()));
        productService.checkProductRequestForMoving(productList,body.getProducts());
        productList.stream().forEach(p->{
            MovingProductRequest.Product productData=body.getProducts()
                    .stream()
                    .filter(e->e.getProductId()==p.getId())
                    .findFirst()
                    .get();
            productInWarehouseService.changeProductPresence(p,initialWarehouse,finalWarehouse,productData.getCount());
            documentService.createMovingDocument(
                    body.getNumber(),
                    p,
                    initialWarehouse,
                    finalWarehouse,
                    productData.getCount());
        });
        Map<String,Object> res =new HashMap<>();
        res.put("message","The products were successfully moved between warehouses");
        return ResponseEntity.ok().body(res);
    };
    @GetMapping("/proceed")
    public  ResponseEntity<Map<String,Object>> getProceedDocumentStatistics(
            @RequestParam Integer page,
            @RequestParam Integer amount){
        return documentService.getProceedDocumentStatistics(page,amount);
    }
    @GetMapping("/sale")
    public  ResponseEntity<Map<String,Object>> getSaleDocumentStatistics(
            @RequestParam Integer page,
            @RequestParam Integer amount){
        return documentService.getSaleDocumentStatistics(page,amount);
    }
    @GetMapping("/move")
    public  ResponseEntity<Map<String,Object>> getMoveDocumentStatistics(
            @RequestParam Integer page,
            @RequestParam Integer amount){
        return documentService.getMoveDocumentStatistics(page,amount);
    }
}
