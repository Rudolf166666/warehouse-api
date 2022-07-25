package com.example.demo.Services;
import com.example.demo.Dto.MoveDto;
import com.example.demo.Dto.ProceedDto;
import com.example.demo.Dto.SaleDto;
import com.example.demo.Dto.WarehousesDto;
import com.example.demo.Models.*;
import com.example.demo.Repositories.MovingDao;
import com.example.demo.Repositories.ProceedDao;
import com.example.demo.Repositories.SaleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService {
    private ProceedDao proceedDao;
    private MovingDao movingDao;
    private SaleDao saleDao;

    @Autowired
    public DocumentService(ProceedDao proceedDao, MovingDao movingDao, SaleDao saleDao) {
        this.proceedDao = proceedDao;
        this.movingDao = movingDao;
        this.saleDao = saleDao;
    }
    public void createProceedDocument(Long number,Product p, Warehouse warehouse, Integer count,Integer price){
        ProceedGoods pg=new ProceedGoods(number,p,count,warehouse,price);
        proceedDao.save(pg);
    }
    public void createSaleDocument(Long number,Product p, Warehouse warehouse, Integer count,Integer price){
        SaleGoods sg=new SaleGoods(number,p,count,warehouse,price);
        saleDao.save(sg);
    }
    public void createMovingDocument(Long number,Product p, Warehouse initialWarehouse,Warehouse finalWarehouse, Integer count){
        MovingGoods wg=new MovingGoods(number,p,count,initialWarehouse,finalWarehouse);
        movingDao.save(wg);
    }
    public ResponseEntity<Map<String,Object>> getProceedDocumentStatistics(Integer page, Integer amount){
        Map<String,Object>response=new HashMap<>();
        Pageable pageWithSort = PageRequest.of(page-1, amount);
        Page<ProceedDto> proceeds=proceedDao.getProceedStatistic(pageWithSort);
        Long total= proceedDao.count();
        response.put("proceeds",proceeds.get());
        response.put("page",page);
        response.put("total",total);
        response.put("pagesCount",Math.ceil((double)total/amount));

        return ResponseEntity.ok().body(response);
    }
    public ResponseEntity<Map<String,Object>> getSaleDocumentStatistics(Integer page, Integer amount){
        Map<String,Object>response=new HashMap<>();
        Pageable pageWithSort = PageRequest.of(page-1, amount);
        Page<SaleDto> sales=saleDao.getSaleStatistic(pageWithSort);
        Long total= saleDao.count();
        response.put("sales",sales.get());
        response.put("page",page);
        response.put("total",total);
        response.put("pagesCount",Math.ceil((double)total/amount));

        return ResponseEntity.ok().body(response);
    }
    public ResponseEntity<Map<String,Object>> getMoveDocumentStatistics(Integer page, Integer amount){
        Map<String,Object>response=new HashMap<>();
        Pageable pageWithSort = PageRequest.of(page-1, amount);
        Page<MoveDto> moves=movingDao.getProceedStatistic(pageWithSort);
        Long total= movingDao.count();
        response.put("moves",moves.get());
        response.put("page",page);
        response.put("total",total);
        response.put("pagesCount",Math.ceil((double)total/amount));

        return ResponseEntity.ok().body(response);
    }
}
