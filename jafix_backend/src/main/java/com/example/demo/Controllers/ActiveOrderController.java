package com.example.demo.Controllers;
import com.example.demo.Models.PurchaseOrder;
import com.example.demo.Repositories.PurchaseOrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/active_order")
public class ActiveOrderController {
    private final PurchaseOrderRepository purchaseOrderRepository;
    ActiveOrderController(PurchaseOrderRepository purchaseOrderRepository){
        this.purchaseOrderRepository = purchaseOrderRepository;
    }
    @GetMapping
    public List<PurchaseOrder> getActiveOrders(@RequestParam Integer id){
        return purchaseOrderRepository.getActiveOrders(id);
    }
    @PatchMapping
    public void deactivateOrder(@RequestParam Integer orderId){
        purchaseOrderRepository.deactivateOrder(orderId);
    }
    @PutMapping
    private void activateOrder(@RequestParam Integer orderId){
        purchaseOrderRepository.activateOrder(orderId);
    }
}
