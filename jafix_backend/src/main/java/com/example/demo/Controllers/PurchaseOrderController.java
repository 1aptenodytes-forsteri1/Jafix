package com.example.demo.Controllers;

import com.example.demo.Models.PurchaseOrder;
import com.example.demo.Repositories.BatchRepository;
import com.example.demo.Repositories.PurchaseOrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderController {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final BatchRepository batchRepository;
    PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository, BatchRepository batchRepository){
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.batchRepository = batchRepository;
    }
    @PostMapping
    @Transactional
    public void makeOrder(@RequestBody PurchaseOrder purchaseOrder){
        purchaseOrderRepository.makeOrder(purchaseOrder);
        batchRepository.spendIngredient(purchaseOrderRepository.getLatest());
    }
    @GetMapping
    public List<PurchaseOrder> getOrders(@RequestParam Integer userId){
        return purchaseOrderRepository.getOrdersByUserId(userId);
    }
}
