package com.example.demo.Controllers;

import com.example.demo.Models.PurchaseOrder;
import com.example.demo.Repositories.PurchaseOrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderController {
    private final PurchaseOrderRepository purchaseOrderRepository;
    PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository){
        this.purchaseOrderRepository = purchaseOrderRepository;
    }
    @PostMapping
    public void makeOrder(@RequestBody PurchaseOrder purchaseOrder){
        purchaseOrderRepository.makeOrder(purchaseOrder);
    }
    @GetMapping
    public List<PurchaseOrder> getOrders(@RequestParam Integer userId){
        return purchaseOrderRepository.getOrdersByUserId(userId);
    }
}
